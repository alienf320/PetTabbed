package com.mchavez.pettabbed.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class PageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    public PageAdapter (FragmentManager fr, ArrayList<Fragment> fragments){
        super(fr);
        this.fragments=fragments;
    }

    public Fragment getItem (int position){
        return fragments.get(position);
    }

    public int getCount(){
        return fragments.size();
    }
}