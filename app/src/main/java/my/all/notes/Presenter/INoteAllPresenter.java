package my.all.notes.Presenter;

import android.content.Context;

public interface INoteAllPresenter {
    void onAllNotes(Context context, int mode);
    void onDelete(String id, Context context);
}
