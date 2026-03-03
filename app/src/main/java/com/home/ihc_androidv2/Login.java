package com.home.ihc_androidv2;

import static com.home.ihc_androidv2.MainActivity.PREFS_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    String nume_utilizator,parola;
    boolean nume_utilizator_filled, parola_filled;
    boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //            // Setează sysConfigured = true
        SharedPreferences prefs= getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(MainActivity.KEY_SYS_CONFIGURED, true);
        editor.apply(); // salvează modificările

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        Button buton_login=findViewById(R.id.buttonLogin);
        ImageButton buton_show_pass=findViewById(R.id.button_show_password);
        EditText username=findViewById(R.id.textView_name);
        EditText password=findViewById(R.id.textView_password);


        buton_show_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });


        buton_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                nume_utilizator = username.getEditableText().toString();
                parola = password.getEditableText().toString();

                sendCredentials();

            }  //End of onClick()
        });
    } //End of OnCreate()


// Start functions
    public void togglePasswordVisibility() {
        EditText textView_password = findViewById(R.id.textView_password);
        if (isPasswordVisible) {
            // If password is visible, hide it by setting the input type to textPassword
            textView_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            isPasswordVisible = false;
        } else {
            // If password is hidden, show it by setting the input type to text
            textView_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            isPasswordVisible = true;
        }
        // Move the cursor to the end of the text to maintain the current position
        textView_password.setSelection(textView_password.getText().length());
    }




    public void sendCredentials(){

        if(nume_utilizator.isEmpty()){
            Toast.makeText(Login.this, "  Nu ai introdus nimic!  ", Toast.LENGTH_LONG).show();
            nume_utilizator_filled=false;
        }else if((nume_utilizator.length()<4)||(nume_utilizator.length()>12)){
            Toast.makeText(Login.this, "  Ai introdus greșit!  ", Toast.LENGTH_LONG).show();
            nume_utilizator_filled=false;
        }else{
            //Utilizator OK -> Intre 4 si 12 caractere inclusiv
            nume_utilizator_filled=true;    //NUME UTILIZATOR OK
        }


        if(parola.isEmpty()){
            // Toast.makeText(Login.this, "Introduceti o parola!", Toast.LENGTH_LONG).show();
            Toast toast = Toast.makeText(getApplicationContext(), "  Nu ai introdus nimic!  ", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0); // Poziționează în centru
            toast.show();
            parola_filled=false;
        }else if((parola.length()<4)||(parola.length()>12)){
            // Toast.makeText(Login.this, "Parola trebuie sa aiba intre 4 si 12 caractere!", Toast.LENGTH_SHORT).show();
            Toast toast = Toast.makeText(getApplicationContext(), "  Ai introdus greșit!  ", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0); // Poziționează în centru
            toast.show();
            parola_filled=false;
        }else if (parola.endsWith("$")){
            //Toast.makeText(Login.this, "Parola nu se poate incheia cu $", Toast.LENGTH_SHORT).show();
            Toast toast = Toast.makeText(getApplicationContext(), "  Ai introdus greșit!  ", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0); // Poziționează în centru
            toast.show();
            parola_filled=false;
        }else{
            //Parola OK -> Intre 4 si 12 caractere inclusiv, nu se incheie cu $
            parola_filled=true;    //PAROLA OK

        }

    //VERIFICARE CREDENTIALE OK
        if((nume_utilizator_filled==true)&&(parola_filled==true)){
            RequestQueue queue = Volley.newRequestQueue(this); // PENTRU REQUEST HTTP SI RASPUNS
            String req_login = "http://192.168.1.179/"+nume_utilizator+parola+"$";

            StringRequest getRequest = new StringRequest(Request.Method.GET, req_login,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Login.this, "textResponse"+response, Toast.LENGTH_LONG).show();
                            // Raspuns 1 -> OK , treci in Control
                            if(response.endsWith("1")){
                                Toast.makeText(Login.this, response +"->expect 1", Toast.LENGTH_LONG).show();
                                Intent Control = new Intent(Login.this, Control.class);
                                startActivity(Control);
                                finish();
                            }
                            else if(response.endsWith("0")){
                                Toast.makeText(Login.this, response +"->expect 0", Toast.LENGTH_LONG).show();
//                                //PAROLA GRESITA
//                                Toast toast = Toast.makeText(getApplicationContext(), "  Parola gresita!  ", Toast.LENGTH_LONG);
//                                toast.setGravity(Gravity.CENTER, 0, 0); // Poziționează în centru
//                                toast.show();

                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            String error1=String.valueOf(error);
                            Toast.makeText(Login.this, "ERROR"+error1, Toast.LENGTH_LONG).show();

                            if (error instanceof NoConnectionError ||
                                    error instanceof TimeoutError ||
                                    error instanceof NetworkError) {
                                Toast toast = Toast.makeText(getApplicationContext(), " !!! Server DOWN !!! ", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0); // Poziționează în centru
                                toast.show();
                            }
                        }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "text/plain");
                    headers.put("Connection", "close");// header personalizat
                    //headers.put("", "\r\n"); -> nu merge -> Illegal Argument Exception ..
                    return headers;
                }
            };
            queue.add(getRequest);

        }
    }


}  //End of Class
