package com.timerlearning.activity;

import android.arch.persistence.room.Room;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.timerlearning.AppDatabase;
import com.timerlearning.R;
import com.timerlearning.db.TbSetting;

import java.util.Objects;

public class SettingActivity extends AppCompatActivity {

    AppDatabase settingDb;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");

        setContentView(R.layout.activity_setting);

        settingDb = Room.databaseBuilder(this, AppDatabase.class, "setting.db")
                .allowMainThreadQueries()
                .build();
        Boolean currentSetting = settingDb.tbSettingDao().getNotification();

        Switch s = findViewById(R.id.showNotif);
        s.setChecked(currentSetting);

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(SettingActivity.this, "Notification Activated!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SettingActivity.this, "Notification Deactivated!", Toast.LENGTH_LONG).show();
                }
                TbSetting tbsetting =new TbSetting();
                tbsetting.id = 1;
                tbsetting.notification = isChecked;

                settingDb.tbSettingDao().insertToTbSetting(tbsetting);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
