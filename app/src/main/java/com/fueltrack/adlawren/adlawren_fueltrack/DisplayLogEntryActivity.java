package com.fueltrack.adlawren.adlawren_fueltrack;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DisplayLogEntryActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_log_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Acquire View handles
        TextView dateView = (TextView) findViewById(R.id.entry_date);

        EditText stationInput = (EditText) findViewById(R.id.station_input),
                gradeInput = (EditText) findViewById(R.id.grade_input),
                odometerInput = (EditText) findViewById(R.id.odometer_input),
                fuelAmountInput = (EditText) findViewById(R.id.fuel_amount_input),
                fuelUnitCostInput = (EditText) findViewById(R.id.fuel_unit_cost_input);

        TextView totalCostView = (TextView) findViewById(R.id.entry_total_cost);

        Button saveButton = (Button) findViewById(R.id.save_entry);

        // Assign Listeners from the DisplayLogEntryController
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this,
                                                DisplayLogEntryController.getInstance().getEntryDateOnDateSetListener(this),
                                                calendar.get(Calendar.YEAR),
                                                calendar.get(Calendar.MONTH),
                                                calendar.get(Calendar.DAY_OF_MONTH));

        stationInput.addTextChangedListener(DisplayLogEntryController.getInstance().getStationInputTextWatcher());
        gradeInput.addTextChangedListener(DisplayLogEntryController.getInstance().getFuelGradeInputTextWatcher());
        odometerInput.addTextChangedListener(DisplayLogEntryController.getInstance().getOdometerInputTextWatcher());
        fuelAmountInput.addTextChangedListener(DisplayLogEntryController.getInstance().getFuelAmountInputTextWatcher(this));
        fuelUnitCostInput.addTextChangedListener(DisplayLogEntryController.getInstance().getFuelUnitCostInputTextWatcher(this));

        saveButton.setOnClickListener(DisplayLogEntryController.getInstance().getSaveEntryOnClickListener(this, getIntent()));

        // Populate the views with the data found in the DisplayLogEntryDataStore
        LogEntry displayedEntry = DisplayLogEntryDataStore.getInstance().getDisplayedEntry();

        dateView.setText("Date: " + new SimpleDateFormat("yyyy-MM-dd").format(displayedEntry.getDate()));

        stationInput.setText(displayedEntry.getStation());
        gradeInput.setText(displayedEntry.getFuelGrade());
        odometerInput.setText(displayedEntry.getOdometerReading().toString());
        fuelAmountInput.setText(displayedEntry.getFuelAmount().toString());
        fuelUnitCostInput.setText(displayedEntry.getFuelUnitCost().toString());

        totalCostView.setText("Total Cost: $" + displayedEntry.getFuelCost().toString());

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
    }
}
