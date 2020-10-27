package my.all.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

import my.all.notes.Data.Data;
import my.all.notes.Presenter.Settings.ISettingsPresenter;
import my.all.notes.Presenter.Settings.SettingsPresenter;
import my.all.notes.Util.ConfigSettings;
import my.all.notes.Util.Tutorial;
import my.all.notes.View.ISettings;

public class Settings extends AppCompatActivity implements ISettings {

    private Toolbar toolbar;
    private TextInputEditText FastPassage;
    private ISettingsPresenter iSettingsPResenter;
    private MaterialButton materialButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (!ConfigSettings.FisrtStart){
            new Tutorial(this).tutorial(this, "Настройки", R.string.tutorial_settings);
        }

        toolbar = findViewById(R.id.toolbarSettings);
        toolbar.setTitle("Настройки");
        toolbar.setBackgroundColor(Color.TRANSPARENT);

        materialButton = findViewById(R.id.saveSettings);

        FastPassage = findViewById(R.id.FastPassage);
        FastPassage.setText(ConfigSettings.FastPassage);

        iSettingsPResenter = new SettingsPresenter(this, new Data());

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (FastPassage.getText().toString().trim().length() < 2){
                    Toast.makeText(getApplicationContext(), "Быстрый переход должен состоять из двух символов", Toast.LENGTH_SHORT).show();
                }else {
                    ConfigSettings.FastPassage = FastPassage.getText().toString().trim();
                    Map<String, String> setting = new HashMap<>();
                    setting.put("FastPassage", ConfigSettings.FastPassage);
                    iSettingsPResenter.saveSettings(Settings.this, setting);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }
}