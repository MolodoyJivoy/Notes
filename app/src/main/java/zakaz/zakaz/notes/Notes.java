package zakaz.zakaz.notes;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import zakaz.zakaz.notes.Adapter.RecyclerAdapterTags;
import zakaz.zakaz.notes.Adapter.SliderPagerAdapter;
import zakaz.zakaz.notes.Model.Note;
import zakaz.zakaz.notes.Model.StatusNote;
import zakaz.zakaz.notes.Presenter.Easy.NoteEasyPresenter;
import zakaz.zakaz.notes.Presenter.Modern.INoteModernPresenter;
import zakaz.zakaz.notes.Util.ModeOpenNotes;
import zakaz.zakaz.notes.uiNotes.NotesEasyFragment;
import zakaz.zakaz.notes.uiNotes.NoteModernFragment;

public class Notes extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    NotesEasyFragment notesEasyFragment;
    NoteModernFragment noteModernFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

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
        }


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1){
                    notesEasyFragment.startAnim();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


}