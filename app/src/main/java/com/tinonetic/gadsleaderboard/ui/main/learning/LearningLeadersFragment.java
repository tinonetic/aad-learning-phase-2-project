package com.tinonetic.gadsleaderboard.ui.main.learning;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tinonetic.gadsleaderboard.R;

public class LearningLeadersFragment extends Fragment {

    private LearningLeadersViewModel mViewModel;

    public static LearningLeadersFragment newInstance() {
        return new LearningLeadersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_learning_leaders, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LearningLeadersViewModel.class);
        // TODO: Use the ViewModel
    }

}