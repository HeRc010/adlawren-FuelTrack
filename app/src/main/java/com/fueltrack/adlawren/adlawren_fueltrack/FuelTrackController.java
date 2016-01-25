package com.fueltrack.adlawren.adlawren_fueltrack;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by adlawren on 24/01/16.
 */
public class FuelTrackController {
    private static FuelTrackController instance = new FuelTrackController();

    public static FuelTrackController getInstance() {
        return instance;
    }

    private FuelTrackController() {

    }

    public static final String LOG_ENTRY_EXTRA = "com.fueltrack.adlawren.adlawren_fuel_track.log.entry";
    public static final String NEW_LOG_ENTRY_EXTRA = "com.fueltrack.adlawren.adlawren_fuel_track.new.log.entry";

    class LogEntryOnItemClickListener implements AdapterView.OnItemClickListener {
        private Context context;

        public LogEntryOnItemClickListener(Context initialContext) {
            context = initialContext;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(context, DisplayLogEntryActivity.class);
            intent.putExtra(LOG_ENTRY_EXTRA, (Serializable) parent.getItemAtPosition(position));
            intent.putExtra(NEW_LOG_ENTRY_EXTRA, false);
            context.startActivity(intent);
        }
    }

    public LogEntryOnItemClickListener getLogEntryOnClickListener(Context context) {
        return new LogEntryOnItemClickListener(context);
    }

    class AddNewEntryOnClickListener implements View.OnClickListener {
        private Context context;

        public AddNewEntryOnClickListener(Context initialContext) {
            context = initialContext;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DisplayLogEntryActivity.class);
            intent.putExtra(NEW_LOG_ENTRY_EXTRA, true);
            context.startActivity(intent);
        }
    }

    public AddNewEntryOnClickListener getNewEntryOnClickListener(Context context) {
        return new AddNewEntryOnClickListener(context);
    }

    class SaveEntryOnClickListener implements View.OnClickListener {
        private Context context;
        private Intent displayIntent;

        private EditText stationInput,
                         gradeInput,
                         odometerInput,
                         fuelAmountInput,
                         fuelUnitCostInput;

        public SaveEntryOnClickListener(Context initialContext,
                                        Intent initialDisplayIntent,
                                        EditText initialStationInput,
                                        EditText initialGradeInput,
                                        EditText initialOdometerInput,
                                        EditText initialFuelAmountInput,
                                        EditText initialFuelUnitCostInput) {
            context = initialContext;
            displayIntent = initialDisplayIntent;

            stationInput = initialStationInput;
            gradeInput = initialGradeInput;
            odometerInput = initialOdometerInput;
            fuelAmountInput = initialFuelAmountInput;
            fuelUnitCostInput = initialFuelUnitCostInput;
        }

        // TODO: implement
        @Override
        public void onClick(View view) {

            LogEntry displayedEntry = new LogEntry(); //DisplayLogEntryActivity.getDisplayedLogEntry();

            // TODO: add functionality to edit date
            displayedEntry.setStation(stationInput.getText().toString());
            displayedEntry.setFuelGrade(gradeInput.getText().toString());

            if (odometerInput.getText().toString().equals("")) {
                displayedEntry.setOdometerReading(new Double(0.0));
            } else {
                displayedEntry.setOdometerReading(new Double(odometerInput.getText().toString()));
            }

            if (fuelAmountInput.getText().toString().equals("")) {
                displayedEntry.setFuelAmount(new Double(0.0));
            } else {
                displayedEntry.setFuelAmount(new Double(fuelAmountInput.getText().toString()));
            }

            if (fuelUnitCostInput.getText().toString().equals("")) {
                displayedEntry.setFuelUnitCost(new Double(0.0));
            } else {
                displayedEntry.setFuelUnitCost(new Double(fuelUnitCostInput.getText().toString()));
            }

            if (displayIntent.getBooleanExtra(NEW_LOG_ENTRY_EXTRA, false)) {
                FuelTrackDataStore.getInstance().addLogEntry(context, displayedEntry);
            } else {
                LogEntry existingEntry = (LogEntry) displayIntent.getSerializableExtra(FuelTrackController.LOG_ENTRY_EXTRA);
                FuelTrackDataStore.getInstance().updateLogEntry(context, existingEntry, displayedEntry);
            }

            Intent intent = new Intent(context, FuelTrackActivity.class);
            context.startActivity(intent);
        }
    }

    public SaveEntryOnClickListener getSaveEntryOnClickListener(Context context,
                                                                Intent displayIntent,
                                                                EditText stationInput,
                                                                EditText gradeInput,
                                                                EditText odometerInput,
                                                                EditText fuelAmountInput,
                                                                EditText fuelUnitCostInput) {
        return new SaveEntryOnClickListener(context,
                                            displayIntent,
                                            stationInput,
                                            gradeInput,
                                            odometerInput,
                                            fuelAmountInput,
                                            fuelUnitCostInput);
    }
}
