package com.tinonetic.gadsleaderboard.networking;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String SUBMISSION_BASE_URL = "https://docs.google.com/forms/d/e/";

    // Create Logger interceptor
    private static HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    // Create OkHttp client and register logger above
    private static OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder().addInterceptor(logger);

    // Build the service
    // Gson convertor allows Retrofit to automatically handle mappings
    // special okHttp client above registered to allow for logging
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(SUBMISSION_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    private static Retrofit retrofit = builder.build();

    // Used to automatically generate service classes given Retrofit interfaces
    public static<S> S buildService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }

}
