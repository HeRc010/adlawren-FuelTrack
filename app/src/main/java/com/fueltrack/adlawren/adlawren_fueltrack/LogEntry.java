package com.fueltrack.adlawren.adlawren_fueltrack;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adlawren on 23/01/16.
 */
public class LogEntry {

    // TODO: see how difficult this is to implement;
    // instead of having the user enter an arbitrarily specific value,
    // use a drop down list with limited options.
    // public enum FuelGrade {REGULAR, PREMIUM};

    private Date date;
    private String station, fuelGrade;
    private Double odometerReading = 0.0, // in km, rounded to 1 decimal place.
                   fuelAmount = 0.0, // in L, numeric to 3 decimal places.
                   fuelUnitCost = 0.0, // in cents per L, numeric to 1 decimal place.
                   fuelCost = 0.0; // in dollars, numeric to 2 decimal places.

    public LogEntry() {
        this.date = new Date(System.currentTimeMillis());
    }

    public LogEntry(Date initialDate) {
        this.date = initialDate;
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(this.date);
    }
}
