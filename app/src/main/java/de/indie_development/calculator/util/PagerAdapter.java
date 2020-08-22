package de.indie_development.calculator.util;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import de.indie_development.calculator.R;
import de.indie_development.calculator.fragments.tabs.CalculatorFragment;
import de.indie_development.calculator.fragments.tabs.ConverterFragment;
import de.indie_development.calculator.fragments.tabs.FormulasFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private static final int[] tab_titles = {R.string.firstTab, R.string.secondTab, R.string.thirdTab};
    private Context tab_context;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        tab_context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return new CalculatorFragment();

            case 1:
                return new FormulasFragment();

            case 2:
                return new ConverterFragment();

            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab_context.getResources().getString(tab_titles[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}