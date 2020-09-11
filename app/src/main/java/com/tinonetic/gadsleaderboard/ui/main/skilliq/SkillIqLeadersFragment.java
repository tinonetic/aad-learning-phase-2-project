package com.tinonetic.gadsleaderboard.ui.main.skilliq;

import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tinonetic.gadsleaderboard.R;
import com.tinonetic.gadsleaderboard.model.LearningLeader;
import com.tinonetic.gadsleaderboard.model.SkillIqLeader;
import com.tinonetic.gadsleaderboard.networking.ApiClient;
import com.tinonetic.gadsleaderboard.ui.main.learning.LearningLeadersAdapter;
import com.tinonetic.gadsleaderboard.ui.main.learning.LearningLeadersFragment;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;

/**
 * Responsible for displaying the Skill IQ Leaders
 */
public class SkillIqLeadersFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private SkillIqLeadersViewModel mViewModel;
    private ProgressBar mLoadingProgress;
    private RecyclerView mRecyclerSkillIqLeaders;

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

        mLoadingProgress = (ProgressBar) getView().findViewById(R.id.progress_bar_loading);
        mRecyclerSkillIqLeaders = (RecyclerView) getView().findViewById(R.id.recycler_skilliq_leaders);
        LinearLayoutManager skilliqLeadersLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerSkillIqLeaders.setLayoutManager(skilliqLeadersLayoutManager);
        try {
            URL learnersUrl = ApiClient.buildUrl("api/skilliq");
            // fetch and assign results to view
            new SkillIqLeadersFragment.SkillIqLeadersApiRequestTask(this).execute(learnersUrl);

        }
        catch  (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    /*
     * Performs the background fetching of the Skill IQ Leaders data from the API
     **/
    public class SkillIqLeadersApiRequestTask extends AsyncTask<URL,Void, String> {
        // TODO: Review. Weak reference prevents memory leak by allowing its garbage collecion
        private WeakReference<SkillIqLeadersFragment> mFragmentReference;

        public SkillIqLeadersApiRequestTask(SkillIqLeadersFragment fragmentReference) {
            this.mFragmentReference = new WeakReference<>(fragmentReference);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;
            try {
                result = ApiClient.getJson(searchURL);
                Log.d(TAG, "JSON: " +  result);
            } catch (IOException e) {
                Log.e(TAG,"Error getting JSON "+ e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textViewLoadingError = (TextView) getView().findViewById(R.id.text_error);

            mLoadingProgress.setVisibility(View.INVISIBLE);

            // TODO: Enhance handling of flight mode
            if(result == null){
                mRecyclerSkillIqLeaders.setVisibility(View.INVISIBLE);
                // TODO: enhance error display
                textViewLoadingError.setVisibility(View.VISIBLE);
            }
            else{
                mRecyclerSkillIqLeaders.setVisibility(View.VISIBLE);
                textViewLoadingError.setVisibility(View.INVISIBLE);
            }

            ArrayList<SkillIqLeader> skillIqLeaders = ApiClient.getSkillIqLeaderFromJson(result);

            SkillIqLeadersAdapter adapter = new SkillIqLeadersAdapter(mFragmentReference.get().getContext(),skillIqLeaders);
            mRecyclerSkillIqLeaders.setAdapter(adapter);
        }
    }

}