package zakaz.zakaz.notes.Data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        Paper.book("Notes").write(note.getUniqueID(), note);
    }

    @Override
    public int getCountNotes(Context context) {
        Paper.init(context);
        return Paper.book("Notes").getAllKeys().size();
    }

    @Override
    public void delete(String id, Context context) {
        Paper.init(context);
        Paper.book("Notes").delete(id);
    }

    @Override
    public Note getItemNoteInfo(Context context, String uidID) {
        Paper.init(context);
        return Paper.book("Notes").read(uidID);
    }

    @Override
    public void ItemUpdate(Context context, Note noteUpdate) {
        Paper.init(context);
        Paper.book("Notes").write(noteUpdate.getUniqueID(), noteUpdate);
    }
}
