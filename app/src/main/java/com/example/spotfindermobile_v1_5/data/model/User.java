/*
    Code created by: Alex Bailey
    Edited by: --
    Spot Finder App Version: 1.5.0
    Original creation date: 9/27/21
    For course: CSCI 362
 */

package com.example.spotfindermobile_v1_5.data.model;

import java.util.Random;

public class User {

    // variables
    private String firstName;
    private String lastName;
    private String fullName;
    private String username;
    private String userID ;
    private String email;
    private String phoneNum;
    private String userType;
    private String plateNum;
    private boolean verified;
    private double balance;
    private double payment;
    private double remainBal;
    private boolean paidFull;

    // constructor
    public User(String firstName, String lastName, String username, String email, String phoneNum, String userType, boolean verified, String plateNum) {
        super();

        // get full name
        this.firstName = firstName;
        this.lastName = lastName;
        setFullName();

        // set other variables
        this.username = username;
        this.email = email;
        this.phoneNum = phoneNum;
        this.userType = userType;
        this.verified = verified;
        this.plateNum = plateNum;
        balance = 0;
        payment = 0;

        // calculate initial balance & determine full payment status
        remainBal = calcBal();
        paidFull = getPayStat();

        // generate User ID
        userID = "";
        for (int i = 0; i < 8 ; i++) {
            Random num = new Random();
            int randInt = num.nextInt(9);
            String intString = String.valueOf(randInt);
            userID += intString;
        }
    }

    // getter method for first name
    public String getFirstName() {
        return firstName;
    }

    // setter method for name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // getter method for last name
    public String getLastName() {
        return lastName;
    }

    // setter method for last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // getter method for full name
    public String getFullName() {
        return fullName;
    }

    // setter method for full name
    public void setFullName() {
        this.fullName = firstName + " " + lastName;
    }

    // getter method for User ID
    public String getUserID() {
        return userID;
    }

    // setter method for UserID
    public void setNewUserID() {
        String newUserID = "";
        for (int i = 0; i < 8 ; i++) {
            Random num = new Random();
            int randInt = num.nextInt(9);
            String intString = String.valueOf(randInt);
            newUserID.concat(intString);
        }

        if (!(newUserID.equals(userID))) {
            userID = newUserID;
        }
        else {
            setNewUserID();
        }
    }

    // getter method for username
    public String getUsername() { return username; }

    // setter method for username
    public void changeUsername(String newUsername) { username = newUsername; }

    // getter method for payment amount
    public double getPayment() {
        return payment;
    }

    // setter method for payment amount
    public void addPayment(double payment) {
        this.payment += payment;

        // calculate new balance & reset payment status
        balance = calcBal();

    }

    // calculate remaining balance
    public double calcBal() {
        double calc = balance - payment;
        remainBal = Math.round(calc * 100.0)/100.0;
        return remainBal;
    }

    // getter method for remaining balance
    public double getBalance() {
        return balance;
    }

    // adds to remaining balance
    public void addBalance(double balance) {
        this.balance += balance;
    }

    // determines payment status
    public void detPayStat() {
        remainBal = calcBal();
        if (remainBal <= 0.0) { paidFull = true; }
        else { paidFull = false; }
    }

    // getter method for payment status
    public boolean getPayStat() {
        detPayStat();
        return paidFull;
    }

    @Override
    public String toString() {
        return "{User Info: [Name = " + fullName + "][User ID = " + userID + "][Payment Amount = $" + payment + "][Remaining Balance: $" + balance + "][Paid in Full: " + paidFull + "]";
    }

}
