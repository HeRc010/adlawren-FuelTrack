package com.fueltrack.adlawren.adlawren_fueltrack;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adlawren on 23/01/16.
 */
public class LogEntry implements Serializable {
    private Date date;
    private String station = null, fuelGrade = null;
    private Double odometerReading = 0.0, // in km, rounded to 1 decimal place
                   fuelAmount = 0.0, // in L, numeric to 3 decimal places
                   fuelUnitCost = 0.0, // in cents per L, numeric to 1 decimal place
                   fuelCost = 0.0; // in dollars, numeric to 2 decimal places // TODO: remove, uneeded

    // Default Constructor; initialize member variables to basic values
    public LogEntry() {
        date = new Date(System.currentTimeMillis());

        station = "";
        fuelGrade = "";

        odometerReading = 0.0;
        fuelAmount = 0.0;
        fuelUnitCost = 0.0;
    }

    // Copy Constructor; duplicate the member variables found in the other LogEntry
    public LogEntry(LogEntry otherLogEntry) {
        date = otherLogEntry.date;

        station = otherLogEntry.station;
        fuelGrade = otherLogEntry.fuelGrade;

        odometerReading = otherLogEntry.odometerReading;
        fuelAmount = otherLogEntry.fuelAmount;
        fuelUnitCost = otherLogEntry.fuelUnitCost;
    }

    // TODO: add error checking
    // Construct a LogEntry using the provided parameters
    public LogEntry(Date initialDate,
                    String initialStation,
                    String initialFuelGrade,
                    Double initialOdometerReading,
                    Double initialFuelAmount,
                    Double initialFuelUnitCost) {
        date = initialDate; // Ensure the date is not in the future
        station = initialStation; // Ensure maximum length?
        fuelGrade = initialFuelGrade; // Ensure maximum length?
        odometerReading = initialOdometerReading; // Ensure value is greater than zero, maximum value?
        fuelAmount = initialFuelAmount; // Ensure value is greater than zero, maximum value?
        fuelUnitCost = initialFuelUnitCost; // Ensure value is greater than zero, maximum value?
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date newDate) {
        date = newDate;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String newStation) {
        station = newStation;
    }

    public String getFuelGrade() {
        return fuelGrade;
    }

    public void setFuelGrade(String newFuelGrade) {
        fuelGrade = newFuelGrade;
    }

    public Double getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(Double newOdometerReading) {
        odometerReading = newOdometerReading;
    }

    public Double getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(Double newFuelAmount) {
        fuelAmount = newFuelAmount;
    }

    public Double getFuelUnitCost() {
        return fuelUnitCost;
    }

    public void setFuelUnitCost(Double newFuelUnitCost) {
        fuelUnitCost = newFuelUnitCost;
    }

    public Double getFuelCost() {
        return fuelAmount * fuelUnitCost;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        // Add date
        builder.append("Date: ");
        builder.append(new SimpleDateFormat("yyyy-MM-dd").format(date) + "\n");

        // Add Station
        builder.append("Station: " + station + "\n");

        // Add Fuel Grade
        builder.append("Grade: " + fuelGrade + "\n");

        // Add Odometer Reading
        builder.append("Odometer Reading: ");
        builder.append(new DecimalFormat("#0.0").format(odometerReading));
        builder.append("\n");

        // Add Fuel Amount
        builder.append("Amount: ");
        builder.append(new DecimalFormat("#0.000").format(fuelAmount));
        builder.append("\n");

        // Add Fuel Unit Cost
        builder.append("Unit Cost: ");
        builder.append(new DecimalFormat("#0.0").format(fuelUnitCost));
        builder.append("\n");

        // Add Fuel Cost
        builder.append("Cost: ");
        builder.append(new DecimalFormat("#0.00").format(getFuelCost()));
        builder.append("\n");

        return builder.toString();
    }

    // Custom equals(...) implementation to ensure accurate comparison
    @Override
    public boolean equals(Object object) {
        LogEntry otherEntry = (LogEntry) object;

        if (!otherEntry.getDate().equals(date)) { return false; }
        if (!otherEntry.getStation().equals(station)) { return false; }
        if (!otherEntry.getFuelGrade().equals(fuelGrade)) { return false; }
        if (!otherEntry.getOdometerReading().equals(odometerReading)) { return false; }
        if (!otherEntry.getFuelAmount().equals(fuelAmount)) { return false; }
        if (!otherEntry.getFuelUnitCost().equals(fuelUnitCost)) { return false; }
        if (!otherEntry.getFuelCost().equals(getFuelCost())) { return false; }

        return true;
    }
}
