package com.tinonetic.gadsleaderboard.ui.main.learning;

import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tinonetic.gadsleaderboard.R;
import com.tinonetic.gadsleaderboard.api.ApiClient;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;

/**
 * Responsible for displaying the Learning Leaders
 */
public class LearningLeadersFragment extends Fragment {

    private final String TAG = getClass().getSimpleName();
    private LearningLeadersViewModel mViewModel;
    private ProgressBar mLoadingProgress;

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

        try {
            URL learnersUrl = ApiClient.buildUrl("api/hours");
            // fetch and assign results to view
            new LearningLeadersApiRequestTask().execute(learnersUrl);

        }
        catch  (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    /*
    * Performs the background fetching of the LearningLeaders data from the API
    */
    public class LearningLeadersApiRequestTask extends AsyncTask<URL,Void, String> {

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
            TextView textViewApiResult = (TextView) getView().findViewById(R.id.text_learning_leaders);
            TextView textViewLoadingError = (TextView) getView().findViewById(R.id.text_error);

            mLoadingProgress.setVisibility(View.INVISIBLE);

            // TODO: Handle aeroplane mode
            if(result == null){
                textViewApiResult.setVisibility(View.INVISIBLE);
                textViewLoadingError.setVisibility(View.VISIBLE);
            }
            else{
                textViewApiResult.setVisibility(View.VISIBLE);
                textViewLoadingError.setVisibility(View.INVISIBLE);
            }
            textViewApiResult.setText(result);
        }
    }
}