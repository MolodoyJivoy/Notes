package my.all.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import my.all.notes.Data.Data;
import my.all.notes.OnBoarding.OnBoarding;
import my.all.notes.Util.ConfigSettings;

public class Walkthrough extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1900;

    SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "settingsStart";
    public static final String APP_PREFERENCES_NAME = "SettingStart";
    private boolean first_Start = false;

    private String[] pharase;

    TextView textWalk;
    TextView Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_walkthrough);
        pharase = getResources().getStringArray(R.array.phrases);
        textWalk = findViewById(R.id.TextWalkthrough);
        Date = findViewById(R.id.DateWalkthrough);
        getTime();
        Data.getSettings(this);
        int value = (int) (Math.random() * pharase.length);
        textWalk.setText(pharase[value]);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                startWithoutBoarding();
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void getTime() {
        DateFormat df = new SimpleDateFormat("d MMM yyyy, EEE, HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());
        Date.setText(date);
    }

    private void startWithoutBoarding() {
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if(mSettings.contains(APP_PREFERENCES_NAME)) {
            first_Start = mSettings.getBoolean(APP_PREFERENCES_NAME, false);
            ConfigSettings.FisrtStart = first_Start;
        }

        if (first_Start){
            Intent intent = new Intent(Walkthrough.this, MainAllAllNotes.class);
            startActivity(intent);
        }else {
            Intent i = new Intent (Walkthrough.this, OnBoarding.class);
            startActivity(i);
        }
    }
}