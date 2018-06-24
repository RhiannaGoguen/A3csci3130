package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, emailField, addressField;
    private Spinner businessList, provinceList;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        addressField = (EditText) findViewById(R.id.address);

        businessList = (Spinner) findViewById(R.id.business);
        provinceList = (Spinner) findViewById(R.id.province);

        ArrayAdapter<CharSequence> businessAdapter = ArrayAdapter.createFromResource(this,
                R.array.business_array, android.R.layout.simple_spinner_item);
        businessAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        businessList.setAdapter(businessAdapter);

        ArrayAdapter<CharSequence> provinceAdapter = ArrayAdapter.createFromResource(this,
                R.array.province_array, android.R.layout.simple_spinner_item);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceList.setAdapter(provinceAdapter);

        businessList.setOnItemSelectedListener(new SpinnerView());
        provinceList.setOnItemSelectedListener(new SpinnerView());

    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String address = addressField.getText().toString();
        String province = String.valueOf(provinceList.getSelectedItem());
        String business = String.valueOf(businessList.getSelectedItem());
        Contact person = new Contact(personID, name, email, province, address, business);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }

}
