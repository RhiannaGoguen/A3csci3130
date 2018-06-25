package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase database. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public  String uid;             //User ID: for the Database
    public  String name;            //Contact's name
    public  String email;           //Contact's email address
    public  String province;        //Contact's province/territory they operate in
    public  String address;         //Contact's address
    public  String business;        //Contact's business type
    public  String businessNumber;  //Contact's business number

    //Getters and setters

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }
    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getBusiness() {
        return business;
    }
    public void setBusiness(String business) {
        this.business = business;
    }

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Contact constructor
     *
     * @param uid
     *  User ID
     * @param name
     *  Name of contact
     * @param email
     *  Email of contact
     * @param province
     *  Province or Territory that contact lives in
     * @param address
     *  Address of contact
     * @param business
     *  Type of business that contact has
     * @param businessNumber
     *  The business number
     */
    public Contact(String uid, String name, String email, String province, String address, String business, String businessNumber){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.province = province;
        this.address = address;
        this.business = business;
        this.businessNumber = businessNumber;
    }

}
