package com.acme.a3csci3130;

import android.view.View;
import android.widget.AdapterView;

/**
 * This is a class for the Spinners in the CreateContactActivity and in
 * the DetailViewActivity. According to the Spinner page in the Android
 * Studio documentation, they need a class like this to work.
 */
public class SpinnerView implements AdapterView.OnItemSelectedListener {
    String choice;

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
         choice = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
