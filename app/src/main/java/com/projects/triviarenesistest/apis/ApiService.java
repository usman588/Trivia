package com.projects.triviarenesistest.apis;

import android.content.Context;

import com.projects.triviarenesistest.models.QuestionsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiService {

    private static GenerixMISService generixMISService;

    static GenerixMISService getGenerixMISService(Context context) {


        if (generixMISService == null) {
            generixMISService = ApiClient
                    .getApiClient(context)
                    .create(GenerixMISService.class);
        }
        return generixMISService;


    }

    public interface GenerixMISService {

        @GET("api.php")
        Call<QuestionsResponse> getQuestions(@Query("amount") int amount, @Query("category") int category, @Query("difficulty") String difficulty, @Query("type") String type);


    }

}
