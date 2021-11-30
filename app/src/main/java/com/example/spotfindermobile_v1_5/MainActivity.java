/*
    Code created by: Alex Bailey
    Edited by: --
    Spot Finder App Version: 1.5.0
    Original creation date: 9/27/21
    For course: CSCI 362
 */

package com.example.spotfindermobile_v1_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spotfindermobile_v1_5.ui.createaccount.CreateAccountActivity;
import com.example.spotfindermobile_v1_5.ui.login.LoginActivity;
import com.example.spotfindermobile_v1_5.ui.termsandconditions.TermsAndConditionsActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // get bundled info
        Bundle bundle = getIntent().getExtras();

        // open bundled info
        ArrayList<String> displayInfo = bundle.getStringArrayList("info");

        // display bundled info
        String info = "";
        for (int i = 0; i < displayInfo.size(); i++) { info = info + displayInfo.get(i) + ", "; }
        TextView user = findViewById(R.id.user_info_main);
        user.setText(info);

        // logout button
        logoutButton = (Button) findViewById(R.id.logout_button);

        // button function
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Log Out Successful", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}