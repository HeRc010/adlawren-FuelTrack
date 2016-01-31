package com.fueltrack.adlawren.adlawren_fueltrack;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by adlawren on 24/01/16.
 */
public class FuelTrackDataStore {
    private static FuelTrackDataStore instance = new FuelTrackDataStore();

    public static FuelTrackDataStore getInstance() {
        return instance;
    }

    private FuelTrackDataStore() {
    }

    private static final String FILENAME = "previous_log_entries.sav";

    private void loadLogEntriesFromFile(Context context) {
        logEntries = new ArrayList<LogEntry>();
        try {

            // Initialize BufferedReader using stream from the given file
            FileInputStream stream = context.openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            Gson gson = new Gson();

            // Taken from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            // Parse the Json stored in the file
            Type listType = new TypeToken<ArrayList<LogEntry>>() {}.getType();
            logEntries = gson.fromJson(reader, listType);

            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLogEntriesToFile(Context context) {
        try {

            // Initialize BufferedWriter using stream from the given file
            FileOutputStream stream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));

            // Write the Json to the given file
            Gson gson = new Gson();
            gson.toJson(logEntries, writer);
            writer.flush();

            stream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static ArrayList<LogEntry> logEntries = new ArrayList<LogEntry>();
    private static ArrayAdapter<LogEntry> adapter = null;

    private void initializeArrayAdapter(Context context) {

        // Load the LogEntries from the given file and construct the ArrayAdapter
        loadLogEntriesFromFile(context);
        adapter = new ArrayAdapter<LogEntry>(context, R.layout.log_entry, logEntries);
    }

    public ArrayAdapter<LogEntry> getLogEntryArrayAdapter(Context context) {

        // Ensure the ArrayAdapter has been initialized
        if (adapter == null) {
            initializeArrayAdapter(context);
        }

        return adapter;
    }

    public void addLogEntry(Context context, LogEntry newEntry) {

        // Ensure the ArrayAdapter has been initialized
        if (adapter == null) {
            initializeArrayAdapter(context);
        }

        // Add the newEntry to the ArrayList
        logEntries.add(newEntry);

        // Update the ArrayAdapter and file system
        adapter.notifyDataSetChanged();
        saveLogEntriesToFile(context);
    }

    public void updateLogEntry(Context context, LogEntry oldEntry, LogEntry newEntry) {

        // Ensure the ArrayAdapter has been initialized
        if (adapter == null) {
            initializeArrayAdapter(context);
        }

        // Attempt to find the index of the oldEntry in the ArrayList
        int oldIndex = logEntries.indexOf(oldEntry);
        if (oldIndex == -1) {

            // No LogEntry found which resembles the oldEntry
            return;
        }

        // Replace the oldEntry with the newEntry
        logEntries.set(oldIndex, newEntry);

        // Update the ArrayAdapter and file system
        adapter.notifyDataSetChanged();
        saveLogEntriesToFile(context);
    }

    // Compute the total cost from the list of LogEntries
    public Double getTotalFuelCost() {
        Double totalCost = 0.0;
        for (LogEntry entry : logEntries) {
            totalCost += entry.getFuelCost();
        }

        return totalCost;
    }
}
