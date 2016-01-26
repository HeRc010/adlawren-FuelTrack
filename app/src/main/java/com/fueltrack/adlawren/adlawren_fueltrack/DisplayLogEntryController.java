package com.fueltrack.adlawren.adlawren_fueltrack;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

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

    private class StationInputTextWatcher implements TextWatcher {

        @Override
        public void afterTextChanged(Editable editable) {
            DisplayLogEntryDataStore.getInstance().updateStation(editable.toString());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }

    public StationInputTextWatcher getStationInputTextWatcher() {
        return new StationInputTextWatcher();
    }

    private class FuelGradeInputTextWatcher implements TextWatcher {

        @Override
        public void afterTextChanged(Editable editable) {
            DisplayLogEntryDataStore.getInstance().updateFuelGrade(editable.toString());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }

    public FuelGradeInputTextWatcher getFuelGradeInputTextWatcher() {
        return new FuelGradeInputTextWatcher();
    }

    private class OdometerInputTextWatcher implements TextWatcher {

        @Override
        public void afterTextChanged(Editable editable) {
            DisplayLogEntryDataStore.getInstance().updateOdometerReading(Double.valueOf(editable.toString()));
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }

    public OdometerInputTextWatcher getOdometerInputTextWatcher() {
        return new OdometerInputTextWatcher();
    }

    private class FuelAmountTextWatcher implements TextWatcher {

        @Override
        public void afterTextChanged(Editable editable) {
            DisplayLogEntryDataStore.getInstance().updateFuelAmount(Double.valueOf(editable.toString()));
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }

    public FuelAmountTextWatcher getFuelAmountInputTextWatcher() {
        return new FuelAmountTextWatcher();
    }

    private class FuelUnitCostTextWatcher implements TextWatcher {

        @Override
        public void afterTextChanged(Editable editable) {
            DisplayLogEntryDataStore.getInstance().updateFuelUnitCost(Double.valueOf(editable.toString()));
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }

    public FuelUnitCostTextWatcher getFuelUnitCostInputTextWatcher() {
        return new FuelUnitCostTextWatcher();
    }

    private class SaveEntryOnClickListener implements View.OnClickListener {
        private Context context;
        private Intent displayIntent;

        public SaveEntryOnClickListener(Context initialContext, Intent initialDisplayIntent) {
            context = initialContext;
            displayIntent = initialDisplayIntent;
        }

        @Override
        public void onClick(View view) {
            LogEntry displayedEntry = DisplayLogEntryDataStore.getInstance().getDisplayedEntry();

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

    public SaveEntryOnClickListener getSaveEntryOnClickListener(Context context, Intent displayIntent) {
        return new SaveEntryOnClickListener(context, displayIntent);
    }
}
