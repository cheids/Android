package android.bignerdranch.lost.activity;

import android.bignerdranch.lost.R;
import android.bignerdranch.lost.base.BaseActivity;
import android.bignerdranch.lost.bean.EventMessage;
import android.bignerdranch.lost.bean.Luntan;
import android.bignerdranch.lost.utils.LuntanDBUtils;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;


import com.bumptech.glide.Glide;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class SendLuntanActivity extends BaseActivity {


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.image_head)
    ImageView imageHead;
    @BindView(R.id.rl_head)
    LinearLayout rlHead;



    @BindView(R.id.tv_biaoti)
    EditText tvbiaoti;
    @BindView(R.id.sp_type)
    Spinner sptype;
    @BindView(R.id.sp_type1)
    Spinner sptype1;

    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.btn_send)
    AppCompatTextView btnSend;

    String leixing11="展示";
    String leixing22="老师";
    String path= "";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_luntan;
    }

    @Override
    protected void init() {
        tvTitle.setText("发布动态");
        sptype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] language=getResources().getStringArray(R.array.leixing);
                leixing11 = language[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sptype1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] language=getResources().getStringArray(R.array.leixing1);
                leixing22 = language[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @OnClick({R.id.rl_back, R.id.rl_head, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_head:
                PictureSelector
                        .create(SendLuntanActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture();
                break;
            case R.id.btn_send:
                String content = tvName.getText().toString();
                String tvbiaoti1 = tvbiaoti.getText().toString();
                if (TextUtils.isEmpty(content)){
                    showToast("请输入动态...");
                    return;
                } else {
                    Luntan luntan = new Luntan();
                    luntan.setUsername("张三");
                    luntan.setPic(path);
                    luntan.setBiaoti(tvbiaoti1);
                    luntan.setLeixing(leixing11);
                    luntan.setFabuzhe(leixing22);
                    luntan.setZan("0");
                    luntan.setContent(content);
                    //luntan.setTime(utils.getTime());
                    LuntanDBUtils.getInstance(getApplicationContext()).insert(luntan);
                    showToast("发布成功");
                    EventBus.getDefault().post(new EventMessage(EventMessage.REFRESH));
                    finish();
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                Glide.with(this).load(pictureBean.getPath()).into(imageHead);
                path = pictureBean.getPath();
            }
        }
    }
}
