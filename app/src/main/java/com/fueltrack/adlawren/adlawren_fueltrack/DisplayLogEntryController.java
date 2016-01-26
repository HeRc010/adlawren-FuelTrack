package com.fueltrack.adlawren.adlawren_fueltrack;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by adlawren on 26/01/16.
 */
public class DisplayLogEntryController {
    private static DisplayLogEntryController ourInstance = new DisplayLogEntryController();

    public static DisplayLogEntryController getInstance() {
        return ourInstance;
    }

    private DisplayLogEntryController() {

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
            } else if ((new Double(odometerInput.getText().toString())).compareTo(new Double(0.0)) < 0) {
                displayedEntry.setOdometerReading(new Double(0.0));
            } else {
                displayedEntry.setOdometerReading(new Double(odometerInput.getText().toString()));
            }

            if (fuelAmountInput.getText().toString().equals("")) {
                displayedEntry.setFuelAmount(new Double(0.0));
            } else if ((new Double(fuelAmountInput.getText().toString())).compareTo(new Double(0.0)) < 0) {
                displayedEntry.setFuelAmount(new Double(0.0));
            } else {
                displayedEntry.setFuelAmount(new Double(fuelAmountInput.getText().toString()));
            }

            if (fuelUnitCostInput.getText().toString().equals("")) {
                displayedEntry.setFuelUnitCost(new Double(0.0));
            } else if ((new Double(fuelUnitCostInput.getText().toString())).compareTo(new Double(0.0)) < 0) {
                displayedEntry.setFuelUnitCost(new Double(0.0));
            } else {
                displayedEntry.setFuelUnitCost(new Double(fuelUnitCostInput.getText().toString()));
            }

            if (displayIntent.getBooleanExtra(FuelTrackController.NEW_LOG_ENTRY_EXTRA, false)) {
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
