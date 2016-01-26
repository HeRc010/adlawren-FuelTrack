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

        // TODO: this currently doesn't dynamically update as the user enters new values, need to change that
        TextView totalCostView = (TextView) findViewById(R.id.entry_total_cost);

        boolean newEntry = getIntent().getBooleanExtra(FuelTrackController.NEW_LOG_ENTRY_EXTRA, false);
        if (!newEntry) {
            LogEntry logEntry = (LogEntry) getIntent().getSerializableExtra(FuelTrackController.LOG_ENTRY_EXTRA);

            dateView.setText("Date: " + new SimpleDateFormat("yyyy-MM-dd").format(logEntry.getDate()));

            stationInput.setText(logEntry.getStation());
            gradeInput.setText(logEntry.getFuelGrade());
            odometerInput.setText(logEntry.getOdometerReading().toString());
            fuelAmountInput.setText(logEntry.getFuelAmount().toString());
            fuelUnitCostInput.setText(logEntry.getFuelUnitCost().toString());

            totalCostView.setText("Total Cost: $" + logEntry.getFuelCost().toString());
        }

        Button saveButton = (Button) findViewById(R.id.save_entry);
        saveButton.setOnClickListener(DisplayLogEntryController.getInstance().getSaveEntryOnClickListener(this,
                                                                                                    getIntent(),
                                                                                                    stationInput,
                                                                                                    gradeInput,
                                                                                                    odometerInput,
                                                                                                    fuelAmountInput,
                                                                                                    fuelUnitCostInput));

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

    // TODO: implement?
    public static LogEntry getDisplayedLogEntry() {
        LogEntry displayedEntry = new LogEntry();

        // ...

        return displayedEntry;
    }

}
