/*
    Code created by: Alex Bailey
    Edited by: --
    Spot Finder App Version: 1.5.0
    Original creation date: 9/27/21
    For course: CSCI 362
 */

package com.example.spotfindermobile_v1_5.ui.createaccount;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.spotfindermobile_v1_5.R;
import com.example.spotfindermobile_v1_5.ui.login.LoginActivity;
import android.widget.EditText;

import java.util.ArrayList;

public class CreateAccountActivity extends AppCompatActivity {

    // variables
    String[] items = {"Driver", "Lot Owner"};
    private AutoCompleteTextView userType;
    private ArrayAdapter<String> adapterItems;
    private Button continueButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // define dropdown list
        userType = findViewById(R.id.user_type_input);

        // hooks
        adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, items);
        userType.setAdapter(adapterItems);

        // selection action
        userType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String choice = parent.getItemAtPosition(i).toString();
            }
        });

        // initialize buttons
        continueButton = (Button) findViewById(R.id.continue_button);
        cancelButton = (Button) findViewById(R.id._cancel_button);

        // new account button function
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // instantiate error message
                String errorMsg = "";

                // instantiate helper functions
                CreateAccountActions actions = new CreateAccountActions();

                // get form inputs
                TextView userType = findViewById(R.id.user_type_input);
                TextView firstName = findViewById(R.id.firstName);
                TextView lastName = findViewById(R.id.lastName);
                TextView email = findViewById(R.id.email);
                TextView phoneNum = findViewById(R.id.phone);
                TextView ursn = findViewById(R.id.username_create);
                TextView pswd = findViewById(R.id.password_create);
                TextView pswdConf = findViewById(R.id.password_confirm);

                // Convert all input to strings
                String userTypeStr = userType.getText().toString();
                String firstNameStr = firstName.getText().toString();
                String lastNameStr = lastName.getText().toString();
                String emailStr = email.getText().toString();
                String phoneNumStr = phoneNum.getText().toString();
                String usrnStr = ursn.getText().toString();
                String pswdStr = pswd.getText().toString();
                String pswdConfStr = pswdConf.getText().toString();

                // Check character lengths
                boolean fiNameLen = actions.checkLength(firstNameStr, 25, 0);
                boolean laNameLen = actions.checkLength(lastNameStr, 25, 0);
                boolean emailLen = actions.checkLength(emailStr, 40, 0);
                boolean phoneLen = actions.checkPhone(phoneNumStr);
                boolean usrnLen = actions.checkLength(usrnStr, 30,4);
                int usrErrorCode = actions.getLenFailCode();
                boolean pswdLen = actions.checkLength(pswdStr, 30, 10);
                int pswdErrorCode = actions.getLenFailCode();

                // Confirm passwords
                boolean pswdChk = actions.checkPswd(pswdStr, pswdConfStr);

                // Determine next page
                boolean licensePage = actions.detNextPage(userTypeStr);

                // If any, return errors
                // instantiate continue condition
                boolean formComplete = true;

                // If no user type is selected
                if (userTypeStr.toLowerCase().equals("select")) {
                    // error message
                    // editText.setError("error");
                    errorMsg = getString(R.string.error_user_type);
                    formComplete = false;
                }

                // First name
                if ((firstNameStr.isEmpty()) || (fiNameLen == false)) {
                    if (firstNameStr.isEmpty()) {
                        // error message
                        errorMsg = getString(R.string.error_incomplete_form) + " (First Name)";
                    }
                    if (fiNameLen == false) {
                        // error message
                        errorMsg = getString(R.string.error_invalid_length_long) + " (First Name)";
                    }
                    formComplete = false;
                }

                // Last name
                if ((lastNameStr.isEmpty()) || (laNameLen == false)) {
                    if (lastNameStr.isEmpty()) {
                        // error message
                        errorMsg = getString(R.string.error_incomplete_form) + " (Last Name)";
                    }
                    if (laNameLen == false) {
                        // error message
                        errorMsg = getString(R.string.error_invalid_length_long) + " (Last Name)";
                    }
                    formComplete = false;
                }

                // Email
                if ((emailStr.isEmpty()) || (emailLen == false)) {
                    if (emailStr.isEmpty()) {
                        // error message
                        errorMsg = getString(R.string.error_incomplete_form) + " (Email)";
                    }
                    if (emailLen == false) {
                        // error message
                        errorMsg = getString(R.string.error_invalid_length_long) + " (Email)";
                    }
                    formComplete = false;
                }

                // Phone number
                if ((phoneNumStr.isEmpty()) || (phoneLen == false)) {
                    if (phoneNumStr.isEmpty()) {
                        // error message
                        errorMsg = getString(R.string.error_invalid_phone);
                    }
                    if (phoneLen == false) {
                        // error message
                        errorMsg = getString(R.string.error_invalid_phone);
                    }
                    formComplete = false;
                }

                // Username
                if ((usrnStr.isEmpty()) || (usrnLen == false)) {
                    if (usrnStr.isEmpty()) {
                        // error message
                        errorMsg = getString(R.string.error_incomplete_form) + " (Username)";
                    }
                    if (usrnLen == false) {
                        // error message
                        if (usrErrorCode == 1) { errorMsg = getString(R.string.error_invalid_length_long) + " (Username)"; }
                        if (usrErrorCode == 2) { errorMsg = getString(R.string.error_invalid_length_short) + " (Username)"; }
                    }
                    formComplete = false;
                }

                // Password null or too long
                if ((pswdStr.isEmpty()) || (pswdLen == false)) {
                    if (pswdStr == null) {
                        // error message
                        errorMsg = getString(R.string.error_incomplete_form) + " (Password)";
                    }
                    if (pswdLen == false) {
                        // error message
                        if (pswdErrorCode == 1) { errorMsg = getString(R.string.error_invalid_length_long) + " (Password)"; }
                        if (pswdErrorCode == 2) { errorMsg = getString(R.string.error_password_req1); }
                    }
                    formComplete = false;
                }

                // Passwords do not match
                if (pswdChk == false) {
                    if (pswdChk == false) {
                        // error message
                        int failCode = actions.getPswdFailCode();
                        if (failCode == 1) { errorMsg = getString(R.string.error_password_match_error); }
                        else if (failCode == 2) { errorMsg = getString(R.string.error_password_req2); }
                        else if (failCode == 3) { errorMsg = getString(R.string.error_password_req3); }
                        else if (failCode == 4) { errorMsg = getString(R.string.error_password_req4); }
                        else if (failCode == 5) { errorMsg = getString(R.string.error_password_req5); }
                        else if (failCode == 6) { errorMsg = getString(R.string.error_password_req6); }
                    }
                    formComplete = false;
                }

                // If all fields pass
                if (formComplete == true) {

                    // create array list of info
                    ArrayList<String> info = new ArrayList<String>();
                    info.add(firstNameStr);
                    info.add(lastNameStr);
                    info.add(usrnStr);
                    info.add(emailStr);
                    info.add(phoneNumStr);
                    info.add(userTypeStr);

                    // bundle info
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("info", info);

                    // Determine path
                    Intent intent;
                    if (licensePage == true) {

                        // target path
                        intent = new Intent(CreateAccountActivity.this, LicensePlateActivity.class);
                    }

                    else {
                        // target path
                        intent = new Intent(CreateAccountActivity.this, LotOwnerActivity.class);
                    }

                    // add bundle to intent
                    intent.putExtras(bundle);

                    // Execute
                    startActivity(intent);
                    finish();
                }
                else { Toast.makeText(CreateAccountActivity.this, errorMsg, Toast.LENGTH_LONG).show(); }
            }
        });

        // cancel account create and return to login screen
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
