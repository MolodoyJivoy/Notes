package my.all.notes.uiNotes;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import my.all.notes.Data.Data;
import my.all.notes.Model.Note;
import my.all.notes.Util.StatusNote;
import my.all.notes.Presenter.Easy.INoteEasyPresenter;
import my.all.notes.Presenter.Easy.NoteEasyPresenter;
import my.all.notes.R;
import my.all.notes.Util.ModeOpenNotes;
import my.all.notes.View.INoteEasy;

import static android.app.Activity.RESULT_OK;


public class NotesEasyFragment extends Fragment implements INoteEasy {

    TextView Date;
    MaterialButton newNoteEasy;

    INoteEasyPresenter iNoteEasyPresenter;
    Note note;
    Note noteUpdate;

    EditText Today;

    ModeOpenNotes modeOpenNotes;
    String uidNoteID;

    public NotesEasyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes_easy, container, false);

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
        // Inflate the layout for this fragment
        return view;
    }

    private void getNoteInfo(String uidNoteID) {
        noteUpdate = iNoteEasyPresenter.ItemNoteInfo(getContext(), uidNoteID);
        setDataNote(noteUpdate);
    }

    private void setDataNote(Note noteUpdate) {
        Today.setText(noteUpdate.getToday());
    }

    private void clickListener() {
        newNoteEasy.setOnClickListener(new View.OnClickListener() {
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
    }

    private boolean getDataNote() {
        String zagolovok = "Заметка " + iNoteEasyPresenter.countNote(getContext());
        String today = Today.getText().toString();
        String date = Date.getText().toString();
        String uniqueID = UUID.randomUUID().toString();
        if (today.trim().length() != 0){
            if (modeOpenNotes == ModeOpenNotes.NEW){
                note = new Note(zagolovok, today, null, null, null, null, date, null, StatusNote.EASY, uniqueID, null, null);
                iNoteEasyPresenter.saveNote(note, getContext());
            }else if (modeOpenNotes == ModeOpenNotes.UPDATE){
                noteUpdate.setToday(today);
                noteUpdate.setDate(date);
                iNoteEasyPresenter.ItemUpdate(getContext(), noteUpdate);
            }
            return true;
        }else {
            Toast.makeText(getContext(), "Заметка не может быть пустая", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void getTime() {
        DateFormat df = new SimpleDateFormat("d MMM yyyy, EEE, HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());
        Date.setText(date);
    }

    private void init(View view) {
        Date = view.findViewById(R.id.DateEasyNotes);
        Today = view.findViewById(R.id.Today);
        newNoteEasy = view.findViewById(R.id.newNoteEasy);

        iNoteEasyPresenter = new NoteEasyPresenter(this, new Data());
    }

    public void setModeOpenNotes(ModeOpenNotes modeOpenNotes) {
        this.modeOpenNotes = modeOpenNotes;
    }

    public void setUidNoteID(String uidNoteID) {
        this.uidNoteID = uidNoteID;
    }
}