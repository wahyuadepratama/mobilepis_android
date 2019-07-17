package com.timerlearning;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.timerlearning.db.TbSetting;
import com.timerlearning.db.TbSettingDao;

@Database( entities = {TbSetting.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TbSettingDao tbSettingDao();
}
