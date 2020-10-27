package my.all.notes.Presenter.Settings;

import android.content.Context;

import java.util.Map;

import my.all.notes.Data.IData;
import my.all.notes.View.ISettings;

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
