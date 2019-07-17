package com.timerlearning.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tb_setting")
public class TbSetting {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "notification")
    public Boolean notification;

}
