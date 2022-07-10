package android.bignerdranch.lost.base;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends RxAppCompatActivity {

    protected Unbinder unBinder;
    protected Context mContext;






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setWindowParams();

        setContentView(getLayoutId());
        mContext = this;
        unBinder = ButterKnife.bind(this);
        init();

    }


    protected String getCurrentTime(){
        String currentTime= DateFormat.format("yyyy-MM-dd hh-mm-ss", new Date()).toString();
        return currentTime;
    }
    public void setWindowParams(){

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //移除view绑定
        if (unBinder != null) {
            unBinder.unbind();
        }


    }


    protected abstract int getLayoutId();

    protected abstract void init();


    protected void showToast(String message){
        Toast.makeText(BaseActivity.this,message,Toast.LENGTH_SHORT).show();
    }


}
