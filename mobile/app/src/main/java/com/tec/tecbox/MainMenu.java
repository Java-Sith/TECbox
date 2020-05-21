package com.tec.tecbox;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tec.tecbox.data.model.JsonInterface;
import com.tec.tecbox.data.model.JsonPlaceHolder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainMenu extends AppCompatActivity {

    Button search;
    Button entregado;
    Button fallido;
    Button devuelto;
    EditText tracker;
    TextView estado;
    String ip;

    private static Retrofit retrofit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        search = findViewById(R.id.bt_search);
        entregado = findViewById(R.id.bt_entregado);
        fallido = findViewById(R.id.bt_fallido);
        devuelto = findViewById(R.id.bt_sucursal);
        tracker = findViewById(R.id.et_trackerID);
        estado = findViewById(R.id.estadopaq);



        connectApi();
    }

    private void connectApi() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        JsonInterface json = retrofit.create(JsonInterface.class);

        Call<JsonPlaceHolder> call = json.getInfo();

        call.enqueue(new Callback<JsonPlaceHolder>() {
            @Override
            public void onResponse(Call<JsonPlaceHolder> call, Response<JsonPlaceHolder> response) {
                estado.setText(String.valueOf(response.body().isCompleted()));
            }

            @Override
            public void onFailure(Call<JsonPlaceHolder> call, Throwable t) {
                estado.setText(t.toString());
            }
        });

    }
}
