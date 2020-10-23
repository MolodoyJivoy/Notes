package zakaz.zakaz.notes;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import zakaz.zakaz.notes.Data.Data;
import zakaz.zakaz.notes.OnBoarding.OnBoarding;

public class Walkthrough extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1900;

    private String[] pharase = {
            "Дойдя до конца, люди смеются над страхами, мучившими их вначале. (Пауло Коэльо)",
    "Пробуйте и терпите неудачу, но не прерывайте ваших стараний. (Стивен Каггва)",
    "Мы сами должны стать теми переменами, которые хотим видеть в мире. (Махатма Ганди)",
    "Там, где кончается саморазвитие, начинается диван. (Дэвид Аллен)"};

    TextView textWalk;
    TextView Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_walkthrough);
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
                Intent i = new Intent (Walkthrough.this, OnBoarding.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void getTime() {
        DateFormat df = new SimpleDateFormat("d MMM yyyy, EEE, HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());
        Date.setText(date);
    }
}