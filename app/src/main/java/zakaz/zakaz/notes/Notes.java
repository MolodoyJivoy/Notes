package zakaz.zakaz.notes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
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
import zakaz.zakaz.notes.Presenter.INoteModernPresenter;
import zakaz.zakaz.notes.uiNotes.NotesEasyFragment;
import zakaz.zakaz.notes.uiNotes.NoteModernFragment;

public class Notes extends AppCompatActivity {

    Toolbar toolbar;
    INoteModernPresenter iNoteModernPresenter;
    Note note;

    RecyclerAdapterTags recyclerAdapterTags;

    private TextInputEditText Zagolovok;
    private TextView Date;
    private EditText Today, Thanks, Task, Sleep, Mood;
    private RecyclerView recyclerView;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NoteModernFragment());
        fragments.add(new NotesEasyFragment());

        viewPager = findViewById(R.id.viewPagerAll);
        pagerAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (position == 2){
//                    NotesModernFragment fragment = (NotesModernFragment) pagerAdapter.instantiateItem(viewPager, position);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

    }


}