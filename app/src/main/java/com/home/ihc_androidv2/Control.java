package com.home.ihc_androidv2;

import static android.app.ProgressDialog.show;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.materialswitch.MaterialSwitch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Control extends AppCompatActivity {

    List<LinearLayout> layoutList;
    List<HorizontalScrollView> scrollViewList;
    private boolean isFromUser = true;
    private boolean isWaitingServerUpdate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //Scoate bara de navigare
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().hide();
//        }

        setContentView(R.layout.activity_control);


        ConstraintLayout middleConstraintLayout = findViewById(R.id.middleConstraintLayout);
        ConstraintLayout CL_mainControl = findViewById(R.id.CL_mainControl);
        ConstraintLayout lowerConstrainLayout = findViewById(R.id.lowerConstrainLayout);

        //---------------------------------------------------------------------------------TEST
        ConstraintSet set = new ConstraintSet();
        set.clone(CL_mainControl);
        //---------------------------------------------------------------------------------TEST


//        //Buton adauga dispozitiv
        //ImageButton btn_plus = findViewById(R.id.imgbtn_plus);

//        //Main ScrollView
        HorizontalScrollView scrollBar_toate = findViewById(R.id.scrollBar_toate);
        Button btn_main_toate = findViewById(R.id.btn_main_toate);
        Button btn_main_parter = findViewById(R.id.btn_main_parter);
        Button btn_main_etaj = findViewById(R.id.btn_main_etaj);
        Button btn_main_subsol = findViewById(R.id.btn_main_subsol);
        Button btn_main_exterior = findViewById(R.id.btn_main_exterior);

//      //Vertical ScrollView Toate
        ScrollView scroll_control = findViewById(R.id.scroll_control);    //DO NOT HIDE


//Exterior Scrollview
        HorizontalScrollView scrollView_exterior = findViewById(R.id.scrollView_exterior);
        Button btn_exterior_1 = findViewById(R.id.btn_exterior_1);
        Button btn_exterior_2 = findViewById(R.id.btn_exterior_2);
        Button btn_exterior_3 = findViewById(R.id.btn_exterior_3);


//        //Parter ScrollView
        HorizontalScrollView scrollView_parter = findViewById(R.id.scrollView_parter);
        Button btn_parter_1 = findViewById(R.id.btn_parter_1);
        Button btn_parter_2 = findViewById(R.id.btn_parter_2);
        Button btn_parter_3 = findViewById(R.id.btn_parter_3);


//1st LinearLayouts
        LinearLayout linearlayout_main = findViewById(R.id.linearlayout_CONTROL);  //Main LinearLayout - DO NOT HIDE
        LinearLayout linearlayout_toate1 = findViewById(R.id.linearlayout_toate1);
        LinearLayout linearlayout_toate2 = findViewById(R.id.linearlayout_toate2);
        LinearLayout linearlayout_toate3 = findViewById(R.id.linearlayout_toate3);
        LinearLayout linearlayout_toate4 = findViewById(R.id.linearlayout_toate4);
        LinearLayout linearlayout_toate5 = findViewById(R.id.linearlayout_toate5);
        LinearLayout linearlayout_toate6 = findViewById(R.id.linearlayout_toate6);
//        LinearLayout linearlayout_toate7 = findViewById(R.id.linearlayout_toate7);
//        LinearLayout linearlayout_toate8 = findViewById(R.id.linearlayout_toate8);
//        LinearLayout linearlayout_toate9 = findViewById(R.id.linearlayout_toate9);

        LinearLayout linearlayout_exterior1_1 = findViewById(R.id.linearlayout_exterior1_1);
        LinearLayout linearLayout_parter1_1 = findViewById(R.id.linearLayout_parter1_1);

//,linearlayout_toate7,linearlayout_toate8,linearlayout_toate9
        layoutList = Arrays.asList(linearlayout_toate1, linearlayout_toate2,linearlayout_toate3,linearlayout_toate4,linearlayout_toate5,linearlayout_toate6,linearlayout_exterior1_1, linearLayout_parter1_1);
        scrollViewList = Arrays.asList(scrollView_parter,scrollView_exterior);


//1st CardView
        MaterialCardView cv_1 = findViewById(R.id.cv_1);
        ConstraintLayout cl_1 = findViewById(R.id.cl_1);
        TextView textView_releu1 = findViewById(R.id.textView_releu1);
        ImageView imageView_releu1_on = findViewById(R.id.imageView_releu1_on);
        MaterialSwitch switch_releu1 = findViewById(R.id.switch_releu1);
        TextView txtOnline = findViewById(R.id.txtOnline);
        ImageView img_conectat = findViewById(R.id.img_conectat);

//2nd CardView
        MaterialCardView cv_2 = findViewById(R.id.cv_2);
        ConstraintLayout cl_2 = findViewById(R.id.cl_2);
        ImageView imageView_releu2_on = findViewById(R.id.imageView_releu2_on);
        MaterialSwitch switch_releu2 = findViewById(R.id.switch_releu2);
        TextView txtOnline2 = findViewById(R.id.txtOnline2);
        ImageView img_conectat2 = findViewById(R.id.img_conectat2);


//Set onClickListeners

//        //Buton adauga dispozitiv
//        btn_plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                hideViews();
//                //new Layout pentru a adauga dispozitivul
//            }
//        });

        //Buton toate
        btn_main_toate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideViews();
                hideScroll();

                set.connect(R.id.scroll_control, ConstraintSet.TOP, R.id.scrollBar_toate, ConstraintSet.BOTTOM,0);
                set.applyTo(CL_mainControl);

                linearlayout_toate1.setVisibility(View.VISIBLE);
                linearlayout_toate2.setVisibility(View.VISIBLE);
                linearlayout_toate3.setVisibility(View.VISIBLE);
                linearlayout_toate4.setVisibility(View.VISIBLE);
                linearlayout_toate5.setVisibility(View.VISIBLE);
                linearlayout_toate6.setVisibility(View.VISIBLE);

            }
        });

        //Buton Exterior
        btn_main_exterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideViews();
                hideScroll();

                set.connect(R.id.scrollBar_toate, ConstraintSet.BOTTOM, R.id.scrollView_exterior, ConstraintSet.TOP,0);
                set.connect(R.id.scroll_control, ConstraintSet.TOP, R.id.scrollView_exterior, ConstraintSet.BOTTOM,0);
                set.applyTo(CL_mainControl);

                scrollView_exterior.setVisibility(View.VISIBLE);
            }
        });
        btn_exterior_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideViews();
                hideScroll();
                scrollView_exterior.setVisibility(View.VISIBLE);
                linearlayout_exterior1_1.setVisibility(View.VISIBLE);
            }
        });

        //Buton Parter
        btn_main_parter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideViews();
                hideScroll();

                set.connect(R.id.scrollBar_toate, ConstraintSet.BOTTOM, R.id.scrollView_parter, ConstraintSet.TOP,0);
                set.connect(R.id.scroll_control, ConstraintSet.TOP, R.id.scrollView_parter, ConstraintSet.BOTTOM,0);
                set.applyTo(CL_mainControl);

                scrollView_parter.setVisibility(View.VISIBLE);
            }
        });

        btn_parter_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideViews();
                hideScroll();
                scrollView_parter.setVisibility(View.VISIBLE);
                linearLayout_parter1_1.setVisibility(View.VISIBLE);
            }
        });

    //ETAJ
    btn_main_etaj.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            hideViews();
            hideScroll();

        }
        });

    RequestQueue queue1 = Volley.newRequestQueue(this); // PENTRU REQUEST HTTP SI RASPUNS



            switch_releu1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!isFromUser) return;  // ignoră modificările programatice
                    // codul tău la schimbarea stării switch-ului
                    //Toast.makeText(Control.this, "Switch este " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show();
                    if (isChecked) {
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, Globals.RELEU_1_ON,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(Control.this, "RELEU_1_ON_response", Toast.LENGTH_LONG).show();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Control.this, "RELEU_1_ON_error", Toast.LENGTH_LONG).show();

                            }
                        });
                        queue1.add(stringRequest);
                    } else {
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, Globals.RELEU_1_OFF,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(Control.this, "RELEU_1_OFF_response", Toast.LENGTH_LONG).show();
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                String error1 = error.toString();
                                textView_releu1.setText(error1);

                            }
                        }){
                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> headers = new HashMap<>();
                                headers.put("Content-Type", "text/plain"); // header personalizat
                                headers.put("Connection", "close");
                                return headers;
                            }
                        };
                        queue1.add(stringRequest);
                    }
                }
            });


