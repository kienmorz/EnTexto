package com.example.lucifer.entexto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

/**
 * Created by Lucifer on 3/21/2018.
 */

public class InputPasswordActivity extends AppCompatActivity{
    String password;
    PatternLockView mPatternLockView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_password);
        SharedPreferences preferences = getSharedPreferences("PREFS",0);
        password = preferences.getString("password","0");
        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                if(password.equals(PatternLockUtils.patternToString(mPatternLockView, pattern)))
                {
                    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
                else{
                    Toast.makeText(getApplicationContext(),"Wrong Pattern",Toast.LENGTH_LONG).show();
                    mPatternLockView.clearPattern();
                }


            }

            @Override
            public void onCleared() {

            }

        });
    }
}

