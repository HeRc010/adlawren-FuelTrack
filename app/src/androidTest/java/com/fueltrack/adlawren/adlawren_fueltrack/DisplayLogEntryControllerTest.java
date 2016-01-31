package com.fueltrack.adlawren.adlawren_fueltrack;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by adlawren on 30/01/16.
 */
public class DisplayLogEntryControllerTest extends ActivityInstrumentationTestCase2 {

    // TODO: remove; not needed?
    //private static final String NEW_LOG_ENTRY_EXTRA = "com.fueltrack.adlawren.adlawren_fuel_track.new.log.entry";

    public DisplayLogEntryControllerTest() {
        super(DisplayLogEntryActivity.class);
    }

    public void testGetEntryDateOnDateSetListener() {
        assertNotNull(DisplayLogEntryController.getInstance().getEntryDateOnDateSetListener(this.getActivity()));
    }

    public void testGetStationInputTextWatcher() {
        assertNotNull(DisplayLogEntryController.getInstance().getStationInputTextWatcher());
    }

    public void testGetFuelGradeInputTextWatcher() {
        assertNotNull(DisplayLogEntryController.getInstance().getFuelGradeInputTextWatcher());
    }

    public void testGetOdometerInputTextWatcher() {
        assertNotNull(DisplayLogEntryController.getInstance().getOdometerInputTextWatcher());
    }

    public void testGetFuelAmountInputTextWatcher() {
        assertNotNull(DisplayLogEntryController.getInstance().getFuelAmountInputTextWatcher(this.getActivity()));
    }

    public void testGetFuelUnitCostInputTextWatcher() {
        assertNotNull(DisplayLogEntryController.getInstance().getFuelUnitCostInputTextWatcher(this.getActivity()));
    }

    public void testGetSaveEntryOnClickListener() {
        Intent intent = new Intent();
        // intent.putExtra(NEW_LOG_ENTRY_EXTRA, false); // TODO: remove; not needed?

        assertNotNull(DisplayLogEntryController.getInstance().getSaveEntryOnClickListener(this.getActivity(), intent));
    }
}

