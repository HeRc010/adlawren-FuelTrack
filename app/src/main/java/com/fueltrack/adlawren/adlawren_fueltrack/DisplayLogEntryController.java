package com.fueltrack.adlawren.adlawren_fueltrack;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by adlawren on 26/01/16.
 */
public class DisplayLogEntryController {
    private static DisplayLogEntryController instance = new DisplayLogEntryController();

    public static DisplayLogEntryController getInstance() {
        return instance;
    }

    private DisplayLogEntryController() {
    }

    private class EntryDateOnDateSetListener implements DatePickerDialog.OnDateSetListener {
        Context context;

        public EntryDateOnDateSetListener(Context initialContext) {
            context = initialContext;
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, month, day);

            DisplayLogEntryDataStore.getInstance().updatedDate(new Date(newDate.getTimeInMillis()));

            // TODO: find an alternative method; this is hacky
            // Update the EntryTotalCost TextView
            Activity activity = (Activity) context;

            TextView entryDateView = (TextView) activity.findViewById(R.id.entry_date);
            entryDateView.setText("Date: " + new SimpleDateFormat("yyyy-MM-dd").format(DisplayLogEntryDataStore.getInstance().getDisplayedEntry().getDate()));
        }
    }

    public EntryDateOnDateSetListener getEntryDateOnDateSetListener(Context context) {
        return new EntryDateOnDateSetListener(context);
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
            if (editable.toString().equals("")) {
                DisplayLogEntryDataStore.getInstance().updateOdometerReading(0.0);
            } else {
                DisplayLogEntryDataStore.getInstance().updateOdometerReading(Double.valueOf(editable.toString()));
            }
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

        private Context context;

        public FuelAmountTextWatcher(Context initialContext) {
            context = initialContext;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().equals("")) {
                DisplayLogEntryDataStore.getInstance().updateFuelAmount(0.0);
            } else {
                DisplayLogEntryDataStore.getInstance().updateFuelAmount(Double.valueOf(editable.toString()));
            }

            // TODO: find an alternative method; this is hacky
            // Update the EntryTotalCost TextView
            Activity activity = (Activity) context;

            TextView entryTotalCostView = (TextView) activity.findViewById(R.id.entry_total_cost);
            entryTotalCostView.setText("Total Cost: $" + DisplayLogEntryDataStore.getInstance().getDisplayedEntry().getFuelCost().toString());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }

    public FuelAmountTextWatcher getFuelAmountInputTextWatcher(Context context) {
        return new FuelAmountTextWatcher(context);
    }

    private class FuelUnitCostTextWatcher implements TextWatcher {

        Context context;

        public FuelUnitCostTextWatcher(Context initialContext) {
            context = initialContext;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().equals("")) {
                DisplayLogEntryDataStore.getInstance().updateFuelUnitCost(0.0);
            } else {
                DisplayLogEntryDataStore.getInstance().updateFuelUnitCost(Double.valueOf(editable.toString()));
            }

            // TODO: find an alternative method; this is hacky
            // Update the EntryTotalCost TextView
            Activity activity = (Activity) context;

            TextView entryTotalCostView = (TextView) activity.findViewById(R.id.entry_total_cost);
            entryTotalCostView.setText("Total Cost: $" + DisplayLogEntryDataStore.getInstance().getDisplayedEntry().getFuelCost().toString());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    }

    public FuelUnitCostTextWatcher getFuelUnitCostInputTextWatcher(Context context) {
        return new FuelUnitCostTextWatcher(context);
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
