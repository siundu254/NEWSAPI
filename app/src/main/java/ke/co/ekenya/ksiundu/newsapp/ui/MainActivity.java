package ke.co.ekenya.ksiundu.newsapp.ui;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

import ke.co.ekenya.ksiundu.newsapp.R;
import ke.co.ekenya.ksiundu.newsapp.adapter.HomeTabsAdapter;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager mMainPager = findViewById(R.id.viewPagerMain);
        TabLayout mMainTabs = findViewById(R.id.tabLayoutMain);

        HomeTabsAdapter mAdapter = new HomeTabsAdapter(getSupportFragmentManager());
        mMainTabs.setupWithViewPager(mMainPager);
        mMainPager.setAdapter(mAdapter);
        mMainTabs.getTabAt(0).setIcon(R.drawable.icon_headlines);
        mMainTabs.getTabAt(1).setIcon(R.drawable.icon_globe);
        mMainTabs.getTabAt(2).setIcon(R.drawable.icon_tech);

    }
}
