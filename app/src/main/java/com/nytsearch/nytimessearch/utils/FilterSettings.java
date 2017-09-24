package com.nytsearch.nytimessearch.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SagarMutha on 9/24/17.
 */

public class FilterSettings implements Parcelable {

    String date;
    String sortOrder;
    String newsDesk;

    public FilterSettings(String date, String sortOrder, String newsDesk) {
        this.date = date;
        this.sortOrder = sortOrder.toLowerCase();
        this.newsDesk = newsDesk;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getNewsDesk() {
        return newsDesk;
    }

    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.sortOrder);
        dest.writeString(this.newsDesk);
    }

    protected FilterSettings(Parcel in) {
        this.date = in.readString();
        this.sortOrder = in.readString();
        this.newsDesk = in.readString();
    }

    public static final Parcelable.Creator<FilterSettings> CREATOR = new Parcelable.Creator<FilterSettings>() {
        @Override
        public FilterSettings createFromParcel(Parcel source) {
            return new FilterSettings(source);
        }

        @Override
        public FilterSettings[] newArray(int size) {
            return new FilterSettings[size];
        }
    };
}
