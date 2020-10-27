package my.all.notes.OnBoarding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import my.all.notes.MainAllAllNotes;
import my.all.notes.R;
import my.all.notes.Util.ConfigSettings;

public class OnBoarding extends AppCompatActivity {

    private ViewPager viewPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabLayout;
    private Button getStarted;
    private int pos = 0;
    private Animation btnAnim;
    private boolean only_one = false;

    SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "settingsStart";
    public static final String APP_PREFERENCES_NAME = "SettingStart";
    private boolean first_Start = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //startWithoutBoarding();

        setContentView(R.layout.activity_on_boarding);
        viewPager = findViewById(R.id.viewPagerOnBoarding);
        tabLayout = findViewById(R.id.tab_indicator);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#131313"));

        getStarted = findViewById(R.id.getStarted);
        getStarted.setVisibility(View.INVISIBLE);
        btnAnim = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.button_animation);
        final List<ScreenItem> screenItems = new ArrayList<>();
        screenItems.add(new ScreenItem("Создавайте заметки", "Создавайте, удаляйте и редактируйте свои заметки. Чем больше Вы пишите, тем лучше результат", R.drawable.ic_main_icon_boarding));
        screenItems.add(new ScreenItem("Используйте два шаблона заметок", "Создавай как обычные заметки, так и заметки по особому шаблону. Совмещайте их вместе как Вам удобно.", R.drawable.ic_main_icon_boarding));
        screenItems.add(new ScreenItem("Добавляй теги", "Добавляй теги к заметкам по особому шаблону", R.drawable.ic_main_icon_boarding));
        //screenItems.add(new ScreenItem("", "Каждый день - это новая жизнь!»", R.drawable.ic_clipboard_boarding));
        introViewPagerAdapter = new IntroViewPagerAdapter(OnBoarding.this, screenItems);
        viewPager.setAdapter(introViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == screenItems.size() - 1 && !only_one){
                    loadLastScreen();
                    only_one = true;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnBoarding.this, MainAllAllNotes.class);
                startActivity(intent);
            }
        });

    }

    private void startWithoutBoarding() {
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if(mSettings.contains(APP_PREFERENCES_NAME)) {
            first_Start = mSettings.getBoolean(APP_PREFERENCES_NAME, false);
            ConfigSettings.FisrtStart = true;
        }

        if (first_Start){
            Intent intent = new Intent(OnBoarding.this, MainAllAllNotes.class);
            startActivity(intent);
        }
    }

    private void loadLastScreen() {
        tabLayout.setVisibility(View.VISIBLE);
        getStarted.setVisibility(View.VISIBLE);
        getStarted.setAnimation(btnAnim);
    }
}