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

    private static ArrayList<LogEntry> logEntries = new ArrayList<LogEntry>();
    private static ArrayAdapter<LogEntry> adapter = null;

    private void initializeArrayAdapter(Context context) {
        loadLogEntriesFromFile(context);
        adapter = new ArrayAdapter<LogEntry>(context, R.layout.log_entry, logEntries);
    }

    private void loadLogEntriesFromFile(Context context) {
        logEntries = new ArrayList<LogEntry>();
        try {
            FileInputStream stream = context.openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            Gson gson = new Gson();

            // Taken from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            Type listType = new TypeToken<ArrayList<LogEntry>>() {}.getType();
            logEntries = gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveLogEntriesToFile(Context context) {
        try {
            FileOutputStream stream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
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

    public ArrayAdapter<LogEntry> getLogEntryArrayAdapter(Context context) {

        if (adapter == null) {
            initializeArrayAdapter(context);
        }

        return adapter;
    }

    public void addLogEntry(Context context, LogEntry newEntry) {

        if (adapter == null) {
            initializeArrayAdapter(context);
        }

        logEntries.add(newEntry);
        adapter.notifyDataSetChanged();
    }

    public Double getTotalFuelCost() {
        Double totalCost = 0.0;
        for (LogEntry entry : logEntries) {
            totalCost += entry.getFuelCost();
        }

        return totalCost;
    }
}
