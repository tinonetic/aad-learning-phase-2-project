package com.tinonetic.gadsleaderboard.ui.main.learning;

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
import com.tinonetic.gadsleaderboard.api.ApiClient;
import com.tinonetic.gadsleaderboard.model.LearningLeader;

import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;

/*
 * Responsible for displaying the Learning Leaders
 **/
public class LearningLeadersFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private LearningLeadersViewModel mViewModel;
    private ProgressBar mLoadingProgress;
    private RecyclerView mRecyclerLearningLeaders;

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

        mLoadingProgress = (ProgressBar) getView().findViewById(R.id.progress_bar_loading);
        mRecyclerLearningLeaders = (RecyclerView) getView().findViewById(R.id.recycler_learning_leaders);
        LinearLayoutManager  learningLeadersLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerLearningLeaders.setLayoutManager(learningLeadersLayoutManager);
        try {
            URL learnersUrl = ApiClient.buildUrl("api/hours");
            // fetch and assign results to view
            new LearningLeadersApiRequestTask(this).execute(learnersUrl);

        }
        catch  (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    /*
    * Performs the background fetching of the LearningLeaders data from the API
    **/
    public class LearningLeadersApiRequestTask extends AsyncTask<URL,Void, String> {
        // TODO: Review. Weak reference prevents memory leak by allowing its garbage collecion
        private WeakReference<LearningLeadersFragment> mFragmentReference;

        public LearningLeadersApiRequestTask(LearningLeadersFragment fragmentReference) {
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
                mRecyclerLearningLeaders.setVisibility(View.INVISIBLE);
                // TODO: enhance error display
                textViewLoadingError.setVisibility(View.VISIBLE);
            }
            else{
                mRecyclerLearningLeaders.setVisibility(View.VISIBLE);
                textViewLoadingError.setVisibility(View.INVISIBLE);
            }

            ArrayList<LearningLeader> learningLeaders = ApiClient.getLearningLeaderFromJson(result);
            String resultString = "";
            StringBuilder stringBuilder = new StringBuilder();

            LearningLeadersAdapter adapter = new LearningLeadersAdapter(mFragmentReference.get().getContext(),learningLeaders);
            mRecyclerLearningLeaders.setAdapter(adapter);
        }
    }
}