///Functie ce merge in loop
///Raspunde starea dispozitivelor sub forma de string . eg. 101101 -> 6 dispozitive conectate in stari diferite
        //String STATUS="http://109.101.228.231/STATUS";
        char status[] = new char[18];
        Timer c = new Timer();
        RequestQueue queue = Volley.newRequestQueue(this); // PENTRU REQUEST HTTP SI RASPUNS
        TimerTask cc = new TimerTask() {
            @Override
            public void run() {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, Globals.GET_DEVICES_STATUS,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Globals.charArray=response.toCharArray();
                                status[0]=Globals.charArray[0];
                                if((status[0]=='1') && (!switch_releu1.isChecked())){
                                    isFromUser = false;  // dezactivează temporar listener-ul
                                    switch_releu1.setChecked(true);              // schimbă starea
                                    isFromUser = true;  // reactivează listener-ul
                                }else if((status[0]=='0') && (switch_releu1.isChecked())){
                                    isFromUser = false;
                                    switch_releu1.setChecked(false);
                                    isFromUser = true;
                                }
                                //Toast.makeText(Control.this, response, Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String error1 =  error.toString();
                        Toast.makeText(Control.this,"error:"+error1, Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "text/plain"); // header personalizat
                        headers.put("Connection", "close");
                        return headers;
                    }
                };
                queue.add(stringRequest);
            }};c.schedule(cc,0,1000);  // F pentru loop - pune 300 cand termini



    }//End of OnCreate()


    public void hideViews(View... views) {
        for (LinearLayout layout : layoutList) {
            layout.setVisibility(View.GONE);
        }
    }
    public void hideScroll(View... views) {
        for(HorizontalScrollView scrollView : scrollViewList){
            scrollView.setVisibility(View.GONE);
        }
    }



}//End of Class