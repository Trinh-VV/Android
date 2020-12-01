package com.trinh.japanese.common;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.trinh.japanese.view.fragment.M001GameFrament;
import com.trinh.japanese.view.fragment.M002MimiN3Frament;
import com.trinh.japanese.view.fragment.M003FlashFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public static final int TAB1 = 0;
    public static final int TAB2 = 1;
    public static final int TAB3 = 2;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB1:
                return new M001GameFrament();
            case TAB2:
                return new M002MimiN3Frament();
            case TAB3:
                return new M003FlashFragment();
            default:
                return new M001GameFrament();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
