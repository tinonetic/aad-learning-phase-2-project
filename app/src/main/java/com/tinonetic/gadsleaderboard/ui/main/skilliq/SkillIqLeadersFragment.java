package com.tinonetic.gadsleaderboard.ui.main.skilliq;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tinonetic.gadsleaderboard.R;

/**
 * Responsible for displaying the Skill IQ Leaders
 */
public class SkillIqLeadersFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private SkillIqLeadersViewModel mViewModel;

    public static SkillIqLeadersFragment newInstance() {
        return new SkillIqLeadersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SkillIqLeadersViewModel.class);
        // TODO: Use the ViewModel
        //getView().
    }

}