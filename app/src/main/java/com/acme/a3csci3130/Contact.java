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

    public  String uid;
    public  String name;
    public  String email;
    public  String province;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public  String address;
    public  String business;

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
     */
    public Contact(String uid, String name, String email, String province, String address, String business){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.province = province;
        this.address = address;
        this.business = business;
    }

    /**
     * Turns the current Contact Object into a map
     * @return
     *  A map containing the contents of the Contact Object
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("email", email);
        result.put("address", address);
        result.put("province", province);
        result.put("business", business);

        return result;
    }
}
