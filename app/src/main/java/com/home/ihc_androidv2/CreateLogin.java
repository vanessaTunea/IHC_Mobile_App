package com.home.ihc_androidv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreateLogin extends AppCompatActivity {

    String nume_utilizator,parola,parola2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       // RequestQueue queue = Volley.newRequestQueue(this); // PENTRU REQUEST HTTP SI RASPUNS

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_create_login);

        Button buton_set_cred=findViewById(R.id.btn_create_acc);
        EditText username=findViewById(R.id.username);
        EditText password=findViewById(R.id.password);
        EditText password2=findViewById(R.id.password2);

        TextView errorview=findViewById(R.id.textView3);

//        username.setOnFocusChangeListener((v, hasFocus) -> {
//            if (hasFocus && username.getText().toString().equals("Utilizator")) {
//                username.setText("");
//            }
//        });

//        password.setOnFocusChangeListener((v, hasFocus) -> {
//            if (hasFocus && username.getText().toString().equals("Parola")) {
//                username.setText("");
//            }
//        });




        buton_set_cred.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                nume_utilizator = username.getEditableText().toString();
                parola = password.getEditableText().toString();
                parola2 = password2.getEditableText().toString();

                boolean nume_utilizator_filled, parola_filled, parola2_filled;


                if(nume_utilizator.isEmpty()){
                    Toast.makeText(CreateLogin.this, "Introduceti un utilizator!", Toast.LENGTH_LONG).show();
                    nume_utilizator_filled=false;
                }else if((nume_utilizator.length()<4)||(nume_utilizator.length()>12)){
                    Toast.makeText(CreateLogin.this, "Numele utilizatorului trebuie sa aiba intre 4 si 12 caractere!", Toast.LENGTH_LONG).show();
                    nume_utilizator_filled=false;
                }else{
                    //Utilizator OK -> Intre 4 si 12 caractere inclusiv
                    nume_utilizator_filled=true;
                }


                if(parola.isEmpty()){
                   // Toast.makeText(CreateLogin.this, "Introduceti o parola!", Toast.LENGTH_LONG).show();
                    Toast toast = Toast.makeText(getApplicationContext(), "  Introduceți o parolă!  ", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0); // Poziționează în centru
                    toast.show();
                    parola_filled=false;
                }else if((parola.length()<4)||(parola.length()>12)){
                   // Toast.makeText(CreateLogin.this, "Parola trebuie sa aiba intre 4 si 12 caractere!", Toast.LENGTH_SHORT).show();
                    Toast toast = Toast.makeText(getApplicationContext(), "  Parola trebuie să aibă între 4 și 12 caractere!  ", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0); // Poziționează în centru
                    toast.show();
                    parola_filled=false;
                }else if (parola.endsWith("$")){
                    //Toast.makeText(CreateLogin.this, "Parola nu se poate incheia cu $", Toast.LENGTH_SHORT).show();
                    Toast toast = Toast.makeText(getApplicationContext(), "  Parola nu se poate încheia cu $!  ", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0); // Poziționează în centru
                    toast.show();
                    parola_filled=false;
                }else{
                    //Parola OK -> Intre 4 si 12 caractere inclusiv, nu se incheie cu $
                    parola_filled=true;
                }

                if(!Objects.equals(parola2, parola)){
                    //Toast.makeText(CreateLogin.this, "Parolele nu se potrivesc!", Toast.LENGTH_SHORT).show();
                    Toast toast = Toast.makeText(getApplicationContext(), "  Parolele nu se potrivesc!  ", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0); // Poziționează în centru
                    toast.show();
                    parola2_filled=false;
                }else{
                    //Parola OK -> Intre 4 si 12 caractere inclusiv, nu se incheie cu $
                    parola2_filled=true;
                }

                if((nume_utilizator_filled==true)&&(parola_filled==true)&&(parola2_filled==true)){
                    addAccount();
                }
            };
        });
    }


    public void addAccount(){
        RequestQueue queue = Volley.newRequestQueue(this); // PENTRU REQUEST HTTP SI RASPUNS
        String add_account="http://192.168.1.179/REG"+nume_utilizator+parola+"$";

        StringRequest getRequest = new StringRequest(Request.Method.GET, add_account,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Raspuns OK -> cont creat cu succes, treci in Login
                        Toast.makeText(CreateLogin.this, response, Toast.LENGTH_LONG).show();
                        Toast toast = Toast.makeText(getApplicationContext(), "  Contul a fost creat cu succes!  ", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0); // Poziționează în centru
                        toast.show();
                        Intent Login = new Intent(CreateLogin.this, Login.class);
                        startActivity(Login);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NoConnectionError ||
                                error instanceof TimeoutError ||
                                error instanceof NetworkError) {
                            Toast toast = Toast.makeText(getApplicationContext(), " !!!Server DOWN!!! ", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0); // Poziționează în centru
                            toast.show();
                        }
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "text/plain"); // header personalizat
                return headers;
            }
        };
        queue.add(getRequest);
    }





}    //End of Class