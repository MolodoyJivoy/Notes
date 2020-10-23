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
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import zakaz.zakaz.notes.Adapter.RecyclerAdapterTags;
import zakaz.zakaz.notes.Data.Data;
import zakaz.zakaz.notes.Model.Note;
import zakaz.zakaz.notes.Model.StatusNote;
import zakaz.zakaz.notes.Model.Tag;
import zakaz.zakaz.notes.Presenter.Modern.INoteModernPresenter;
import zakaz.zakaz.notes.Presenter.Modern.NoteModernPresenter;
import zakaz.zakaz.notes.R;
import zakaz.zakaz.notes.Util.ConfigSettings;
import zakaz.zakaz.notes.Util.ModeOpenNotes;
import zakaz.zakaz.notes.View.INoteModern;

import static android.app.Activity.RESULT_OK;

public class NoteModernFragment extends Fragment implements INoteModern {

    Toolbar toolbar;
    INoteModernPresenter iNoteModernPresenter;
    Note note;
    Note noteUpdate;

    RecyclerAdapterTags recyclerAdapterTags;

    private MaterialButton newNote;

    private TextInputEditText Zagolovok;
    private TextView Date;
    private EditText Today, Thanks, Task, Sleep, Mood, Lucky;
    private RecyclerView recyclerView;

    ModeOpenNotes modeOpenNotes;
    String uidNoteID;

    public NoteModernFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        if (modeOpenNotes == ModeOpenNotes.NEW){
            init(view);
            getTime();
            clickListener();
        }else if (modeOpenNotes == ModeOpenNotes.UPDATE){
            init(view);
            getTime();
            clickListener();
            getNoteInfo(uidNoteID);
        }

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        return view;
    }

    private void getNoteInfo(String uidNoteID) {
        noteUpdate = iNoteModernPresenter.ItemNoteInfo(getContext(), uidNoteID);
        setDataNote(noteUpdate);
    }

    private void setDataNote(Note noteUpdate) {
        Zagolovok.setText(noteUpdate.getZagolovok());
        Today.setText(noteUpdate.getToday());
        Thanks.setText(noteUpdate.getThanks());
        Task.setText(noteUpdate.getTask());
        Sleep.setText(noteUpdate.getSleep());
        Mood.setText(noteUpdate.getMood());
        Lucky.setText(noteUpdate.getLucky());
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("Добавить тег", -1));
        for (int i = 0; i < noteUpdate.getTag().length; i++){
            tags.add(0, new Tag(noteUpdate.getTag()[i], i));
        }
        recyclerAdapterTags = new RecyclerAdapterTags(getContext(), tags);
        recyclerView.setAdapter(recyclerAdapterTags);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
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
                if (modeOpenNotes == ModeOpenNotes.NEW){
                    if (getDataNote()){
                        getActivity().setResult(RESULT_OK, intent);
                        getActivity().finish();
                    }
                }else if (modeOpenNotes == ModeOpenNotes.UPDATE){
                    if (getDataNote()){
                        getActivity().setResult(RESULT_OK, intent);
                        getActivity().finish();
                    }
                }
            }
        });

        Today.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setFocus(s, Today, Thanks);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Thanks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setFocus(s, Thanks, Task);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Task.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setFocus(s, Task, Sleep);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Sleep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setFocus(s, Sleep, Mood);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Mood.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setFocus(s, Mood, Lucky);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setFocus(CharSequence s, EditText current, EditText focus) {
        String m_s = s.toString();
        if (m_s.trim().contains(ConfigSettings.FastPassage)){
            focus.setFocusable(true);
            focus.requestFocus();
            String tmp = current.getText().toString();
            current.setText(tmp.substring(0, tmp.length() - 2));
        }
    }

    private void getTime() {
        DateFormat df = new SimpleDateFormat("d MMM yyyy, EEE, HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());
        Date.setText(date);
    }

    //инициализация и заполнение данными
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

        Lucky = view.findViewById(R.id.Lucky);

        newNote = view.findViewById(R.id.newNote);

        recyclerView = view.findViewById(R.id.ItemsCircle);
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("Добавить тег", -1));
        recyclerAdapterTags = new RecyclerAdapterTags(getContext(), tags);
        recyclerView.setAdapter(recyclerAdapterTags);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private boolean getDataNote() {
        String zagolovok = Zagolovok.getText().toString();
        String today = Today.getText().toString();
        String thanks = Thanks.getText().toString();
        String task = Task.getText().toString();
        String sleep = Sleep.getText().toString();
        String mood = Mood.getText().toString();
        String date = Date.getText().toString();
        String lucky = Lucky.getText().toString();
        List<Tag> tagsList = recyclerAdapterTags.getModelList();
        String uniqueID = UUID.randomUUID().toString();
        String[] tags = new String[tagsList.size() - 1];
        for (int i = 0 ; i < tagsList.size() - 1; i++){
            tags[i] = tagsList.get(i).getName();
        }

        if (today.trim().length() != 0 || thanks.trim().length() != 0 || task.trim().length() != 0 || sleep.trim().length() != 0 ||
                mood.trim().length() != 0){
            if (modeOpenNotes == ModeOpenNotes.NEW){
                note = new Note(zagolovok, today, thanks, task, sleep, mood, date, tags, StatusNote.MODERN, uniqueID, lucky);
                iNoteModernPresenter.saveNote(note, getContext());
            }else if (modeOpenNotes == ModeOpenNotes.UPDATE){
                noteUpdate.setDate(date);
                noteUpdate.setToday(today);
                noteUpdate.setMood(mood);
                noteUpdate.setSleep(sleep);
                noteUpdate.setTag(tags);
                noteUpdate.setTask(task);
                noteUpdate.setThanks(thanks);
                noteUpdate.setZagolovok(zagolovok);
                noteUpdate.setLucky(lucky);
                iNoteModernPresenter.ItemUpdate(getContext(), noteUpdate);
            }
            return true;
        }else {
            Toast.makeText(getContext(), "Заметка не может быть пустая", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void setModeOpenNotes(ModeOpenNotes modeOpenNotes) {
        this.modeOpenNotes = modeOpenNotes;
    }

    public void setUidNoteID(String uidNoteID) {
        this.uidNoteID = uidNoteID;
    }
}