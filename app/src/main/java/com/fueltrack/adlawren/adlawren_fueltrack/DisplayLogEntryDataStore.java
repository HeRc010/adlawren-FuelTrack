package com.fueltrack.adlawren.adlawren_fueltrack;

import java.util.Date;

/**
 * Created by adlawren on 26/01/16.
 */
public class DisplayLogEntryDataStore {
    private static DisplayLogEntryDataStore ourInstance = new DisplayLogEntryDataStore();

    public static DisplayLogEntryDataStore getInstance() {
        return ourInstance;
    }

    private DisplayLogEntryDataStore() {
        displayedEntry = new LogEntry();
    }

    // The LogEntry associated with the information currently displayed in the DisplayLogEntryActivity
    private LogEntry displayedEntry = null;

    public void updatedDate(Date newDate) {
        displayedEntry.setDate(newDate);
    }

    public void updateLogEntry(LogEntry newEntry) {
        displayedEntry = newEntry;
    }

    public void updateStation(String newStation) {
        displayedEntry.setStation(newStation);
    }

    public void updateFuelGrade(String newFuelGrade) {
        displayedEntry.setFuelGrade(newFuelGrade);
    }

    public void updateOdometerReading(Double newOdometerReading) {
        displayedEntry.setOdometerReading(newOdometerReading);
    }

    public void updateFuelAmount(Double newFuelAmount) {
        displayedEntry.setFuelAmount(newFuelAmount);
    }

    public void updateFuelUnitCost(Double newFuelUnitCost) {
        displayedEntry.setFuelUnitCost(newFuelUnitCost);
    }

    public LogEntry getDisplayedEntry() {
        return displayedEntry;
    }
}
