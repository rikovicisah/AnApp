package com.example.irhad.anapp.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> listaFragment = new ArrayList<>();
    private final List<String> listaNaslovi = new ArrayList<>();


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return listaFragment.get(i);
    }

    @Override
    public int getCount() {
        return listaNaslovi.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listaNaslovi.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        listaFragment.add(fragment);
        listaNaslovi.add(title);
    }
}
