package io.github.okrand.drivr;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Report {
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
}
