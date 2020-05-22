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
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.tec.tecbox.data.Client;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        String joe = "NOBODY";
        String key = "";
        String url = "https://192.168.137.155:45455/";

        Retrofit r = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(Client.getUnsafeOkHttpClient())
                .build();

        AuthInterface i = r.create(AuthInterface.class);

        AuthHandler h = new AuthHandler(2, Integer.parseInt(username.trim()), password);

        Call<AuthHandler> call = i.makeAuth(h);

        try {
            AuthHandler response = call.execute().body();
            key = response != null ? response.getKey() : "";
            joe = response != null ? response.getNombre() : "NOBODY";

        } catch (IOException e) {
           return new Result.Error(new IOException("No se pudo conectar", e));
        }

        return new Result.Success<>(new LoggedInUser(key, joe));
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
