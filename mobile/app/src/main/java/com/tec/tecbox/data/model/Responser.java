package com.tec.tecbox.data.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Responser {

    @POST("api/repartidor")
    Call<ResponserHandler> setPaqState(@Body ResponserHandler state);
}
