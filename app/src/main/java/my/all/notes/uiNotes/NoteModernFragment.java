package my.all.notes.uiNotes;

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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import my.all.notes.Adapter.RecyclerAdapterTags;
import my.all.notes.Data.Data;
import my.all.notes.Model.Note;
import my.all.notes.Util.StatusNote;
import my.all.notes.Model.Tag;
import my.all.notes.Presenter.Modern.INoteModernPresenter;
import my.all.notes.Presenter.Modern.NoteModernPresenter;
import my.all.notes.R;
import my.all.notes.Util.ConfigSettings;
import my.all.notes.Util.ModeOpenNotes;
import my.all.notes.View.INoteModern;

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
    private TextView TodayHead, ThanksHead, TaskHead, SleepHead, MoodHead, LuckyHead;
    private RecyclerView recyclerView;
    private Switch SwitchMode;

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
        SwitchMode.setChecked(noteUpdate.getSwitchMode());
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

        SwitchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    textViewNight();
                }else {
                    textViewDay();
                }
            }
        });

    }

    private void textViewDay() {
        TodayHead.setText(R.string.modern_todayDay);
        ThanksHead.setText(R.string.modern_thanksDay);
        LuckyHead.setText(R.string.modern_luckyDay);
        MoodHead.setText(R.string.modern_moodDay);
        SleepHead.setText(R.string.modern_sleepDay);
        TaskHead.setText(R.string.modern_taskDay);

        Today.setHint(R.string.hint_todayDay);
        Thanks.setHint(R.string.hint_thanksDay);
        Lucky.setHint(R.string.hint_luckyDay);
        Mood.setHint(R.string.hint_moodDay);
        Sleep.setHint(R.string.hint_sleepDay);
        Task.setHint(R.string.hint_taskDay);

    }

    private void textViewNight() {
        TodayHead.setText(R.string.modern_todayNight);
        ThanksHead.setText(R.string.modern_thanksNight);
        LuckyHead.setText(R.string.modern_luckyNight);
        MoodHead.setText(R.string.modern_moodNight);
        SleepHead.setText(R.string.modern_sleepNight);
        TaskHead.setText(R.string.modern_taskNight);

        Today.setHint(R.string.hint_todayNight);
        Thanks.setHint(R.string.hint_thanksNight);
        Lucky.setHint(R.string.hint_luckyNight);
        Mood.setHint(R.string.hint_moodNight);
        Sleep.setHint(R.string.hint_sleepNight);
        Task.setHint(R.string.hint_taskNight);
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

        TodayHead = view.findViewById(R.id.TodayModernHead);
        ThanksHead = view.findViewById(R.id.ThanksModernHead);
        TaskHead = view.findViewById(R.id.TaskModernHead);
        SleepHead = view.findViewById(R.id.SleepModernHead);
        MoodHead = view.findViewById(R.id.MoodModernHead);
        LuckyHead = view.findViewById(R.id.LuckyModernHead);

        SwitchMode = view.findViewById(R.id.switchMode);

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
        Boolean switchMode = SwitchMode.isChecked();
        List<Tag> tagsList = recyclerAdapterTags.getModelList();
        String uniqueID = UUID.randomUUID().toString();
        String[] tags = new String[tagsList.size() - 1];
        for (int i = 0 ; i < tagsList.size() - 1; i++){
            tags[i] = tagsList.get(i).getName();
        }

        if (today.trim().length() != 0 || thanks.trim().length() != 0 || task.trim().length() != 0 || sleep.trim().length() != 0 ||
                mood.trim().length() != 0){
            if (modeOpenNotes == ModeOpenNotes.NEW){
                note = new Note(zagolovok, today, thanks, task, sleep, mood, date, tags, StatusNote.MODERN, uniqueID, lucky, switchMode);
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
                noteUpdate.setSwitchMode(switchMode);
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