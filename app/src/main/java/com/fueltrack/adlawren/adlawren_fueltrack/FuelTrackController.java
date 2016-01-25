package com.fueltrack.adlawren.adlawren_fueltrack;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import java.io.Serializable;

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

        public SaveEntryOnClickListener(Context initialContext) {
            context = initialContext;
        }

        // TODO: implement
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, FuelTrackActivity.class);
            context.startActivity(intent);
        }
    }

    public SaveEntryOnClickListener getSaveEntryOnClickListener(Context context) {
        return new SaveEntryOnClickListener(context);
    }
}
