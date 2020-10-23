package zakaz.zakaz.notes.Data;

import android.content.Context;

import java.util.List;
import java.util.Map;

import zakaz.zakaz.notes.Model.Note;
import zakaz.zakaz.notes.Util.ConfigSettings;

public interface IData {
    List<Note> getAllNotes(Context context);
    void saveNote(Note note, Context context);
    int getCountNotes(Context context);
    void delete(String id, Context context);

    Note getItemNoteInfo(Context context, String uidID);
    void ItemUpdate(Context context, Note noteUpdate);
    void setSettings(Context context, Map<String, String> configSettings);
}
