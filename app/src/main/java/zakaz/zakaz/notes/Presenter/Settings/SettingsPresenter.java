package zakaz.zakaz.notes.Presenter.Settings;

import android.content.Context;

import java.util.List;
import java.util.Map;

import zakaz.zakaz.notes.Data.IData;
import zakaz.zakaz.notes.Util.ConfigSettings;
import zakaz.zakaz.notes.View.ISettings;

public class SettingsPresenter implements ISettingsPresenter {
    ISettings iSettings;
    IData iDataClass;

    public SettingsPresenter(ISettings iSettings, IData iDataClass) {
        this.iSettings = iSettings;
        this.iDataClass = iDataClass;
    }


    @Override
    public void saveSettings(Context context, Map<String, String> configSettings) {
        iDataClass.setSettings(context, configSettings);
    }
}
