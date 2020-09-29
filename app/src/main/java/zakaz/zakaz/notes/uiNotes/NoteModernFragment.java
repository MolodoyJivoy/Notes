package zakaz.zakaz.notes.uiNotes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import zakaz.zakaz.notes.Adapter.RecyclerAdapterTags;
import zakaz.zakaz.notes.Data.Data;
import zakaz.zakaz.notes.Model.Note;
import zakaz.zakaz.notes.Model.StatusNote;
import zakaz.zakaz.notes.Model.Tag;
import zakaz.zakaz.notes.Presenter.Modern.INoteModernPresenter;
import zakaz.zakaz.notes.Presenter.Modern.NoteModernPresenter;
import zakaz.zakaz.notes.R;
import zakaz.zakaz.notes.View.INoteModern;

import static android.app.Activity.RESULT_OK;

public class NoteModernFragment extends Fragment implements INoteModern {

    Toolbar toolbar;
    INoteModernPresenter iNoteModernPresenter;
    Note note;

    RecyclerAdapterTags recyclerAdapterTags;

    private MaterialButton newNote;

    private TextInputEditText Zagolovok;
    private TextView Date;
    private EditText Today, Thanks, Task, Sleep, Mood;
    private RecyclerView recyclerView;

    public NoteModernFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        init(view);
        getTime();
        clickListener();

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        return view;
    }

    private void clickListener() {
        Zagolovok.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0){
                    toolbar.setTitle("Новая заметка");
                }else {
                    toolbar.setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                getDataNote();
                getActivity().setResult(RESULT_OK, intent);
                getActivity().finish();
            }
        });

    }

    private void getTime() {
        DateFormat df = new SimpleDateFormat("d MMM yyyy, EEE, HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());
        Date.setText(date);
    }

    private void init(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        Zagolovok = view.findViewById(R.id.Zagolovok);
        iNoteModernPresenter = new NoteModernPresenter(this, new Data());
        Today = view.findViewById(R.id.Today);
        Thanks = view.findViewById(R.id.Thanks);
        Task = view.findViewById(R.id.Task);
        Sleep = view.findViewById(R.id.Sleep);
        Date = view.findViewById(R.id.DateModernNotes);
        Mood = view.findViewById(R.id.Mood);

        newNote = view.findViewById(R.id.newNote);

        recyclerView = view.findViewById(R.id.ItemsCircle);
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("Добавить тег", -1));
        recyclerAdapterTags = new RecyclerAdapterTags(getContext(), tags);
        recyclerView.setAdapter(recyclerAdapterTags);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void getDataNote() {
        String zagolovok = Zagolovok.getText().toString();
        String today = Today.getText().toString();
        String thanks = Thanks.getText().toString();
        String task = Task.getText().toString();
        String sleep = Sleep.getText().toString();
        String mood = Mood.getText().toString();
        String date = Date.getText().toString();
        List<Tag> tagsList = recyclerAdapterTags.getModelList();
        String[] tags = new String[tagsList.size() - 1];
        for (int i = 0 ; i < tagsList.size() - 1; i++){
            tags[i] = tagsList.get(i).getName();
        }
        note = new Note(zagolovok, today, thanks, task, sleep, mood, date, tags, StatusNote.MODERN);
        iNoteModernPresenter.saveNote(note, getContext());
    }
}