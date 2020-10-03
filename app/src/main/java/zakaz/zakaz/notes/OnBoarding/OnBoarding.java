package zakaz.zakaz.notes.OnBoarding;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import zakaz.zakaz.notes.MainAllAllNotes;
import zakaz.zakaz.notes.R;

public class OnBoarding extends AppCompatActivity {

    private ViewPager viewPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabLayout;
    private Button getStarted;
    private int pos = 0;
    private Animation btnAnim;
    private boolean only_one = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);
        viewPager = findViewById(R.id.viewPagerOnBoarding);
        tabLayout = findViewById(R.id.tab_indicator);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#131313"));

        getStarted = findViewById(R.id.getStarted);
        getStarted.setVisibility(View.INVISIBLE);
        btnAnim = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.button_animation);
        final List<ScreenItem> screenItems = new ArrayList<>();
        screenItems.add(new ScreenItem("Каждый день новая книга", "Каждый день - это новая жизнь!» Каждый день по утрам проживаем мы юность, Не взирая на пол и на возраст, на чин и на званья.", R.drawable.ic_clipboard_boarding));
        screenItems.add(new ScreenItem("Добавляй в избранное, чтобы не забыть", "Каждый день - это новая жизнь!» Каждый день по утрам проживаем мы юность, Не взирая на пол и на возраст, на чин и на званья.", R.drawable.ic_clipboard_boarding));
        screenItems.add(new ScreenItem("Не забывай, читать классно", "Каждый день - это новая жизнь!» Каждый день по утрам проживаем мы юность, Не взирая на пол и на возраст, на чин и на званья.", R.drawable.ic_clipboard_boarding));
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

    private void loadLastScreen() {
        tabLayout.setVisibility(View.VISIBLE);
        getStarted.setVisibility(View.VISIBLE);
        getStarted.setAnimation(btnAnim);
    }
}