package android.bignerdranch.lost.activity;

import android.app.Activity;
import android.bignerdranch.lost.R;

import android.bignerdranch.lost.bean.User;
import android.bignerdranch.lost.db.UserService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

    EditText mUsername;
    EditText mPassword;
    Button mRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registter);

        mUsername = (EditText) findViewById(R.id.register_user);
        mPassword = (EditText) findViewById(R.id.register_password);
        mRegister = (Button) findViewById(R.id.register_button);
        mRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = mUsername.getText().toString().trim();
                String pass = mPassword.getText().toString().trim();
                UserService uService=new UserService(RegisterActivity.this);
                User user=new User();
                user.setUsername(name);
                user.setPassword(pass);
                uService.register(user);
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }





}