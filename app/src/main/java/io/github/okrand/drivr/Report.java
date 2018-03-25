package io.github.okrand.drivr;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Report implements Parcelable{
    private String license;
    private String state;
    private String claim;
    private String time;

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public Report(String state, String license, String claim) {
        this.state = state;
        this.license = license;
        this.claim = claim;
        Date date = new Date();
        time = sdf.format(date);
    }

    public Report(String state, String license, String claim, String time) {
        this.state = state;
        this.license = license;
        this.claim = claim;
        this.time = time;
    }

    public Report() {
        this.state = "";
        this.license = "";
        this.claim = "";
        time = "";
    }

    protected Report(Parcel in) {
        license = in.readString();
        state = in.readString();
        claim = in.readString();
        time = in.readString();
    }

    public static final Creator<Report> CREATOR = new Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel in) {
            return new Report(in);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };

    public String getLicense() {
        return license;
    }

    public String getState() {
        return state;
    }

    public String getClaim() {
        return claim;
    }

    public String getTime() {
        return time;
    }

    public void setLicense(String l) { license = l; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(license);
        dest.writeString(state);
        dest.writeString(claim);
        dest.writeString(time);
    }
}
