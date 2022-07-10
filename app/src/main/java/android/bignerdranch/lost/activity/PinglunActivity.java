package android.bignerdranch.lost.activity;

import android.bignerdranch.lost.R;
import android.bignerdranch.lost.adapter.PinglunAdapter;
import android.bignerdranch.lost.base.BaseActivity;
import android.bignerdranch.lost.bean.Pinglun;
import android.bignerdranch.lost.utils.PinglunDBUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

import java.util.List;

public class PinglunActivity extends BaseActivity {

    PinglunAdapter pinglunAdapter;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.rl_pinglun)
    RecyclerView rlPinglun;
    @BindView(R.id.et_pinglun)
    EditText etPinglun;
    @BindView(R.id.tv_pinglun)
    TextView tvPinglun;
    List<Pinglun> pinglunList;
    int luntan_id;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_school_pinglun;
    }

    @Override
    protected void init() {
        tvTitle.setText("评论");
        luntan_id = getIntent().getIntExtra("detail",0);
        pinglunList = PinglunDBUtils.getInstance(getApplicationContext()).findAllByLuntanId(luntan_id+"");
        pinglunAdapter = new PinglunAdapter(PinglunActivity.this);
        pinglunAdapter.setNewData(pinglunList);
        rlPinglun.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rlPinglun.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
//        pinglunAdapter.bindToRecyclerView(rlPinglun);
        pinglunAdapter.disableLoadMoreIfNotFullPage();
    }



    @OnClick({R.id.rl_back, R.id.tv_pinglun})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_pinglun:
                String content = etPinglun.getText().toString();
                if (TextUtils.isEmpty(content)){
                    showToast("请输入评论内容");
                    return;
                }else {
                    Pinglun pinglun = new Pinglun();
                    pinglun.setLuntan_id(luntan_id+"");
                    pinglun.setContent(content);
                    //pinglun.setTime(utils.getTime());
                    PinglunDBUtils.getInstance(getApplicationContext()).insert(pinglun);
                    pinglunList.clear();
                    pinglunList = PinglunDBUtils.getInstance(getApplicationContext()).findAllByLuntanId(luntan_id+"");
                    //pinglunAdapter.setNewData(pinglunList);
//                    pinglunAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
