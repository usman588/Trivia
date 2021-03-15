package com.projects.triviarenesistest.apis;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.projects.triviarenesistest.BuildConfig;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient{

    private static Retrofit retrofitClient;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        public static synchronized Retrofit getApiClient(Context context){

            String apiEndPoint = "https://opentdb.com/";
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(1, TimeUnit.MINUTES);
            builder.connectTimeout(1, TimeUnit.MINUTES);
            builder.writeTimeout(1, TimeUnit.MINUTES);
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(interceptor);
            }
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofitClient = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(apiEndPoint)
                    .build();

            return retrofitClient;
        }
}