/*
    Code created by: Alex Bailey
    Edited by: --
    Spot Finder App Version: 1.5.0
    Original creation date: 9/27/21
    For course: CSCI 362
 */

package com.example.spotfindermobile_v1_5.ui.createaccount;

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

import java.lang.*;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotfindermobile_v1_5.R;
import com.example.spotfindermobile_v1_5.ui.termsandconditions.TermsAndConditionsActivity;

public class LicensePlateActivity extends AppCompatActivity {

    // Variables
    String[] items = {"AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE",
            "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA",
            "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH",
            "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD",
            "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"};
    private AutoCompleteTextView state;
    private ArrayAdapter<String> adapterItems;
    private Button continueButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licenseplate);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // get bundled info
        Bundle bundle = getIntent().getExtras();

        // define dropdown list
        state = findViewById(R.id.plate_state_input);

        // hooks
        adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, items);
        state.setAdapter(adapterItems);

        // selection action
        state.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String choice = parent.getItemAtPosition(i).toString();
            }
        });

        // initialize buttons
        continueButton = (Button) findViewById(R.id.plate_entry_continue_button);
        backButton = (Button) findViewById(R.id.plate_entry_back_button);

        // continue button function
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // pass info
                ArrayList<String> info = bundle.getStringArrayList("info");
                Bundle newBundle = new Bundle();
                newBundle.putStringArrayList("info", info);

                // get plate info
                TextView inputPlate = findViewById(R.id.plate_number);
                TextView inputState = findViewById(R.id.plate_state_input);
                String plateNum = inputPlate.getText().toString();
                String plateState = inputState.getText().toString().toLowerCase();

                if (plateNum.isEmpty() || plateState.equals("select")) {
                    Toast.makeText(LicensePlateActivity.this, R.string.error_required_fields, Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(LicensePlateActivity.this, TermsAndConditionsActivity.class);
                    intent.putExtras(newBundle);
                    startActivity(intent);
                    finish();
                }
            }
        });

        // back button function
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LicensePlateActivity.this, CreateAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
