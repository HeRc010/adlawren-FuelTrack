package com.fueltrack.adlawren.adlawren_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by adlawren on 30/01/16.
 */
public class FuelTrackControllerTest extends ActivityInstrumentationTestCase2 {
    public FuelTrackControllerTest() {
        super(FuelTrackActivity.class);
    }

    public void testGetLogEntryOnClickListener() {
        assertNotNull(FuelTrackController.getInstance().getLogEntryOnClickListener(this.getActivity()));
    }

    public void testGetNewEntryOnClickListener() {
        assertNotNull(FuelTrackController.getInstance().getNewEntryOnClickListener(this.getActivity()));
    }
}
