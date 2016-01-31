package com.fueltrack.adlawren.adlawren_fueltrack;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by adlawren on 29/01/16.
 */
public class FuelTrackDataStoreTest extends ActivityInstrumentationTestCase2 {

    //private static ArrayAdapter<LogEntry> adapter = null;
    private static final String FILENAME = "previous_log_entries.sav";

    public FuelTrackDataStoreTest() {
        super(FuelTrackActivity.class);
    }

    public void setUp() {
        // Remove any existing data file
        // ...

        // TODO: remove; test
        System.out.println("In the setup.");
    }

    public void testGetLogEntryArrayAdapter() {
        ArrayAdapter<LogEntry> adapter = FuelTrackDataStore.getInstance().getLogEntryArrayAdapter(this.getActivity());
        assertNotNull(adapter);
    }

    public void testAddLogEntry() {

        // Add a new log entry and examine the contents of the file for the new entry
        LogEntry logEntry = new LogEntry();

        String station = "Test Station";
        logEntry.setStation(station);

        // FuelTrackDataStore.getInstance().addLogEntry(this.getActivity(), logEntry);

        /*
        try {
            // Initialize BufferedReader using stream from the given file
            FileInputStream stream = this.getActivity().openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            Gson gson = new Gson();

            // Taken from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            // Parse the Json stored in the file
            Type listType = new TypeToken<ArrayList<LogEntry>>() {}.getType();
            ArrayList<LogEntry> logEntries = gson.fromJson(reader, listType);

            assertEquals(1, logEntries.size());
            assertEquals(logEntry, logEntries.get(0));

            stream.close();
        } catch (Exception e) {
            fail();
        }
        */
    }

    /*
    public void testUpdateLogEntry() {
        ArrayAdapter<LogEntry> adapter = FuelTrackDataStore.getInstance().getLogEntryArrayAdapter(this.getActivity());

        // Clear the contents of the adapter
        adapter.clear();
        adapter.notifyDataSetChanged();

        // Add a LogEntry
        assertEquals(0, FuelTrackDataStore.getInstance().getLogEntryArrayAdapter(this.getActivity()).getCount());

        LogEntry logEntry = new LogEntry();
        adapter.add(logEntry);

        assertEquals(1, FuelTrackDataStore.getInstance().getLogEntryArrayAdapter(this.getActivity()).getCount());

        // Update the LogEntry and verify that the internally stored ArrayAdapter contains the updated LogEntry
        LogEntry newLogEntry = new LogEntry();

        String station = "Test Station";
        newLogEntry.setStation(station);

        FuelTrackDataStore.getInstance().updateLogEntry(this.getActivity(), logEntry, newLogEntry);

        assertEquals(1, FuelTrackDataStore.getInstance().getLogEntryArrayAdapter(this.getActivity()).getCount());

        LogEntry storedEntry = FuelTrackDataStore.getInstance().getLogEntryArrayAdapter(this.getActivity()).getItem(0);
        assertEquals(station, storedEntry.getStation());
    }
    */

    public void tearDown() {
        // Remove the data file used for testing
        // ...

        // TODO: remove; test
        System.out.println("In the tear down.");
    }
}
