package com.tianye.mobile.well.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import com.tianye.mobile.well.App;
import com.tianye.mobile.well.R;
import com.tianye.mobile.well.adapter.NavigationDrawerAdapter;
import com.tianye.mobile.well.fragment.BaseActivity;
import com.tianye.mobile.well.fragment.GPSFragment1;
import com.tianye.mobile.well.fragment.HomePagerFragment;
import com.tianye.mobile.well.fragment.IScanFragment;
import com.tianye.mobile.well.fragment.ImageLoaderFragment;
import com.tianye.mobile.well.fragment.LinkmainFragment;
import com.tianye.mobile.well.fragment.MessagePushFragment;
import com.tianye.mobile.well.fragment.NavigationDrawerFragment;
import com.tianye.mobile.well.fragment.TextToSpeecherFragment;
import com.tianye.mobile.well.fragment.VolleyFragment;
import com.tianye.mobile.well.util.ToastUtil;

import cn.jpush.android.api.JPushInterface;

import static com.tianye.mobile.well.fragment.BaseFragment.ARG_SECTION_NUMBER;


public class MainActivity extends BaseActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private NavigationDrawerAdapter navigationDrawerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPushManager();

        setSupportActionBar((android.support.v7.widget.Toolbar)findViewById(R.id.toolbar));

        //setOverflowButtonAlways();
        getSupportActionBar().setDisplayShowHomeEnabled(false); // 使左上角图标是否显示
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        //mTitle = getTitle();

        navigationDrawerAdapter = new NavigationDrawerAdapter(MainActivity.this);
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),navigationDrawerAdapter);
    }

    private void initPushManager() {
        //PushManager.getInstance().initialize(this.getApplicationContext());

        JPushInterface.init(getApplicationContext());

    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(App.getContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(App.getContext());
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new Fragment();
        Bundle args = new Bundle();

        ToastUtil.showLong("current check" + position );
        switch(position){
            case 0:
                fragment = new HomePagerFragment();
                break;
            case 1:

                fragment = new TextToSpeecherFragment();
                break;
            case 2:

                fragment = new IScanFragment();

                break;
            case 3:
                fragment = new GPSFragment1();
                break;

            case 4:
                fragment = new MessagePushFragment();
                break;
            case 5:
                fragment = new LinkmainFragment();
                break;
            case 6:
                fragment = new VolleyFragment();
                break;
            case 7:
                fragment = new ImageLoaderFragment();
                break;
            default:
                fragment = new HomePagerFragment();
                break;
        }
        //onSectionAttached(position);
        args.putInt(ARG_SECTION_NUMBER,position);
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

//    public void onSectionAttached(int number) {
//        switch (number) {
//            case 0:
//                mTitle = getString(R.string.title_section1);
//                break;
//            case 1:
//                mTitle = getString(R.string.title_section2);
//                break;
//            case 2:
//                mTitle = getString(R.string.title_section3);
//                break;
//        }
//    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//
//    private void setOverflowButtonAlways()
//    {
//        try
//        {
//            ViewConfiguration config = ViewConfiguration.get(this);
//            Field menuKey = ViewConfiguration.class
//                    .getDeclaredField("sHasPermanentMenuKey");
//            menuKey.setAccessible(true);
//            menuKey.setBoolean(config, false);
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
}
