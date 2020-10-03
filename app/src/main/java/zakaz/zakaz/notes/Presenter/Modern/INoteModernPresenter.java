package zakaz.zakaz.notes.Presenter.Modern;

import android.content.Context;

import zakaz.zakaz.notes.Model.Note;

public interface INoteModernPresenter {
    void saveNote(Note note, Context context);
    void ItemUpdate(Context context, Note noteUpdate);
    Note ItemNoteInfo(Context context, String uidID);
}
