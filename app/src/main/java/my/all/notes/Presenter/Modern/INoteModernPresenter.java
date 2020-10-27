package my.all.notes.Presenter.Modern;

import android.content.Context;

import my.all.notes.Model.Note;

public interface INoteModernPresenter {
    void saveNote(Note note, Context context);
    void ItemUpdate(Context context, Note noteUpdate);
    Note ItemNoteInfo(Context context, String uidID);
}
