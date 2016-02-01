package com.fueltrack.adlawren.adlawren_fueltrack;

import java.util.Date;

/**
 * Created by adlawren on 26/01/16.
 *
 * Purpose: This class is used to store the data being rendered in the DisplayLogEntryActivity.
 *
 * Design: This class is implemented as a singleton. This class allows for the preliminary initialization
 *         of the LogEntry associated with the DisplayLogEntryActivity. If the user is editing an existing
 *         entry, the contents of the LogEntry stored internaly may be updated via a public method.
 *         Otherwise individual values of the LogEntry may be updated using the provided update methods.
 *         A method is provided, using which, the maintained LogEntry may be retrieved.
 *
 * Outstanding Issues: At present, none observed.
 *
 */
public class DisplayLogEntryDataStore {
    private static DisplayLogEntryDataStore instance = new DisplayLogEntryDataStore();

    public static DisplayLogEntryDataStore getInstance() {
        return instance;
    }

    private DisplayLogEntryDataStore() {
        displayedEntry = new LogEntry();
    }

    // The LogEntry associated with the information currently displayed in the DisplayLogEntryActivity
    private LogEntry displayedEntry = null;

    public void updateLogEntry(LogEntry newEntry) {
        displayedEntry = newEntry;
    }

    public void updateDate(Date newDate) {
        displayedEntry.setDate(newDate);
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
