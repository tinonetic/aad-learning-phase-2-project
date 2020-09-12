package com.tinonetic.gadsleaderboard.ui.main.submission;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.tinonetic.gadsleaderboard.R;
import com.tinonetic.gadsleaderboard.networking.ProjectSubmissionService;
import com.tinonetic.gadsleaderboard.networking.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitProjectActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    // TODO Fix custom Toolbar issue private Toolbar toolbar;
    private Button mOpenSubmitProjectButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_project);
        // TODO Fix custom Toolbar issue toolbar=findViewById(R.id.submit_form_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText firstName = (EditText) findViewById(R.id.text_first_name);
        final EditText lastName = (EditText) findViewById(R.id.text_last_name);
        final EditText email = (EditText) findViewById(R.id.text_email);
        final EditText githubUrl = (EditText) findViewById(R.id.text_github_url);

        // register submit button
        Object btn = findViewById(R.id.button_submit_project);
        Log.d(TAG, "Button Type" + btn != null ? btn.toString() : "IS NULL");
        mOpenSubmitProjectButton = (Button) findViewById(R.id.button_submit_project);
        mOpenSubmitProjectButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ProjectDetail projectDetail = new ProjectDetail();
                projectDetail.setFirstName(firstName.getText().toString());
                projectDetail.setLastName(lastName.getText().toString());
                projectDetail.setEmail(email.getText().toString());
                projectDetail.setGitHubUrl(githubUrl.getText().toString());

                submitProject(projectDetail);
            }
        });


    }




    private void submitProject(ProjectDetail projectDetail) {
        Log.d(TAG, "START submitProject()");
        // build Retrofit service that connects to Google form
        ProjectSubmissionService projectSubmissionService = ServiceBuilder.buildService(ProjectSubmissionService.class);
        Call<String> call = projectSubmissionService.submitProject(
                projectDetail.getEmail(),
                projectDetail.getFirstName(),
                projectDetail.getLastName(),
                projectDetail.getGitHubUrl()
        );

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Log.i(TAG,"SUCCESS submitting Project. Response code:" + response.code());
                    Log.i(TAG,"Full response:" + response.message());
                }
                else{
                    Log.i(TAG,"ERROR submitting Project. Response code:" + response.code());
                    Log.i(TAG,"Full response:" + response.message());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i(TAG,"EXCEPTION Communicating with API: "+ t.getMessage());
            }
        });

        Log.d(TAG, "FINISH submitProject()");
    }
}