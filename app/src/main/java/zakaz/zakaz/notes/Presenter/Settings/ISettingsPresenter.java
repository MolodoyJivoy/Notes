package zakaz.zakaz.notes.Presenter.Settings;

import android.content.Context;

import java.util.List;
import java.util.Map;

import zakaz.zakaz.notes.Util.ConfigSettings;

public interface ISettingsPresenter {
    void saveSettings(Context context, Map<String, String> configSettings);
}
