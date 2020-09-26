package zakaz.zakaz.notes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import io.paperdb.Paper;
import zakaz.zakaz.notes.Adapter.NoteRecyclerListAdapter;
import zakaz.zakaz.notes.Data.Data;
import zakaz.zakaz.notes.Model.Note;
import zakaz.zakaz.notes.Presenter.INoteAllPresenter;
import zakaz.zakaz.notes.Presenter.NoteAllAllPresenter;
import zakaz.zakaz.notes.View.IMainAllNotes;

public class MainAllAllNotes extends AppCompatActivity implements IMainAllNotes, NoteRecyclerListAdapter.OnNoteListener {

    Button newNote;
    INoteAllPresenter iNoteAllPresenter;
    RecyclerView listAllView;

    NoteRecyclerListAdapter noteRecyclerListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //clear();
        listAllView = findViewById(R.id.AllNotes);
        newNote = findViewById(R.id.newNote);

        iNoteAllPresenter = new NoteAllAllPresenter(this, new Data());
        iNoteAllPresenter.onAllNotes(getApplicationContext());

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
        //noteListAdapter = new NoteListAdapter(this, note);
        noteRecyclerListAdapter = new NoteRecyclerListAdapter(this, note, this);
        listAllView.setAdapter(noteRecyclerListAdapter);
        listAllView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

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

    void clear(){
        Paper.init(getApplicationContext());
        Paper.book("Notes").destroy();
    }

    @Override
    public void onNoteClick(int position, List<Note> modelList) {
        Toast.makeText(this, modelList.get(position).getToday(), Toast.LENGTH_SHORT).show();
    }
}