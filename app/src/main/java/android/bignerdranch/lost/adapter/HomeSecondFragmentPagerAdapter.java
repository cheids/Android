package android.bignerdranch.lost.adapter;

import android.bignerdranch.lost.fragment.HomeGetFragment;
import android.bignerdranch.lost.fragment.HomePeopleFragment;
import android.bignerdranch.lost.fragment.HomeSearchFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomeSecondFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"寻物启事", "失物招领", "寻人启事"};

    public HomeSecondFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeSearchFragment();
        } else if (position == 1) {
            return new HomeGetFragment();
        }
        return new HomePeopleFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

}
