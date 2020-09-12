package com.tinonetic.gadsleaderboard.networking;

import com.tinonetic.gadsleaderboard.ui.main.submission.ProjectDetail;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ProjectSubmissionService {

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    //@Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    Call<String> submitProject(
            @Field("entry.1824927963") String email,
            @Field("entry.1877115667") String firstName,
            @Field("entry.2006916086") String lastName,
            @Field(" ntry.284483984") String gitHubUrl
            );
}
