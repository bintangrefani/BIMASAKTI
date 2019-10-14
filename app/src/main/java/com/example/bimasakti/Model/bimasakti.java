package com.example.bimasakti.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class bimasakti implements Parcelable {
    private String date;
    private String label ;
    private int nb_visits;
    private boolean status;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getNb_visits() {
        return nb_visits;
    }

    public void setNb_visits(int nb_visits) {
        this.nb_visits = nb_visits;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.label);
        dest.writeInt(this.nb_visits);
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
    }

    public bimasakti() {
    }

    protected bimasakti(Parcel in) {
        this.date = in.readString();
        this.label = in.readString();
        this.nb_visits = in.readInt();
        this.status = in.readByte() != 0;
    }

    public static final Parcelable.Creator<bimasakti> CREATOR = new Parcelable.Creator<bimasakti>() {
        @Override
        public bimasakti createFromParcel(Parcel source) {
            return new bimasakti(source);
        }

        @Override
        public bimasakti[] newArray(int size) {
            return new bimasakti[size];
        }
    };
}
