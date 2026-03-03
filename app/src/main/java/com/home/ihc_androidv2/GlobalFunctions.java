package com.home.ihc_androidv2;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class GlobalFunctions extends AppCompatActivity {

    RequestQueue queue = Volley.newRequestQueue(this); // PENTRU REQUEST HTTP SI RASPUNS


    public static void createAcc(String numeUtilizator, String parola, String addAccount,  CreateLogin createLogin, Class<Login> loginClass) {
    }

//    public void createAcc(String nume_utilizator, String parola, String add_account, RequestQueue queue ,Context context, Class<?> activitateTinta){
//
//            StringRequest getRequest = new StringRequest(Request.Method.GET, add_account,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    //errorview.setText("Răspuns: " + response);
//                    //Toast.makeText(CreateLogin.this, "Cont creat cu succes!", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(context, activitateTinta);
//                    startActivity(intent);
//                }
//            },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    //errorview.setText("Eroare: " + error.toString());
//                    Toast.makeText(context, "Eroare la crearea contului!", Toast.LENGTH_LONG).show();
//                }
//            }
//    ) {
//        @Override
//        public Map<String, String> getHeaders() throws AuthFailureError {
//            Map<String, String> headers = new HashMap<>();
//            headers.put("Content-Type", "text/plain"); // header personalizat
//            return headers;
//        }
//    };
//
//
//                    queue.add(getRequest);
    }//FINISH createAcc




