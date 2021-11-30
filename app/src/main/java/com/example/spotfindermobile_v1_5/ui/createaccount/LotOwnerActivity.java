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
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotfindermobile_v1_5.R;
import com.example.spotfindermobile_v1_5.ui.login.LoginActivity;
import com.example.spotfindermobile_v1_5.ui.termsandconditions.TermsAndConditionsActivity;

import java.util.ArrayList;

public class LotOwnerActivity extends AppCompatActivity {

    // Variables
    private Button continueButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotowner);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // get bundled info
        Bundle bundle = getIntent().getExtras();

        // initialize buttons
        continueButton = (Button) findViewById(R.id.lot_owner_continue_button);
        backButton = (Button) findViewById(R.id.lot_owner_back_button);

        // continue button function
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LotOwnerActivity.this, TermsAndConditionsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // back button function
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // target page
                Intent intent = new Intent(LotOwnerActivity.this, CreateAccountActivity.class);

                // pass info
                ArrayList<String> info = bundle.getStringArrayList("info");
                Bundle newBundle = new Bundle();
                newBundle.putStringArrayList("info", info);
                intent.putExtras(newBundle);

                // execute
                startActivity(intent);
                finish();
            }
        });

    }
}
