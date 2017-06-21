package com.assin.buttonnavtion;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OneFragment.OnFragmentInteractionListener
        ,ViewPager.OnPageChangeListener{

    private ViewPager mViewPagerContent;
    private MenuItem menuItem;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_choiceness:
                    mViewPagerContent.setCurrentItem(0,false);  //第二个参数 为false，就是指不需要通过滑动切换过去
                    return true;
                case R.id.navigation_discover:
                    mViewPagerContent.setCurrentItem(1,false);
                    return true;
                case R.id.navigation_service:
                    mViewPagerContent.setCurrentItem(2,false);
                    return true;
                case R.id.navigation_my:
                    mViewPagerContent.setCurrentItem(3,false);

                    return true;
            }
            return false;
        }

    };

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPagerContent = (ViewPager) findViewById(R.id.vp_content);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setFragment();
        mViewPagerContent.addOnPageChangeListener(this);

        //禁止ViewPager滑动
//        mViewPagerContent.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

    }
    private void setFragment(){
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(OneFragment.newInstance("what","why"));
        fragments.add(OneFragment.newInstance("what","why"));
        fragments.add(OneFragment.newInstance("what","why"));
        fragments.add(OneFragment.newInstance("what","why"));
        mViewPagerContent.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),fragments));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    /////////////////////////////OnPageChangeListener////////////////////////////////////////////////////

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (menuItem != null){
            menuItem.setChecked(false);
        }else {
            navigation.getMenu().getItem(0).setChecked(false);
        }
        menuItem = navigation.getMenu().getItem(position);
        menuItem.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



    private class ViewPagerAdapter extends FragmentPagerAdapter{

        private List<Fragment> fragmentList;

        ViewPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);
            fragmentList = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }




}
