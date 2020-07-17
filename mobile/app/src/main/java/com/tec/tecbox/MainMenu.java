package com.tec.tecbox;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tec.tecbox.data.Client;
import com.tec.tecbox.data.model.JsonInterface;
import com.tec.tecbox.data.model.JsonPlaceHolder;
import com.tec.tecbox.data.model.Responser;
import com.tec.tecbox.data.model.ResponserHandler;
import com.tec.tecbox.ui.login.LoginActivity;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainMenu extends AppCompatActivity {

    Button entregado;
    Button fallido;
    Button devuelto;
    EditText tracker;

    private static Retrofit retrofit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        entregado = findViewById(R.id.bt_entregado);
        fallido = findViewById(R.id.bt_fallido);
        devuelto = findViewById(R.id.bt_sucursal);
        tracker = findViewById(R.id.et_trackerID);


        entregado.setOnClickListener(v -> {
            connectApi(6);
        });

        fallido.setOnClickListener(v -> {
            connectApi(7);
        });

        devuelto.setOnClickListener(v -> {
            connectApi(8);
        });
    }

    private void connectApi(int state) {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://" + LoginActivity.ip + "/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(Client.getUnsafeOkHttpClient())
                    .build();
        }

        Responser json = retrofit.create(Responser.class);

        ResponserHandler r = new ResponserHandler(Integer.parseInt(tracker.getText().toString().trim()), Integer.parseInt(Objects.requireNonNull(MainMenu
                .this
                .getIntent()
                .getStringExtra("cedula"))), state);

        Call<ResponserHandler> call = json.setPaqState(r);

        call.enqueue(new Callback<ResponserHandler>() {
            @Override
            public void onResponse(Call<ResponserHandler> call, Response<ResponserHandler> response) {
                Toast.makeText(MainMenu.this, response.body().getResult(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResponserHandler> call, Throwable t) {
                Toast.makeText(MainMenu.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}
