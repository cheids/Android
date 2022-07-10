package android.bignerdranch.lost.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bignerdranch.lost.Interface.WelcomeBaseInterface;
import android.bignerdranch.lost.R;
import android.bignerdranch.lost.utils.NetIsConnUtil;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class WelcomeActivity extends WelcomeBaseActivity implements WelcomeBaseInterface {

    private ImageView welcome_iv;
    private Animation animation;        // 动画对象的声明
    private boolean netIsConnflag = false;      // 判断网络是否连接，默认未连接
    private ProgressDialog progressDialog;      // 持续判断是否联网的进度条对话框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();
        initData();
        initOperat();
    }

    @Override
    public void initView() {
        welcome_iv = (ImageView) findViewById(R.id.welcome_iv);
    }

    @Override
    public void initData() {
        // 获取动画对象，给图片设置动画（渐变和拉伸）
        animation = AnimationUtils.loadAnimation(this, R.anim.anim_set);
    }

    @Override
    public void initOperat() {
        welcome_iv.setAnimation(animation);     // 给图片设置动画
        animation.setFillAfter(true);       // 停在动画结束后的状态
        animation.start();
        animation.setAnimationListener(new Animation.AnimationListener() {

            // 动画开始时的回调
            @Override
            public void onAnimationStart(Animation animation) {
                /**
                 * 判断联网状态(使用工具类)
                 */
                netIsConnflag = NetIsConnUtil.netIsConn(WelcomeActivity.this);
            }

            // 动画重复时的回调
            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            // 动画结束时的回调
            @Override
            public void onAnimationEnd(Animation animation) {
                /**
                 * 网络状态的判断，有网跳转到登陆界面，没网提示设置网络
                 */
                if (netIsConnflag) {
                    Intent intent = new Intent(WelcomeActivity.this,
                            LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    toast("网络未连接");
                    // 警告对话框，提示用户去设置网络
                    alertDialog();
                }
            }
        });
    }

    /**
     * 警告对话框，用于提示用户设置网络
     */
    private void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
        builder.setTitle("警告");
        builder.setCancelable(false);
        builder.setMessage("网络未连接，请设置网络");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                /**
                 * 点击取消按钮，退出应用
                 */
                System.exit(0);// 正常退出
            }
        });

        builder.setPositiveButton("设置网络", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        });
        builder.show();
    }

    /**
     * 利用生命周期的回调函数判断 当用户设置完网络，返回时，停8秒再次去判断用户是否联网
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        new Thread() {
            public void run() {
                /**
                 * 8秒判断一次是否联网
                 */
                for (int i = 0; i < 8; i++) {
                    if (i == 3) {
                        netIsConnflag = NetIsConnUtil
                                .netIsConn(WelcomeActivity.this);
                        if (netIsConnflag) {
                            break;
                        }
                    }
                    String message = "......";
                    switch (i % 4) {
                        case 0:
                            message = ".";
                            break;
                        case 1:
                            message = ". .";
                            break;
                        case 2:
                            message = ". . .";
                            break;
                        case 3:
                            message = ". . . .";
                            break;
                    }
                    //避免message不能修改
                    final String MyMessage = message;
                    //跟新UI要在主线程中进行
                    runOnUiThread(new Runnable() {

                        //判断对话框是否为空
                        public void run() {
                            if (progressDialog == null) {
                                progressDialog = showProDialog(false, "正在拼命加载", MyMessage);
                            }else {
                                progressDialog.setMessage(MyMessage);
                            }
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }


                //最后一次判断是否联网
                runOnUiThread(new Runnable() {
                    public void run() {
                        netIsConnflag = NetIsConnUtil.netIsConn(WelcomeActivity.this);
                        if (netIsConnflag) {
                            Intent intent = new Intent(WelcomeActivity.this,
                                    LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            progressDialog.dismiss();
                            progressDialog = null;
                            alertDialog();
                        }
                    }
                });
            }
        }.start();
    }
}