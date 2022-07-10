package android.bignerdranch.lost.base;

import android.content.Context;
import android.content.Intent;

import com.trello.rxlifecycle2.components.support.RxFragment;

public class BaseFragment extends RxFragment {



    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public Context getViewContext(){
        return getActivity();
    }

    public void reStartApp() {
        Intent intent = getActivity().getBaseContext().getPackageManager().getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);            //与正常页面跳转一样可传递序列化数据,在Launch页面内获得
        intent.putExtra("REBOOT", "reboot");
        startActivity(intent);
        getActivity().finish();
    }
}