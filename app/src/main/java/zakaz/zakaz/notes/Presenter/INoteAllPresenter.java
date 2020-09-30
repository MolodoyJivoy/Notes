package zakaz.zakaz.notes.Presenter;

import android.content.Context;

import zakaz.zakaz.notes.Model.Note;

public interface INoteAllPresenter {
    void onAllNotes(Context context, int mode);
    void onDelete(String id, Context context);
}
