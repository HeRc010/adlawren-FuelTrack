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

    public static final String EXTRA_MESSAGE = "com.fueltrack.adlawren.adlawren_fuel_track";

    class LogEntryOnItemClickListener implements AdapterView.OnItemClickListener {
        private Context context;

        public LogEntryOnItemClickListener(Context initialContext) {
            context = initialContext;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(context, DisplayLogEntryActivity.class);
            intent.putExtra(EXTRA_MESSAGE, (Serializable) parent.getItemAtPosition(position));
            context.startActivity(intent);
        }
    }

    public LogEntryOnItemClickListener getLogEntryOnClickListener(FuelTrackActivity activity) {
        return new LogEntryOnItemClickListener(activity);
    }

    class AddNewEntryOnClickListener implements View.OnClickListener {
        private Context context;

        public AddNewEntryOnClickListener(Context initialContext) {
            context = initialContext;
        }

        // TODO: implement
        @Override
        public void onClick(View view) {

            // TODO: remove; test
            System.out.println("Test: From new Listener");
        }
    }

    public AddNewEntryOnClickListener getNewEntryOnClickListener(FuelTrackActivity activity) {
        return new AddNewEntryOnClickListener(activity);
    }
}
