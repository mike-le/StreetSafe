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
    private String option;
    private String time;

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    public Report(String state, String license, String claim, String option, String time) {
        this.state = state;
        this.license = license;
        this.claim = claim;
        this.time = time;
        this.option = option;
    }

    public Report() {
        this.state = "";
        this.license = "";
        this.claim = "";
        this.option = "";
        this.time = "";
    }

    protected Report(Parcel in) {
        license = in.readString();
        state = in.readString();
        claim = in.readString();
        time = in.readString();
        option = in.readString();
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

    public String getOption() {
        return option;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String newTime) { time = newTime; }
    public void setOption(String newOption) { option = newOption; }
    public void setState(String newState) { state = newState; }
    public void setClaim(String newClaim) { claim = newClaim; }
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
        dest.writeString(option);
        dest.writeString(time);
    }
}
