package de.indie_development.calculator.util;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import de.indie_development.calculator.R;
import de.indie_development.calculator.fragments.font_size.CalculatorPreviewFragment;
import de.indie_development.calculator.fragments.font_size.ConverterPreviewFragment;
import de.indie_development.calculator.fragments.font_size.FormulasPreviewFragment;
import de.indie_development.calculator.fragments.font_size.SettingsPreviewFragment;

public class FontSizePreviewPagerAdapter extends FragmentPagerAdapter {

    private static final int[] tab_titles = {R.string.firstTab, R.string.secondTab, R.string.thirdTab};
    private Context tab_context;

    public FontSizePreviewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        tab_context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return new CalculatorPreviewFragment();

            case 1:
                return new FormulasPreviewFragment();

            case 2:
                return new ConverterPreviewFragment();

            case 3:
                return new SettingsPreviewFragment();

            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab_context.getResources().getString(tab_titles[position]);
    }

    @Override
    public int getCount() { return 4; }
}