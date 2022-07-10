package android.bignerdranch.lost.activity;


import android.app.Activity;
import android.bignerdranch.lost.R;
import android.bignerdranch.lost.db.UserService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;


public class LoginActivity extends Activity implements View.OnClickListener {

    private ArrayList<String> usernamelList;

    private Button mLoginButton;
    private Button mRegisterButton;
    private EditText mUserEdit;
    private EditText mPwdEdit;
    private UserService uService = null;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
//        /**
//         * 登录监听
//         * */
//        mLoginButton = (Button) findViewById(R.id.login_button);
//        mLoginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        /**
//         * 注册监听
//         * */
//        mRegisterButton = (Button) findViewById(R.id.login_register);
//        mRegisterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void initView() {
        mLoginButton = (Button) findViewById(R.id.login_button);
        mRegisterButton = (Button) findViewById(R.id.login_register);

        mUserEdit = (EditText) findViewById(R.id.login_user);
        mPwdEdit = (EditText) findViewById(R.id.login_password);


        mLoginButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);

        uService = new UserService(LoginActivity.this);

        usernamelList = uService.getAll();
    }

    protected void onDestroy() {//销毁
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
        usernamelList.clear();              //从注册返回时清除usernamelList
        usernamelList = uService.getAll();      //更新注册的内容
    }


    /**
     * 按钮点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                String name=mUserEdit.getText().toString();
                String pass=mPwdEdit.getText().toString();
                boolean flag=uService.login(name, pass);
                if (flag) {
                    Log.i("TAG","登录成功");
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    Log.i("TAG","登录失败");
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.login_register:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }

    }
}



