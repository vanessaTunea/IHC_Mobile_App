package com.home.ihc_androidv2;

//import static com.home.ihc_androidv2.MainActivity.KEY_SYS_CONFIGURED;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class Register extends AppCompatActivity {
    String nume_utilizator, parola,serial_no,REQUEST_LOGIN;
   // String KEY_SYS_CONFIGURED_REGISTER= KEY_SYS_CONFIGURED;
    String LOGIN = "http://192.168.1.179/";
    String SERVERSTATUS = "http://192.168.1.179/SERVERSTATUS";

    String response_OK = "http://192.168.1.179/1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_register);

        Button button = findViewById(R.id.button);
        TextView user = findViewById(R.id.textView_SN);
        RequestQueue queue = Volley.newRequestQueue(this); // PENTRU REQUEST HTTP SI RASPUNS


//Trimite catre Arduino 192.168.1.179/user_
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                serial_no = user.getEditableText().toString();
                //String req_token="http://192.168.1.179/"+serial_no;
                String req_token="http://192.168.1.179/IHC1100110125RO-PDTMSJ";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, req_token,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();

                                Intent CreateLogin = new Intent(Register.this, CreateLogin.class);
                                startActivity(CreateLogin);
                                finish();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Se primeste raspunsul (tokenul) de la server

                        String error1=String.valueOf(error);
                        error1=error1.substring(0);

                        if (error instanceof NoConnectionError ||
                                error instanceof TimeoutError ||
                                error instanceof NetworkError) {
                            Toast toast = Toast.makeText(getApplicationContext(), " !!!Server DOWN!!! ", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0); // Poziționează în centru
                            toast.show();
                        }

                    }});queue.add(stringRequest);
            }
        });

    }
}