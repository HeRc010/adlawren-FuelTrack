package com.fueltrack.adlawren.adlawren_fueltrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayLogEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_log_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        boolean newEntry = getIntent().getBooleanExtra(FuelTrackController.NEW_LOG_ENTRY_EXTRA, false);

        // TODO: implement
        if (newEntry) {

            // TODO: remove, test
            System.out.println("New LogEntry processed in DisplayLogEntryActivity");

            // ...
        } else {
            LogEntry logEntry = (LogEntry) getIntent().getSerializableExtra(FuelTrackController.LOG_ENTRY_EXTRA);

            EditText stationInput = (EditText) findViewById(R.id.station_input),
                    gradeInput = (EditText) findViewById(R.id.grade_input),
                    odometerInput = (EditText) findViewById(R.id.odometer_input),
                    fuelAmountInput = (EditText) findViewById(R.id.fuel_amount_input),
                    fuelUnitCostInput = (EditText) findViewById(R.id.fuel_unit_cost_input);

            TextView totalCostView = (TextView) findViewById(R.id.entry_total_cost);

            stationInput.setText(logEntry.getStation());
            gradeInput.setText(logEntry.getFuelGrade());
            odometerInput.setText(logEntry.getOdometerReading().toString());
            fuelAmountInput.setText(logEntry.getFuelAmount().toString());
            fuelUnitCostInput.setText(logEntry.getFuelUnitCost().toString());

            totalCostView.setText("Total Cost: $" + logEntry.getFuelCost().toString());
        }

        Button saveButton = (Button) findViewById(R.id.save_entry);
        saveButton.setOnClickListener(FuelTrackController.getInstance().getSaveEntryOnClickListener(this));
    }

}
