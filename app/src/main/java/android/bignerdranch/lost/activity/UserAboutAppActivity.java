package android.bignerdranch.lost.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.bignerdranch.lost.R;
import android.view.View;
import android.widget.TextView;

public class UserAboutAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_about_app);

        /**
         * 报告编译版本 */
        TextView apiLevelTextView = (TextView) findViewById(R.id.api_level_text_view);
        apiLevelTextView.setText(getString(R.string.apiLevel, Build.VERSION.RELEASE));

        init();
    }

    void init() {
    }

    public void officialSite1(View view) {
        Uri telUri = Uri.parse("tel:18834398010");
        Intent returnIt = new Intent(Intent.ACTION_DIAL, telUri);
        startActivity(returnIt);
    }


    public void officialSite2(View view) {
        Uri mapUri = Uri.parse("geo:38.899533,-77.036476");
        Intent returnIt = new Intent(Intent.ACTION_VIEW, mapUri);
        startActivity(returnIt);
    }


    public void officialSite3(View view) {
        Intent intent = new Intent(UserAboutAppActivity.this,UserAboutAppVideoActivity.class);
        startActivity(intent);
    }

    public void back(View view) {
        finish();
    }
}