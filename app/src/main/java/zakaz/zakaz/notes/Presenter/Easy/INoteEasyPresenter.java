package zakaz.zakaz.notes.Presenter.Easy;

import android.content.Context;

import zakaz.zakaz.notes.Model.Note;

public interface INoteEasyPresenter {
    void saveNote(Note note, Context context);
    int countNote(Context context);
    Note ItemNoteInfo(Context context, String uidID);
    void ItemUpdate(Context context, Note noteUpdate);
}
