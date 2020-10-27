package my.all.notes.Presenter.Modern;

import android.content.Context;

import my.all.notes.Data.IData;
import my.all.notes.Model.Note;
import my.all.notes.View.INoteModern;

public class NoteModernPresenter implements INoteModernPresenter {
    INoteModern iNoteModern;
    IData iDataClass;

    public NoteModernPresenter(INoteModern iNoteModern, IData iDataClass) {
        this.iNoteModern = iNoteModern;
        this.iDataClass = iDataClass;
    }


    @Override
    public void saveNote(Note note, Context context) {
        iDataClass.saveNote(note, context);
    }

    @Override
    public void ItemUpdate(Context context, Note noteUpdate) {
        iDataClass.ItemUpdate(context, noteUpdate);
    }

    @Override
    public Note ItemNoteInfo(Context context, String uidID) {
        return iDataClass.getItemNoteInfo(context, uidID);
    }
}
