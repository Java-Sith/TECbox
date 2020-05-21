package com.tec.tecbox.data.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonInterface {

    @GET("todos/1")
    Call<JsonPlaceHolder> getInfo();
}
