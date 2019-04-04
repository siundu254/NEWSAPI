package ke.co.ekenya.ksiundu.newsapp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ke.co.ekenya.ksiundu.newsapp.Fragments.HeadLinesFragment;
import ke.co.ekenya.ksiundu.newsapp.Fragments.TechFragment;
import ke.co.ekenya.ksiundu.newsapp.Fragments.WorldFragment;

public class HomeTabsAdapter extends FragmentPagerAdapter {
    public HomeTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new HeadLinesFragment();
            case 1:
                return new WorldFragment();
            case 2:
                return new TechFragment();
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
