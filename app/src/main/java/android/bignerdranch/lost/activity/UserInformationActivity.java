package android.bignerdranch.lost.activity;

import android.bignerdranch.lost.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserInformationActivity extends AppCompatActivity {

    private Button mBtn_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        mBtn_edit = (Button)findViewById(R.id.btn_edit);

        mBtn_edit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(UserInformationActivity.this, UserInfomodifyActivity.class));
            }
        });
    }
}