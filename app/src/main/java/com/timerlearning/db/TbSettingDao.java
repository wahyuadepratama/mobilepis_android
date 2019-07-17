package com.timerlearning.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface TbSettingDao {

    @Query("SELECT notification FROM tb_setting")
    Boolean getNotification();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertToTbSetting(TbSetting tbsetting);
}
