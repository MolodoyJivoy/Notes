package my.all.notes.Data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.paperdb.Paper;
import my.all.notes.Model.Note;
import my.all.notes.Util.ConfigSettings;

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

    public static void getSettings(Context context){
        Paper.init(context);

        Map<String, String> settings = Paper.book("Settings").read("Settings");
        if (settings != null){
            ConfigSettings.FastPassage = settings.get("FastPassage");
        }

    }

    public void setSettings(Context context, Map<String, String> configSettings){
        Paper.init(context);
        Paper.book("Settings").write("Settings", configSettings);
    }
}
