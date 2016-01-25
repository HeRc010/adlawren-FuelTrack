package com.fueltrack.adlawren.adlawren_fueltrack;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

class LogEntryOnItemClickListener implements AdapterView.OnItemClickListener {

    private FuelTrackActivity fuelTrackActivity;

    public LogEntryOnItemClickListener(FuelTrackActivity activity) {
        this.fuelTrackActivity = activity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this.fuelTrackActivity, DisplayLogEntryActivity.class);
        intent.putExtra(FuelTrackActivity.EXTRA_MESSAGE, (Serializable) parent.getItemAtPosition(position));
        fuelTrackActivity.startActivity(intent);
    }
}

public class FuelTrackActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.fueltrack.adlawren.adlawren_fuel_track";
    private static final String FILENAME = "previous_log_entries.sav";
    private ListView previousLogEntriesView;

    private ArrayList<LogEntry> logEntries = new ArrayList<LogEntry>();
    private ArrayAdapter<LogEntry> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_track);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        previousLogEntriesView = (ListView) findViewById(R.id.previous_log_entries);
        previousLogEntriesView.setOnItemClickListener(new LogEntryOnItemClickListener(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO: remove; test
                logEntries.add(new LogEntry());
                adapter.notifyDataSetChanged();

                updateTotalCost();

                saveToFile();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadFromFile();

        adapter = new ArrayAdapter<LogEntry>(this, R.layout.log_entry, logEntries);
        previousLogEntriesView.setAdapter(adapter);

        updateTotalCost();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fuel_track, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateTotalCost() {

        // Recompute total cost
        Double totalCost = 0.0;
        for (LogEntry entry : logEntries) {
            totalCost += entry.getFuelCost();
        }

        TextView totalCostView = (TextView) findViewById(R.id.total_cost);
        totalCostView.setText("Total Cost: $" + totalCost.toString());
    }

    private void loadFromFile() {
        logEntries = new ArrayList<LogEntry>();
        try {
            FileInputStream stream = openFileInput(FILENAME);
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

    private void saveToFile() {
        try {
            FileOutputStream stream = openFileOutput(FILENAME, Context.MODE_PRIVATE);

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
}
