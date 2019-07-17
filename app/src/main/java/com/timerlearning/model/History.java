package com.timerlearning.model;

import android.os.Parcel;
import android.os.Parcelable;

public class History implements Parcelable {
    private String title;
    private String body;
    private String status;
    private String date;

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.date);
        dest.writeString(this.body);
        dest.writeString(this.status);
    }

    private History(Parcel source) {
        this.title = source.readString();
        this.date = source.readString();
        this.body = source.readString();
        this.status = source.readString();
    }

    public static final Parcelable.Creator<History> CREATOR = new Creator<History>() {
        @Override
        public History createFromParcel(Parcel source) {
            return new History(source);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };
}
