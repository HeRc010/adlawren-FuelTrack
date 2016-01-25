package com.fueltrack.adlawren.adlawren_fueltrack;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adlawren on 23/01/16.
 */
public class LogEntry implements Serializable {

    // TODO: see how difficult this is to implement;
    // instead of having the user enter an arbitrarily specific value,
    // use a drop down list with limited options.
    // public enum FuelGrade {REGULAR, PREMIUM};

    private Date date;
    private String station = null, fuelGrade = null;
    private Double odometerReading = 0.0, // in km, rounded to 1 decimal place
                   fuelAmount = 0.0, // in L, numeric to 3 decimal places
                   fuelUnitCost = 0.0, // in cents per L, numeric to 1 decimal place
                   fuelCost = 0.0; // in dollars, numeric to 2 decimal places // TODO: remove, uneeded

    // TODO: remove; used for testing
    public LogEntry() {
        this.date = new Date(System.currentTimeMillis());

        this.station = "Test Station";
        this.fuelGrade = "Test Fuel Grade";

        this.odometerReading = 50.0;
        this.fuelAmount = 100.0;
        this.fuelUnitCost = 150.0;
    }

    // TODO: add error checking
    public LogEntry(Date initialDate,
                    String initialStation,
                    String initialFuelGrade,
                    Double initialOdometerReading,
                    Double initialFuelAmount,
                    Double initialFuelUnitCost) {
        this.date = initialDate; // Ensure the date is not in the future
        this.station = initialStation; // Ensure maximum length?
        this.fuelGrade = initialFuelGrade; // Ensure maximum length?
        this.odometerReading = initialOdometerReading; // Ensure value is greater than zero, maximum value?
        this.fuelAmount = initialFuelAmount; // Ensure value is greater than zero, maximum value?
        this.fuelUnitCost = initialFuelUnitCost; // Ensure value is greater than zero, maximum value?
    }

    public String getStation() {
        return this.station;
    }

    public String getFuelGrade() {
        return this.fuelGrade;
    }

    public Double getOdometerReading() {
        return this.odometerReading;
    }

    public Double getFuelAmount() {
        return this.fuelAmount;
    }

    public Double getFuelUnitCost() {
        return this.fuelUnitCost;
    }

    public Double getFuelCost() {
        return this.fuelAmount * this.fuelUnitCost;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        // Add date
        builder.append("Date: ");
        builder.append(new SimpleDateFormat("yyyy-MM-dd").format(this.date) + "\n");

        // Add Station
        builder.append("Station: " + this.station + "\n");

        // Add Fuel Grade
        builder.append("Grade: " + this.fuelGrade + "\n");

        // Add Odometer Reading
        builder.append("Odometer Reading: ");
        builder.append(this.odometerReading);
        builder.append("\n");

        // Add Fuel Amount
        builder.append("Amount: ");
        builder.append(this.fuelAmount);
        builder.append("\n");

        // Add Fuel Unit Cost
        builder.append("Unit Cost: ");
        builder.append(this.fuelUnitCost);
        builder.append("\n");

        // Add Fuel Cost
        builder.append("Cost: ");
        builder.append(this.fuelAmount * this.fuelUnitCost);
        builder.append("\n");

        return builder.toString();
    }
}
