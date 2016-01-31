package com.fueltrack.adlawren.adlawren_fueltrack;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class FuelTrackActivity extends AppCompatActivity {

    // The list of LogEntries
    private ListView previousLogEntriesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_track);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Acquire handles to Views and assign Callbacks
        previousLogEntriesView = (ListView) findViewById(R.id.previous_log_entries);
        previousLogEntriesView.setOnItemClickListener(FuelTrackController.getInstance().getLogEntryOnClickListener(this));

        FloatingActionButton addNewEntryButton = (FloatingActionButton) findViewById(R.id.add_new_log_entry);
        addNewEntryButton.setOnClickListener(FuelTrackController.getInstance().getNewEntryOnClickListener(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Acquire adapter to the list of LogEntries
        previousLogEntriesView.setAdapter(FuelTrackDataStore.getInstance().getLogEntryArrayAdapter(this));

        // Compute the total fuel cost and illustrate in the TotalCost TextView
        TextView totalCostView = (TextView) findViewById(R.id.total_cost);
        totalCostView.setText("Total Cost: $" + new DecimalFormat("#0.00").format(FuelTrackDataStore.getInstance().getTotalFuelCost()));
    }
}
