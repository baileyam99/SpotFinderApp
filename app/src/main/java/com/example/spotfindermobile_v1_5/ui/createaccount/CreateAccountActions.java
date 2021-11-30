/*
    Code created by: Alex Bailey
    Edited by: --
    Spot Finder App Version: 1.5.0
    Original creation date: 9/27/21
    For course: CSCI 362
 */

package com.example.spotfindermobile_v1_5.ui.createaccount;

import com.example.spotfindermobile_v1_5.data.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccountActions {
    // variables
    private int pswdFailCode = 0;
    private int lenFailCode = 0;
    User user;

    // checks for correct length
    public boolean checkLength(String string, int maxLen, int minLen) {
        // reset fail code
        lenFailCode = 0;
        // get string length
        int strLen = string.length();

        // initialize return variable
        boolean valid = true;

        if (strLen > maxLen) { valid = false; lenFailCode = 1;}
        if (strLen < minLen) { valid = false; lenFailCode = 2;}

        // return variable
        return valid;
    }

    // iterates through string to find case
    public boolean match(String str, String reg) {
        // return variable
        boolean match;

        // find case
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        match = matcher.find();

        // return result
        return match;
    }

    // checks password requirements
    public boolean checkPswd(String pswd1, String pswd2) {
        // default error code = o
        pswdFailCode = 0;
        // return variable
        boolean chk = true;

        // compares passwords
        if (!(pswd1.equals(pswd2))) { chk = false; pswdFailCode = 1;}
        // checks for no numbers
        else if (match(pswd2, "[0-9]+") == false) {chk = false; pswdFailCode = 2;}
        // checks for no upper case letter
        else if (match(pswd2, "[A-Z]+") == false) {chk = false; pswdFailCode = 3;}
        // checks for no lower case letter
        else if (match(pswd2, "[a-z]+") == false) {chk = false; pswdFailCode = 4;}
        // checks for no special character
        else if (match(pswd2, "[^A-Za-z0-9 ]") == false) {chk = false; pswdFailCode = 5;}
        // checks for spaces
        else if (match(pswd2, "[ ]") == true) {chk = false; pswdFailCode = 6;}

        return chk;
    }

    // gets password fail code
    public int getPswdFailCode() {
        return pswdFailCode;
    }

    // gets password fail code
    public int getLenFailCode() {
        return lenFailCode;
    }

    // determines next page
    public boolean detNextPage(String type) {
        if (type.toLowerCase().equals("driver")) { return true; }
        else { return false; }
    }

    // checks phone number
    public boolean checkPhone(String string) {
        boolean valid;

        // get length
        int strLen = string.length();

        // check length
        if (strLen == 10) { valid = true; }
        else { valid = false; }

        // check for non-number characters
        valid = match(string, "^[0-9]+$");

        return valid;
    }

    // creates a new user
    public void createNewUser(String firstName, String lastName, String username, String email, String phoneNum, String userType, boolean verified, String plateNum) {

        user = new User(firstName, lastName, username, email, phoneNum, userType, verified, plateNum);
    }

    // returns username
    public String getUsername() { return user.getUsername(); }

    // returns user full name
    public String getFullName() { return user.getFullName(); }

    // returns user first name
    public String getFirstName() { return user.getFirstName(); }

    // retruns user last name
    public String getLastName() { return user.getLastName(); }

    // returns user balance
    public Double getBalance() { return user.getBalance(); }
}
