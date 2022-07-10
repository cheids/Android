package android.bignerdranch.lost.fragment;

import android.bignerdranch.lost.R;
import android.bignerdranch.lost.adapter.HomeSecondFragmentPagerAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private HomeSecondFragmentPagerAdapter subFragmentPagerAdapter;



    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;

    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;


    private FloatingActionButton mSelectAdd;

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_item0:
//                Intent intent0 = new Intent(getActivity(), HomePeopleAddActivity.class);
//                startActivity(intent0);
//                break;
//            case R.id.menu_item1:
//                Intent intent1 = new Intent(getActivity(), HomeGetFragment.class);
//                startActivity(intent1);
//                break;
//            case R.id.menu_item2:
//                Intent intent2 = new Intent(getActivity(), HomeSearchAddActivity.class);
//                startActivity(intent2);
//                break;
//            default:
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != mView) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (null != parent) {
                parent.removeView(mView);
            }
        } else {
            mView = inflater.inflate(R.layout.fragment_home, null);
            initView();// 控件初始化
        }



        mViewPager = find(R.id.subViewPager);
        subFragmentPagerAdapter = new HomeSecondFragmentPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(subFragmentPagerAdapter);


        //将TabLayout与ViewPager绑定在一起

        mTabLayout = (TabLayout) find(R.id.sub_bottom_tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        return mView;
        /*//if (mView == null) {
        // 需要inflate一个布局文件 填充Fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        isPrepared = true;
        //实现懒加载
        //lazyLoad();
        //}*/

        /*//缓存的mView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个mView已经有parent的错误。
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;*/
    }

    /**
     * 初始化控件
     */
    private void initView() {
        Log.i("test==", "FragmentHome initView");
        //使用适配器将ViewPager与Fragment绑定在一起


    }

    @Override
    public void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        //填充各控件的数据
        mHasLoadedOnce = true;
    }

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

}
