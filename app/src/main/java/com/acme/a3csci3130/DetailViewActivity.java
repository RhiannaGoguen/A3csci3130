package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;

/**
 * This class shows a view of a selected contact and allows you to see all its properties
 * and lets you have the option of deleting or updating the Contact and its values.
 */

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField, addressField, numberField;
    private TextView business, province;
    private Spinner businessList, provinceList;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    /**
     * This onCreate will retrieve the current contents of the contact object that has been selected
     * and display them, allowing for a user to change and update them, or click the delete button
     * to erase the contact
     *
     * @param savedInstanceState
     *  Standard parameter for onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());


        business = (TextView) findViewById(R.id.currBusiness);
        province = (TextView) findViewById(R.id.currProvince);

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);
        addressField = (EditText) findViewById(R.id.address);
        numberField = (EditText) findViewById(R.id.businessNumber);

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

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.getName());
            emailField.setText(receivedPersonInfo.getEmail());
            addressField.setText(receivedPersonInfo.getAddress());
            numberField.setText(receivedPersonInfo.getBusinessNumber());

            String currentBusiness = "Current Business: "+receivedPersonInfo.getBusiness();
            String currentProvince = "Current Province/Territory: "+receivedPersonInfo.getProvince();

            business.setText(currentBusiness);
            province.setText(currentProvince);
        }
    }

    /**
     * When the update button is clicked, this method is called.
     * It creates a new Contact object with the new parameters and sets the old Contact
     * in the database to have these new parameters, then returns to the main activity.
     *
     * @param v
     *  Unused
     */
    public void updateContact(View v){
        String id = receivedPersonInfo.getUid();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String address = addressField.getText().toString();
        String province = String.valueOf(provinceList.getSelectedItem());
        String business = String.valueOf(businessList.getSelectedItem());
        String businessNumber = numberField.getText().toString();

        Contact person = new Contact(id, name, email, province, address, business, businessNumber);
        appState.firebaseReference.child(id).setValue(person);

        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * This is called when the erase button is clicked, it removes the contact entry
     * from the database
     * @param v
     *  Unused
     */
    public void eraseContact(View v)
    {
        String id = receivedPersonInfo.getUid();
        appState.firebaseReference.child(id).removeValue();

        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
