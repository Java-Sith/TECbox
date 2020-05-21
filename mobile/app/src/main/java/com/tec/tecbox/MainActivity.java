package com.tec.tecbox;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tec.tecbox.ui.login.LoginActivity;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    EditText ip_address;
    Button ip_check;
    Thread connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip_address = findViewById(R.id.ip_address);
        ip_check = findViewById(R.id.check_ip);

    }

    public void checkIP(View view){

        if (ip_address.getText().toString().trim().length() > 0){
            connection = new Thread(new Connection(ip_address.getText().toString().trim()));
            connection.start();
            view.setEnabled(false);
            Handler hand = new Handler();
            hand.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ip_check.setEnabled(true);
                }
            }, 7000);
        } else {
            Toast.makeText(this, "No ingresó dirección IP", Toast.LENGTH_LONG).show();
        }
    }

    class Connection implements Runnable {

        String url = "";

        Connection(String url) {
            this.url = url;
        }


        @Override
        public void run() {
            try {

                InetAddress address = InetAddress.getByName(url);

                if (address.isReachable(5000)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String c = "CONEXIÓN EXITOSA CON " + url;
                            Toast.makeText(MainActivity.this, c, Toast.LENGTH_LONG).show();
                            finish();
                            Intent i = new Intent(MainActivity.this, LoginActivity.class);
                            i.putExtra("url", "https://" + url + "/");
                            MainActivity.this.startActivity(i);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "SERVIDOR NO DISPONIBLE", Toast.LENGTH_LONG).show();
                            ip_check.setEnabled(true);
                        }
                    });
                }

            }  catch (UnknownHostException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "ERROR, REVISE DIRECCIÓN IP", Toast.LENGTH_LONG).show();
                        ip_check.setEnabled(true);
                    }
                });
                e.printStackTrace();

            } catch (IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "ERROR DE CONEXIÓN, REVISE SU CONEXIÓN A INTERNET", Toast.LENGTH_LONG).show();
                        ip_check.setEnabled(true);
                    }
                });
                e.printStackTrace();
            }
        }
    }
}
