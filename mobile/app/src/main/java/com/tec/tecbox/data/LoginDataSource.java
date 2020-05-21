package com.tec.tecbox.data;

import android.util.Log;
import android.widget.Toast;

import com.tec.tecbox.data.model.AuthHandler;
import com.tec.tecbox.data.model.AuthInterface;
import com.tec.tecbox.data.model.JsonInterface;
import com.tec.tecbox.data.model.JsonPlaceHolder;
import com.tec.tecbox.data.model.LoggedInUser;
import com.tec.tecbox.ui.login.LoginActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        /*Retrofit r = new Retrofit.Builder()
                .baseUrl(LoginActivity.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AuthInterface i = r.create(AuthInterface.class);

        AuthHandler h = new AuthHandler();
        h.setCedula(402410587);
        h.setPass("fioahbwfb34");
        h.setType(1);

        Call<AuthHandler> call = i.makeAuth(h);

        call.enqueue(new Callback<AuthHandler>() {
            @Override
            public void onResponse(Call<AuthHandler> call, Response<AuthHandler> response) {
                Log.d("TESTING SHIT", response.message());
            }

            @Override
            public void onFailure(Call<AuthHandler> call, Throwable t) {
                Log.d("TESTING SHIT", t.toString());
            }
        });*/

        Retrofit retrofit = null;

        retrofit = new Retrofit.Builder()
                .baseUrl("https://" + password + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonInterface json = retrofit.create(JsonInterface.class);

        Call<JsonPlaceHolder> call = json.getInfo();

        final String[] joe = {"NOBODY"};

        call.enqueue(new Callback<JsonPlaceHolder>() {
            @Override
            public void onResponse(Call<JsonPlaceHolder> call, Response<JsonPlaceHolder> response) {
                joe[0] = response.message();
            }

            @Override
            public void onFailure(Call<JsonPlaceHolder> call, Throwable t) {
                joe[0] = t.toString();
            }
        });

        return new Result.Success<>(new LoggedInUser("fayfkubwf", joe[0]));
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
