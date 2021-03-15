package com.projects.triviarenesistest.apis;

public interface NetworkResponseListener {
    void onNetworkResponse(int status, int responseCode, String message, String responseForRequest,  Object body);

}
