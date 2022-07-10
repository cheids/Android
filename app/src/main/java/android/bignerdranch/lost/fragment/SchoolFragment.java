package android.bignerdranch.lost.fragment;

import android.bignerdranch.lost.R;
import android.bignerdranch.lost.activity.PinglunActivity;
import android.bignerdranch.lost.activity.SendLuntanActivity;
import android.bignerdranch.lost.base.LazyFragment;
import android.bignerdranch.lost.bean.Luntan;
import android.bignerdranch.lost.utils.LuntanDBUtils;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SchoolFragment extends LazyFragment {

    ListView swipeRefresh;
    Luntan1Adapter luntanAdapter;
    List<Luntan> luntanList;
    RelativeLayout rlBack;
    TextView tvTitle;
    ImageView tvRight;

    TextView quanbu;
    TextView zhanshi;
    TextView wenda;
    TextView tongzhi;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_school;
    }

    @Override
    protected void loadData() {
        unbinder = ButterKnife.bind(this, rootView);
        tvTitle.setText("公开发帖");

        tvRight.setVisibility(View.VISIBLE);
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SendLuntanActivity.class));
            }
        });

        luntanList = new ArrayList<>();
        rlBack.setVisibility(View.GONE);
        luntanAdapter = new Luntan1Adapter(getActivity(), LuntanDBUtils.getInstance(getActivity()).findAll());
        luntanAdapter.notifyDataSetChanged();
        swipeRefresh.setAdapter(luntanAdapter);

        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SendLuntanActivity.class);
                startActivityForResult(intent,101);
            }
        });

        quanbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                luntanAdapter = new Luntan1Adapter(getActivity(), LuntanDBUtils.getInstance(getActivity()).findAll());
                swipeRefresh.setAdapter(luntanAdapter);
            }
        });

        zhanshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                luntanAdapter = new Luntan1Adapter(getActivity(), LuntanDBUtils.getInstance(getActivity()).loadByName("展示"));
                swipeRefresh.setAdapter(luntanAdapter);
            }
        });

        wenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                luntanAdapter = new Luntan1Adapter(getActivity(), LuntanDBUtils.getInstance(getActivity()).loadByName("问答"));
                swipeRefresh.setAdapter(luntanAdapter);
            }
        });

        tongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                luntanAdapter = new Luntan1Adapter(getActivity(), LuntanDBUtils.getInstance(getActivity()).loadByName("通知"));
                swipeRefresh.setAdapter(luntanAdapter);
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        luntanAdapter = new Luntan1Adapter(getActivity(), LuntanDBUtils.getInstance(getActivity()).findAll());
        luntanAdapter.notifyDataSetChanged();
        swipeRefresh.setAdapter(luntanAdapter);
    }

    class Luntan1Adapter extends BaseAdapter {
        private List<Luntan> listdata;
        private Context context;


        public Luntan1Adapter(Context context, List<Luntan> listdata) {
            this.context = context;
            this.listdata = listdata;
        }

        @Override
        public int getCount() {
            return listdata.size();
        }

        @Override
        public Object getItem(int position) {
            return listdata.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_home_luntan, null);
                viewHolder.iv_pic = convertView.findViewById(R.id.iv_pic);
                viewHolder.image_head = convertView.findViewById(R.id.image_head);
                viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
                viewHolder.tv_biaoti = convertView.findViewById(R.id.tv_biaoti);
                viewHolder.tv_content = convertView.findViewById(R.id.tv_content);
                viewHolder.tv_content = convertView.findViewById(R.id.tv_content);
                viewHolder.tv_fabuzhe = convertView.findViewById(R.id.tv_fabuzhe);
                viewHolder.tv_zan = convertView.findViewById(R.id.tv_zan);
                viewHolder.ll_pl = convertView.findViewById(R.id.ll_pl);
                viewHolder.ll_zan = convertView.findViewById(R.id.ll_zan);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_name.setText(listdata.get(position).getUsername());
            viewHolder.tv_biaoti.setText("标题："+listdata.get(position).getBiaoti());
            viewHolder.tv_content.setText(listdata.get(position).getContent());
            viewHolder.tv_fabuzhe.setText("发布者："+listdata.get(position).getFabuzhe());
            viewHolder.tv_zan.setText("点赞"+listdata.get(position).getZan());
            Glide.with(context).load(listdata.get(position).getPic()).into(viewHolder.iv_pic);
            if (!TextUtils.isEmpty(listdata.get(position).getHead_url())) {
                Glide.with(context).load(listdata.get(position).getHead_url()).into(viewHolder.image_head);
            }
            if (!TextUtils.isEmpty(listdata.get(position).getPic())) {
                viewHolder.iv_pic.setVisibility(View.VISIBLE);
                Glide.with(context).load(listdata.get(position).getPic()).into(viewHolder.iv_pic);
            }
            viewHolder.ll_pl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), PinglunActivity.class);
                    intent.putExtra("detail", listdata.get(position).getId());
                    startActivity(intent);
                }
            });
            viewHolder.ll_zan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int zan = Integer.parseInt(listdata.get(position).getZan()) + 1;
                    listdata.get(position).setZan(zan + "");
                    LuntanDBUtils.getInstance(getActivity()).change(getActivity(), listdata.get(position));
                    luntanList=new ArrayList<>();
                    luntanList.clear();
                    luntanList = LuntanDBUtils.getInstance(getActivity()).findAll();
//                    luntanAdapter.setNewData(luntanList);
//                    luntanAdapter.notifyDataSetChanged();
                    luntanAdapter.notifyDataSetChanged();
                    luntanAdapter = new Luntan1Adapter(getActivity(), luntanList);
                    luntanAdapter.notifyDataSetChanged();
                    swipeRefresh.setAdapter(luntanAdapter);
                }
            });
            return convertView;
        }

        class ViewHolder {
            ImageView image_head;
            ImageView iv_pic;
            TextView tv_name, tv_biaoti, tv_content, tv_fabuzhe,tv_zan;
            LinearLayout ll_pl, ll_zan;
        }


    }
}
