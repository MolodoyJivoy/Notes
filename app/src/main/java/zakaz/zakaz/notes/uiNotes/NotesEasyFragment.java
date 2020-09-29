package zakaz.zakaz.notes.uiNotes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import zakaz.zakaz.notes.Data.Data;
import zakaz.zakaz.notes.Model.Note;
import zakaz.zakaz.notes.Model.StatusNote;
import zakaz.zakaz.notes.Presenter.Easy.INoteEasyPresenter;
import zakaz.zakaz.notes.Presenter.Easy.NoteEasyPresenter;
import zakaz.zakaz.notes.Presenter.Modern.INoteModernPresenter;
import zakaz.zakaz.notes.Presenter.Modern.NoteModernPresenter;
import zakaz.zakaz.notes.R;
import zakaz.zakaz.notes.View.INoteEasy;

import static android.app.Activity.RESULT_OK;


public class NotesEasyFragment extends Fragment implements INoteEasy {

    TextView Date;
    MaterialButton newNoteEasy;

    INoteEasyPresenter iNoteEasyPresenter;
    Note note;

    EditText Today;

    public NotesEasyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes_easy, container, false);

        init(view);
        getTime();
        clickListener();
        // Inflate the layout for this fragment
        return view;
    }

    private void clickListener() {
        newNoteEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (getDataNote()){
                    getActivity().setResult(RESULT_OK, intent);
                    getActivity().finish();
                }
            }
        });
    }

    private boolean getDataNote() {
        String zagolovok = "Заметка " + iNoteEasyPresenter.countNote(getContext());
        String today = Today.getText().toString();
        String date = Date.getText().toString();
        if (today.trim().length() != 0){
            note = new Note(zagolovok, today, null, null, null, null, date, null, StatusNote.EASY);
            iNoteEasyPresenter.saveNote(note, getContext());
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
        newNoteEasy = view.findViewById(R.id.newNoteEasy);
        Today = view.findViewById(R.id.Today);
        iNoteEasyPresenter = new NoteEasyPresenter(this, new Data());
    }
}