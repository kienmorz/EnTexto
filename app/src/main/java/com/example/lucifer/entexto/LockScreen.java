package com.example.lucifer.entexto;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.andrognito.patternlockview.utils.PatternLockUtils;

public class LockScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                SharedPreferences preferences = getSharedPreferences("PREFS",0);
                String password = preferences.getString("password","0");
                if(password.equals("0")){
                    Intent intent = new Intent(getApplicationContext(),CreatePasswordActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{

                        Intent intent = new Intent(getApplicationContext(), InputPasswordActivity
                                .class);
                        startActivity(intent);
                        finish();
                    }
                    }


        },100);
    }

}
