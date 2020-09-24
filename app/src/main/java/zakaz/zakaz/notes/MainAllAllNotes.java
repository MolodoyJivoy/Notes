package zakaz.zakaz.notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zakaz.zakaz.notes.Adapter.NoteListAdapter;
import zakaz.zakaz.notes.Data.Data;
import zakaz.zakaz.notes.Model.Note;
import zakaz.zakaz.notes.Presenter.INoteAllPresenter;
import zakaz.zakaz.notes.Presenter.NoteAllAllPresenter;
import zakaz.zakaz.notes.View.IMainAllNotes;

public class MainAllAllNotes extends AppCompatActivity implements IMainAllNotes {

    Button newNote;
    INoteAllPresenter iNoteAllPresenter;
    ListView listAllView;
    NoteListAdapter noteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listAllView = findViewById(R.id.AllNotes);

        iNoteAllPresenter = new NoteAllAllPresenter(this, new Data());
        iNoteAllPresenter.onAllNotes(getApplicationContext());

        newNote = findViewById(R.id.newNote);
        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAllAllNotes.this, Notes.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public void AllNotes(List<Note> note) {
        noteListAdapter = new NoteListAdapter(this, note);
        listAllView.setAdapter(noteListAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == 1){
                iNoteAllPresenter.onAllNotes(getApplicationContext());
                Toast.makeText(MainAllAllNotes.this, "Заметка добавлена", Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}