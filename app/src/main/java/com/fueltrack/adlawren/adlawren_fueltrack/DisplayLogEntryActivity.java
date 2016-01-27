package com.fueltrack.adlawren.adlawren_fueltrack;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

        // TODO: add functionality to edit date
        TextView dateView = (TextView) findViewById(R.id.entry_date);

        EditText stationInput = (EditText) findViewById(R.id.station_input),
                gradeInput = (EditText) findViewById(R.id.grade_input),
                odometerInput = (EditText) findViewById(R.id.odometer_input),
                fuelAmountInput = (EditText) findViewById(R.id.fuel_amount_input),
                fuelUnitCostInput = (EditText) findViewById(R.id.fuel_unit_cost_input);

        stationInput.addTextChangedListener(DisplayLogEntryController.getInstance().getStationInputTextWatcher());
        gradeInput.addTextChangedListener(DisplayLogEntryController.getInstance().getFuelGradeInputTextWatcher());
        odometerInput.addTextChangedListener(DisplayLogEntryController.getInstance().getOdometerInputTextWatcher());
        fuelAmountInput.addTextChangedListener(DisplayLogEntryController.getInstance().getFuelAmountInputTextWatcher(this));
        fuelUnitCostInput.addTextChangedListener(DisplayLogEntryController.getInstance().getFuelUnitCostInputTextWatcher(this));

        // TODO: this currently doesn't dynamically update as the user enters new values, need to change that
        TextView totalCostView = (TextView) findViewById(R.id.entry_total_cost);
        totalCostView.setText("Total Cost: $0.00");

        boolean newEntry = getIntent().getBooleanExtra(FuelTrackController.NEW_LOG_ENTRY_EXTRA, false);
        if (!newEntry) {
            LogEntry existingEntry = DisplayLogEntryDataStore.getInstance().getDisplayedEntry();

            dateView.setText("Date: " + new SimpleDateFormat("yyyy-MM-dd").format(existingEntry.getDate()));

            stationInput.setText(existingEntry.getStation());
            gradeInput.setText(existingEntry.getFuelGrade());
            odometerInput.setText(existingEntry.getOdometerReading().toString());
            fuelAmountInput.setText(existingEntry.getFuelAmount().toString());
            fuelUnitCostInput.setText(existingEntry.getFuelUnitCost().toString());

            totalCostView.setText("Total Cost: $" + existingEntry.getFuelCost().toString());
        }

        Button saveButton = (Button) findViewById(R.id.save_entry);
        saveButton.setOnClickListener(DisplayLogEntryController.getInstance().getSaveEntryOnClickListener(this, getIntent()));

        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        // TODO: update
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int month, int day) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, day);
                System.out.println(newDate.toString());
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
}
