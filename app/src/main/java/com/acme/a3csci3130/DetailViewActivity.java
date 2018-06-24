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

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField, addressField;
    private TextView business, province;
    private Spinner businessList, provinceList;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

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

            String currentBusiness = "Current Business: "+receivedPersonInfo.getBusiness();
            String currentProvince = "Current Province/Territory: "+receivedPersonInfo.getProvince();

            business.setText(currentBusiness);
            province.setText(currentProvince);
        }
    }

    public void updateContact(View v){
        String id = receivedPersonInfo.getUid();
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String address = addressField.getText().toString();
        String province = String.valueOf(provinceList.getSelectedItem());
        String business = String.valueOf(businessList.getSelectedItem());
        Contact person = new Contact(id, name, email, province, address, business);
        appState.firebaseReference.child(id).setValue(person);

        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void eraseContact(View v)
    {
        String id = receivedPersonInfo.getUid();
        appState.firebaseReference.child(id).removeValue();

        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
