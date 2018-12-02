package com.android7.Sem7;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter{

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Currency0ActivityFragment();
            case 1:
                return new Currency1ActivityFragment();
            case 2:
                return new Currency2ActivityFragment();
            case 3:
                return new Currency3ActivityFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "EUR";
            case 1:
                return "PLN";
            case 2:
                return "USD";
            case 3:
                return "JPY";
            default:
                return null;
        }
    }
}
