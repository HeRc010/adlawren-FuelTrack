package com.fueltrack.adlawren.adlawren_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;

/**
 * Created by adlawren on 29/01/16.
 */
public class DisplayLogEntryDataStoreTest extends ActivityInstrumentationTestCase2 {
    public DisplayLogEntryDataStoreTest() {
        super(DisplayLogEntryActivity.class);
    }

    public void testUpdateLogEntry() {
        Date date = new Date(System.currentTimeMillis());
        String station = "Test Station";
        String fuelGrade = "Test Grade";
        Double odometerReading = 100000.0;
        Double fuelAmount = 22.3;
        Double fuelUnitCost = 1.25;

        LogEntry logEntry = new LogEntry(date,
                station,
                fuelGrade,
                odometerReading,
                fuelAmount,
                fuelUnitCost);

        DisplayLogEntryDataStore.getInstance().updateLogEntry(logEntry);

        assertEquals(logEntry, DisplayLogEntryDataStore.getInstance().getDisplayedEntry());
    }

    public void testUpdateDate() {
        Date date = new Date(0);

        DisplayLogEntryDataStore.getInstance().updateDate(date);

        assertEquals(date, DisplayLogEntryDataStore.getInstance().getDisplayedEntry().getDate());
    }

    public void testUpdateStation() {
        String station = "Test Station";

        DisplayLogEntryDataStore.getInstance().updateStation(station);

        assertEquals(station, DisplayLogEntryDataStore.getInstance().getDisplayedEntry().getStation());
    }

    public void testUpdateFuelGrade() {
        String fuelGrade = "Test Fuel Grade";

        DisplayLogEntryDataStore.getInstance().updateFuelGrade(fuelGrade);

        assertEquals(fuelGrade, DisplayLogEntryDataStore.getInstance().getDisplayedEntry().getFuelGrade());
    }

    public void testUpdateOdometerReading() {
        Double odometerReading = 100000.0;

        DisplayLogEntryDataStore.getInstance().updateOdometerReading(odometerReading);

        assertEquals(odometerReading, DisplayLogEntryDataStore.getInstance().getDisplayedEntry().getOdometerReading());
    }

    public void testUpdatedFuelAmount() {
        Double fuelAmount = 20.0;

        DisplayLogEntryDataStore.getInstance().updateFuelAmount(fuelAmount);

        assertEquals(fuelAmount, DisplayLogEntryDataStore.getInstance().getDisplayedEntry().getFuelAmount());
    }

    public void testUpdateFuelUnitCost() {
        Double fuelUnitCost = 1.5;

        DisplayLogEntryDataStore.getInstance().updateFuelUnitCost(fuelUnitCost);

        assertEquals(fuelUnitCost, DisplayLogEntryDataStore.getInstance().getDisplayedEntry().getFuelUnitCost());
    }

    public void testGetDisplayedEntry() {
        Date date = new Date(System.currentTimeMillis());
        String station = "Test Station";
        String fuelGrade = "Test Grade";
        Double odometerReading = 100000.0;
        Double fuelAmount = 22.3;
        Double fuelUnitCost = 1.25;

        LogEntry logEntry = new LogEntry(date,
                station,
                fuelGrade,
                odometerReading,
                fuelAmount,
                fuelUnitCost);

        DisplayLogEntryDataStore.getInstance().updateDate(date);
        DisplayLogEntryDataStore.getInstance().updateStation(station);
        DisplayLogEntryDataStore.getInstance().updateFuelGrade(fuelGrade);
        DisplayLogEntryDataStore.getInstance().updateOdometerReading(odometerReading);
        DisplayLogEntryDataStore.getInstance().updateFuelAmount(fuelAmount);
        DisplayLogEntryDataStore.getInstance().updateFuelUnitCost(fuelUnitCost);

        assertEquals(logEntry, DisplayLogEntryDataStore.getInstance().getDisplayedEntry());
    }
}
