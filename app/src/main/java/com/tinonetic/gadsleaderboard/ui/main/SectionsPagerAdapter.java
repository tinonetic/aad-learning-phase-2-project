package com.tinonetic.gadsleaderboard.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tinonetic.gadsleaderboard.R;
import com.tinonetic.gadsleaderboard.ui.main.learning.LearningLeadersFragment;
import com.tinonetic.gadsleaderboard.ui.main.skilliq.SkillIqLeadersFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_learning_leaders, R.string.tab_text_skill_iq_leaders};
    private final Context mContext;
    private final int LEARNING_LEADERS_TAB = 0;
    private final int SKILLS_IQ_LEADERS_TAB = 1;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case LEARNING_LEADERS_TAB:
                return LearningLeadersFragment.newInstance();
            case SKILLS_IQ_LEADERS_TAB:
                return SkillIqLeadersFragment.newInstance();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}