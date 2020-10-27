package my.all.notes.Presenter.Easy;

import android.content.Context;

import my.all.notes.Data.IData;
import my.all.notes.Model.Note;
import my.all.notes.View.INoteEasy;

public class NoteEasyPresenter implements INoteEasyPresenter {

    INoteEasy iNoteEasy;
    IData iDataClass;

    public NoteEasyPresenter(INoteEasy iNoteEasy, IData iDataClass) {
        this.iNoteEasy = iNoteEasy;
        this.iDataClass = iDataClass;
    }

    @Override
    public void saveNote(Note note, Context context) {
        iDataClass.saveNote(note, context);
    }

    @Override
    public int countNote(Context context) {
        return iDataClass.getCountNotes(context) + 1;
    }

    @Override
    public Note ItemNoteInfo(Context context, String uidID) {
        return iDataClass.getItemNoteInfo(context, uidID);
    }

    @Override
    public void ItemUpdate(Context context, Note noteUpdate) {
        iDataClass.ItemUpdate(context, noteUpdate);
    }
}
