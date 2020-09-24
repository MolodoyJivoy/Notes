package zakaz.zakaz.notes.Data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import zakaz.zakaz.notes.Model.Note;

public class Data implements IData {

    @Override
    public List<Note> getAllNotes(Context context) {
        Paper.init(context);
        List<Note> allNotes = new ArrayList<>();
        List<String> keys = Paper.book("Notes").getAllKeys();
        for (int i = 0; i < keys.size(); i++){
            Note note = Paper.book("Notes").read(keys.get(i));
            allNotes.add(note);
        }
        return allNotes;
    }

    @Override
    public void saveNote(Note note, Context context) {
        Paper.init(context);
        Paper.book("Notes").write(note.getZagolovok(), note);
    }
}
