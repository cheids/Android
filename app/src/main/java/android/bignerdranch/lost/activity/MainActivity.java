package android.bignerdranch.lost.activity;

import android.bignerdranch.lost.R;
import android.bignerdranch.lost.Server.MusicServer;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 背景音乐
         * */
        Intent intent = new Intent(MainActivity.this,MusicServer.class);
        startService(intent);

        // 获取页面上的底部导航栏控件
        BottomNavigationView navView = findViewById(R.id.bottom_view);

        // 配置navigation与底部菜单之间的联系
        // 底部菜单的样式里面的item里面的ID与navigation布局里面指定的ID必须相同，否则会出现绑定失败的情况
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_school, R.id.navigation_user)
                .build();

        // 建立fragment容器的控制器，这个容器就是页面的上的fragment容器
        NavController navController = Navigation.findNavController(this, R.id.bottom_host_fragment);

        // 启动
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }


    protected void onStop(){
        Intent intent = new Intent(MainActivity.this, MusicServer.class);
        stopService(intent);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_add, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item0:
                Intent intent0 = new Intent(MainActivity.this, HomePeopleAddActivity.class);
                startActivity(intent0);
                break;
            case R.id.menu_item1:
                Intent intent1 = new Intent(MainActivity.this, HomeGetAddActivity.class);
                startActivity(intent1);
                break;
            case R.id.menu_item2:
                Intent intent2 = new Intent(MainActivity.this, HomeSearchAddActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}