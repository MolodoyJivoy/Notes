package zakaz.zakaz.notes.Presenter;

import android.content.Context;

import zakaz.zakaz.notes.Data.IData;
import zakaz.zakaz.notes.Model.Note;
import zakaz.zakaz.notes.View.INoteModern;

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
}
