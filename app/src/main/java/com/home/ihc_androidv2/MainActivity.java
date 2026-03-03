package com.home.ihc_androidv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefs";
    public static final String KEY_SYS_CONFIGURED = String.valueOf(Globals.systemConfigured);



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_main);

        SharedPreferences prefs= getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        //OK => boolean sysConfigured = prefs.getBoolean(KEY_SYS_CONFIGURED, false);

        //Sterge-l pe cel de jos cand vrei sa testezi Register
        boolean sysConfigured = true;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //  boolean sysConfigured = Globals.KEY_SYS_CONFIGURED;
                if (sysConfigured==false) {
                    // Prima pornire
                    Intent Register = new Intent(MainActivity.this, Register.class);
                    startActivity(Register);
                    finish();

                } else if (sysConfigured) {
//            // Ulterior
//            Intent Login = new Intent(MainActivity.this, Login.class);
//            startActivity(Login);
                    Intent intent = new Intent(MainActivity.this, Control.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 2500);   //END HANDLER



    }




}    //MAIN ACTIVITY CLASS END