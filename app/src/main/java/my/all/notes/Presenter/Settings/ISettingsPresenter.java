package my.all.notes.Presenter.Settings;

import android.content.Context;

import java.util.Map;

public interface ISettingsPresenter {
    void saveSettings(Context context, Map<String, String> configSettings);
}
