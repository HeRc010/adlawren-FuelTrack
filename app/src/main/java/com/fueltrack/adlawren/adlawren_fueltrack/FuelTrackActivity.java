package com.fueltrack.adlawren.adlawren_fueltrack;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class FuelTrackActivity extends AppCompatActivity {
    private ListView previousLogEntriesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_track);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        previousLogEntriesView = (ListView) findViewById(R.id.previous_log_entries);
        previousLogEntriesView.setOnItemClickListener(FuelTrackController.getInstance().getLogEntryOnClickListener(this));

        FloatingActionButton addNewEntryButton = (FloatingActionButton) findViewById(R.id.add_new_log_entry);
        addNewEntryButton.setOnClickListener(FuelTrackController.getInstance().getNewEntryOnClickListener(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        previousLogEntriesView.setAdapter(FuelTrackDataStore.getInstance().getLogEntryArrayAdapter(this));

        TextView totalCostView = (TextView) findViewById(R.id.total_cost);
        totalCostView.setText("Total Cost: $" + FuelTrackDataStore.getInstance().getTotalFuelCost().toString());
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
}
