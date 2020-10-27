package my.all.notes.Presenter;

import android.content.Context;

import java.util.List;

import my.all.notes.Data.IData;
import my.all.notes.Model.Note;
import my.all.notes.View.IMainAllNotes;

public class NoteAllAllPresenter implements INoteAllPresenter {

    IMainAllNotes iMainAllNotes;
    IData iDataClass;

    public NoteAllAllPresenter(IMainAllNotes iMainAllNotes, IData iDataClass) {
        this.iMainAllNotes = iMainAllNotes;
        this.iDataClass = iDataClass;
    }


    @Override
    public void onAllNotes(Context context, int mode) {
        List<Note> notes = iDataClass.getAllNotes(context);
        iMainAllNotes.AllNotes(notes, mode);
    }

    @Override
    public void onDelete(String id, Context context) {
        iDataClass.delete(id, context);
    }
}
