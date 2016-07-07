package co.kogi.feedtabapp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import co.kogi.feedtabapp.R;
import co.kogi.feedtabapp.util.AppContext;

public class SettingsActivity extends AppCompatActivity {

    Switch switch_articleColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        switch_articleColors = (Switch) findViewById(R.id.switch_articleColors);
        if (AppContext.getPreference("interpColors").equals("true")){
            switch_articleColors.setChecked(true);
        }
        switch_articleColors.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppContext.addPreference("interpColors","true");
                } else {
                    AppContext.addPreference("interpColors","false");
                }
            }
        });
    }
}
