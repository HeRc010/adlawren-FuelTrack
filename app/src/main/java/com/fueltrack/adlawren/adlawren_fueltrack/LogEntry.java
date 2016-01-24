package com.fueltrack.adlawren.adlawren_fueltrack;

import java.util.Date;

/**
 * Created by adlawren on 23/01/16.
 */
public class LogEntry {

    private Date date;

    public LogEntry() {
        this.date = new Date(System.currentTimeMillis());
    }

    public LogEntry(Date initialDate) {
        this.date = initialDate;
    }

    @Override
    public String toString() {
        return date.toString();
    }
}
