package zakaz.zakaz.notes.Data;

import android.content.Context;

import java.util.List;

import zakaz.zakaz.notes.Model.Note;

public interface IData {
    List<Note> getAllNotes(Context context);
    void saveNote(Note note, Context context);
}
