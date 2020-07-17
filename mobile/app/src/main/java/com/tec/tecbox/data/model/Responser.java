package com.tec.tecbox.data.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Responser {

    @POST("Repartidor")
    Call<ResponserHandler> setPaqState(@Body ResponserHandler state);
}
