package com.example.sidenav;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class PageAdapter extends FragmentPagerAdapter {
    public int numOfTabs;

    private static final String TAG = "PageAdapter";

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i){

            case 0:
                return new AcceptedInvites();
            case 1:
                return new NotAcceptedInvites();
            default:
                return new AcceptedInvites();
        }
    }


    @Override
    public int getCount() {
        return numOfTabs;
    }
}
