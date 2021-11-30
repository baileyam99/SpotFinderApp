/*
    Code created by: Alex Bailey
    Edited by: --
    Spot Finder App Version: 1.5.0
    Original creation date: 9/27/21
    For course: CSCI 362
 */

package com.example.spotfindermobile_v1_5.ui.termsandconditions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotfindermobile_v1_5.MainActivity;
import com.example.spotfindermobile_v1_5.R;

import java.util.ArrayList;

public class TermsAndConditionsActivity extends AppCompatActivity {

    // Variables
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsandconditions);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // get bundled info
        Bundle bundle = getIntent().getExtras();

        // get checkbox
        CheckBox read = findViewById(R.id.terms_and_conditions_checkbox);

        // initialize submit button
        submit = (Button) findViewById(R.id.terms_and_conditions_submit_button);

        // submit button function
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get check status
                boolean checked = read.isChecked();

                // correct
                if (checked) {
                    // open info from bundle
                    ArrayList<String> info = bundle.getStringArrayList("info");

                    // add and access info
                    info.add("true");
                    String firstName = info.get(0);

                    // re-bundle to pass on
                    Bundle newBundle = new Bundle();
                    newBundle.putStringArrayList("info", info);

                    // welcome user
                    Toast.makeText(TermsAndConditionsActivity.this, "Welcome " + firstName + "!", Toast.LENGTH_LONG).show();

                    // target path
                    Intent intent = new Intent(TermsAndConditionsActivity.this, MainActivity.class);

                    // pass info
                    intent.putExtras(newBundle);

                    // execute
                    startActivity(intent);
                    finish();
                }

                //incorrect
                else { Toast.makeText(TermsAndConditionsActivity.this, "Please review terms and conditions", Toast.LENGTH_LONG).show(); }
            }
        });
    }
}