package android.bignerdranch.lost.activity;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

/** 抽取activity中常用的方法，简化activity中的代码 **/

public class WelcomeBaseActivity extends FragmentActivity {


    /**
     * 提示信息
     */
    public void toast(String str){
        Toast.makeText(this, str, 0).show();
    }


    /**
     * 在LogCat中打印相关信息，用于调试
     */
    public void log(String str){
        Log.i("MyTag", str);
    }


    /**
     * 获取TextView中用户输入的内容
     * @return  内容
     */
    public String getTVContent(TextView tv){
        String str = tv.getText().toString().trim();
        if (str == null) {
            str = "";
            return str;
        }
        return str;
    }


    /**
     * 进度条对话框
     * @param isCancel 是否可取消
     * @param title  标题
     * @param message 内容
     * @return  进度条对话框对象
     */
    public ProgressDialog showProDialog(boolean isCancel, CharSequence title, CharSequence message){
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setCancelable(isCancel);
        pDialog.setTitle(title);
        pDialog.setMessage(message);
        pDialog.show();
        return pDialog;
    }
}
