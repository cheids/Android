package android.bignerdranch.lost.adapter;

import android.bignerdranch.lost.R;
import android.bignerdranch.lost.bean.Pinglun;
import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


public class PinglunAdapter extends BaseQuickAdapter<Pinglun, BaseViewHolder> {
    private Context context;

    public PinglunAdapter(Context context) {
        super(R.layout.item_pinglun);
        this.context =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Pinglun item) {
        helper.setText(R.id.tv_name,item.getUsername());
        helper.setText(R.id.tv_content,item.getContent());
        helper.setText(R.id.tv_time,item.getTime());
        if (!TextUtils.isEmpty(item.getHead_url())){
            Glide.with(context).load(item.getHead_url()).into((ImageView) helper.getView(R.id.image_head));
        }
    }
}