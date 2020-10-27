package my.all.notes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import my.all.notes.Adapter.SliderPagerAdapter;
import my.all.notes.Util.ConfigSettings;
import my.all.notes.Util.ModeOpenNotes;
import my.all.notes.Util.Tutorial;
import my.all.notes.uiNotes.NotesEasyFragment;
import my.all.notes.uiNotes.NoteModernFragment;

public class Notes extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    NotesEasyFragment notesEasyFragment;
    NoteModernFragment noteModernFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        if (!ConfigSettings.FisrtStart){
            new Tutorial(Notes.this).tutorial(Notes.this, "Усовершенствованная заметка", R.string.tutorial_modern);
        }

        Intent intent = getIntent();

        String fName = intent.getStringExtra("Fragment");
        String fNameStatus = intent.getStringExtra("FragmentOpen");
        String fNameID = intent.getStringExtra("uidID");

        if (fName.equals("New")){
            List<Fragment> fragments = new ArrayList<>();
            notesEasyFragment = new NotesEasyFragment();
            notesEasyFragment.setModeOpenNotes(ModeOpenNotes.NEW);

            noteModernFragment = new NoteModernFragment();
            noteModernFragment.setModeOpenNotes(ModeOpenNotes.NEW);
            fragments.add(noteModernFragment);
            fragments.add(notesEasyFragment);
            viewPager = findViewById(R.id.viewPagerAll);
            pagerAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
            viewPager.setAdapter(pagerAdapter);
            viewPager.setCurrentItem(0);
        }else if (fName.equals("Update")){
            List<Fragment> fragments = new ArrayList<>();
            if (fNameStatus.equals("EASY")){
                notesEasyFragment = new NotesEasyFragment();
                notesEasyFragment.setModeOpenNotes(ModeOpenNotes.UPDATE);
                notesEasyFragment.setUidNoteID(fNameID);
                fragments.add(notesEasyFragment);
            }else if (fNameStatus.equals("MODERN")){
                noteModernFragment = new NoteModernFragment();
                noteModernFragment.setModeOpenNotes(ModeOpenNotes.UPDATE);
                noteModernFragment.setUidNoteID(fNameID);
                fragments.add(noteModernFragment);
            }

            viewPager = findViewById(R.id.viewPagerAll);
            pagerAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
            viewPager.setAdapter(pagerAdapter);
            viewPager.setCurrentItem(0);

            tutorial();

        }


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    if (!ConfigSettings.FisrtStart){
                        new Tutorial(Notes.this).tutorial(Notes.this, "Усовершенствованная заметка", R.string.tutorial_modern);
                    }
                }else if (position == 1){
                    if (!ConfigSettings.FisrtStart){
                        new Tutorial(Notes.this).tutorial(Notes.this, "Обычная заметка", R.string.tutorial_easy);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void tutorial() {
        //анимация показа viewpager
    }


}