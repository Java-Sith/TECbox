package com.tec.tecbox.data.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthInterface {

    @POST("autenticacion")
    Call<AuthHandler> makeAuth(@Body AuthHandler auth);

}
