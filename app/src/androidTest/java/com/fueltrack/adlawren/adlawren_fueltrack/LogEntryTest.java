package com.fueltrack.adlawren.adlawren_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adlawren on 29/01/16.
 */
public class LogEntryTest extends ActivityInstrumentationTestCase2 {
    public LogEntryTest() {
        super(FuelTrackActivity.class);
    }

    private void testLogEntryAgainstExpected(LogEntry logEntry,
                                             Date date,
                                             String station,
                                             String fuelGrade,
                                             Double odometerReading,
                                             Double fuelAmount,
                                             Double fuelUnitCost) {
        assertEquals(logEntry.getDate(), date);
        assertEquals(station, logEntry.getStation());
        assertEquals(fuelGrade, logEntry.getFuelGrade());
        assertEquals(odometerReading, logEntry.getOdometerReading());
        assertEquals(fuelAmount, logEntry.getFuelAmount());
        assertEquals(fuelUnitCost, logEntry.getFuelUnitCost());
        assertEquals(fuelAmount * fuelUnitCost, logEntry.getFuelCost());
    }

    public void testDefaultConstructor() {
        LogEntry logEntry = new LogEntry();

        // Retrieve the date; default constructor uses current time
        Date date = logEntry.getDate();

        testLogEntryAgainstExpected(logEntry,
                date,
                "",
                "",
                0.0,
                0.0,
                0.0);
    }

    public void testConstructor() {
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

        testLogEntryAgainstExpected(logEntry,
                date,
                station,
                fuelGrade,
                odometerReading,
                fuelAmount,
                fuelUnitCost);
    }

    public void testCopyConstructor() {
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

        LogEntry logEntryCopy = new LogEntry(logEntry);

        testLogEntryAgainstExpected(logEntryCopy,
                date,
                station,
                fuelGrade,
                odometerReading,
                fuelAmount,
                fuelUnitCost);
    }

    public void testDate() {
        Date date = new Date(0);

        LogEntry logEntry = new LogEntry();
        logEntry.setDate(date);

        assertEquals(date, logEntry.getDate());
    }

    public void testStation() {
        String station = "Test Station";

        LogEntry logEntry = new LogEntry();
        logEntry.setStation(station);

        assertEquals(station, logEntry.getStation());
    }

    public void testFuelGrade() {
        String fuelGrade = "Test Grade";

        LogEntry logEntry = new LogEntry();
        logEntry.setFuelGrade(fuelGrade);

        assertEquals(fuelGrade, logEntry.getFuelGrade());
    }

    public void testOdometerReading() {
        Double odometerReading = 100000.0;

        LogEntry logEntry = new LogEntry();
        logEntry.setOdometerReading(odometerReading);

        assertEquals(odometerReading, logEntry.getOdometerReading());
    }

    public void testFuelAmount() {
        Double fuelAmount = 22.0;

        LogEntry logEntry = new LogEntry();
        logEntry.setFuelAmount(fuelAmount);

        assertEquals(fuelAmount, logEntry.getFuelAmount());
    }

    public void testFuelUnitCost() {
        Double fuelUnitCost = 1.25;

        LogEntry logEntry = new LogEntry();
        logEntry.setFuelUnitCost(fuelUnitCost);

        assertEquals(logEntry.getFuelUnitCost(), fuelUnitCost);
    }

    public void testFuelTotalCost() {
        Double fuelAmount = 22.0;
        Double fuelUnitCost = 1.25;

        LogEntry logEntry = new LogEntry();
        logEntry.setFuelAmount(fuelAmount);
        logEntry.setFuelUnitCost(fuelUnitCost);

        assertEquals(fuelAmount * fuelUnitCost, logEntry.getFuelCost());
    }

    public void testToString() {
        Date date = new Date(0);
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

        String expectedString = "Date: " + new SimpleDateFormat("yyyy-MM-dd").format(date) + "\n" +
                                "Station: " + station + "\n" +
                                "Grade: " + fuelGrade + "\n" +
                                "Odometer Reading: " + new DecimalFormat("#0.0").format(odometerReading) + "\n" +
                                "Amount: " + new DecimalFormat("#0.000").format(fuelAmount) + "\n" +
                                "Unit Cost: " + new DecimalFormat("#0.0").format(fuelUnitCost) + "\n" +
                                "Cost: " + new DecimalFormat("#0.00").format(fuelAmount * fuelUnitCost) + "\n";

        assertEquals(expectedString, logEntry.toString());
    }

    public void testEquals() {
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

        LogEntry defaultEntry = new LogEntry();
        LogEntry logEntryCopy = new LogEntry(logEntry);

        assertFalse(logEntry.equals(defaultEntry));
        assertTrue(logEntry.equals(logEntryCopy));
    }
}
