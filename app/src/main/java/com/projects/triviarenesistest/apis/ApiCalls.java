package com.projects.triviarenesistest.apis;

import android.content.Context;

import com.projects.triviarenesistest.Utills.Constants;
import com.projects.triviarenesistest.models.QuestionsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCalls {

    private static ApiCalls instance;
    private Context context;

    private ApiCalls(Context context) {
        if (this.context == null)
            this.context = context;
    }

    public static ApiCalls getInstance(Context context) {
        if (instance == null) {
            instance = new ApiCalls(context);
        }
        return instance;
    }
    public void FetchQuestions( int amount, int category, String difficulty, String type, final NetworkResponseListener iNetworkResponseListener) {

        Call<QuestionsResponse> call = ApiService.getGenerixMISService(context).getQuestions(amount,category,difficulty,type);
        call.enqueue(new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(Call<QuestionsResponse> call, Response<QuestionsResponse> response) {
                if (response.body() != null) {
                    iNetworkResponseListener.onNetworkResponse(response.code(), response.body().getResponseCode(), response.message(), Constants.QUESTIONS, response.body());
                } else {
                    iNetworkResponseListener.onNetworkResponse(404,response.body().getResponseCode(),response.message(), Constants.QUESTIONS, null);
                }
            }

            @Override
            public void onFailure(Call<QuestionsResponse> call, Throwable t) {
                iNetworkResponseListener.onNetworkResponse(404,404,t.getMessage(),  Constants.QUESTIONS, null);
                t.printStackTrace();
            }


        });
    }


}
