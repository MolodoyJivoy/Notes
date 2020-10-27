package my.all.notes.Data;

import android.content.Context;

import java.util.List;
import java.util.Map;

import my.all.notes.Model.Note;

public interface IData {
    List<Note> getAllNotes(Context context);
    void saveNote(Note note, Context context);
    int getCountNotes(Context context);
    void delete(String id, Context context);

    Note getItemNoteInfo(Context context, String uidID);
    void ItemUpdate(Context context, Note noteUpdate);
    void setSettings(Context context, Map<String, String> configSettings);
}
