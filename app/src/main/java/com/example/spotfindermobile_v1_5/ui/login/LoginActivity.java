/*
    Code created by: Alex Bailey
    Edited by: --
    Spot Finder App Version: 1.5.0
    Original creation date: 9/27/21
    For course: CSCI 362
 */

package com.example.spotfindermobile_v1_5.ui.login;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spotfindermobile_v1_5.MainActivity;
import com.example.spotfindermobile_v1_5.R;
import com.example.spotfindermobile_v1_5.ui.createaccount.CreateAccountActivity;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    // variables
    private static int TIME = 3000;
    private Button loginButton;
    private Button createAcc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // initialize login button
        loginButton = (Button) findViewById(R.id.login_button);


        // login button function
        loginButton.setOnClickListener(new View.OnClickListener() {
            boolean credentials = true;
            
            // test login
            @Override
            public void onClick(View v) {
                // find username and password inputs
                TextView username = findViewById(R.id.username);
                TextView password = findViewById(R.id.password);

                // convert inputs to strings
                String usernameStr = username.getText().toString();
                String passwordStr = password.getText().toString();

                // correct
                if(usernameStr.equals("admin") && passwordStr.equals("admin")) {
                    // create array list of info
                    ArrayList<String> info = new ArrayList<String>();
                    info.add("Admin");
                    info.add("Admin");
                    info.add("userAdmin");
                    info.add("admin@spotfinderparking.com");
                    info.add("0000000000");
                    info.add("admin");
                    String msg = "Welcome " + info.get(0) + "!";
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();

                    // bundle info
                    Bundle newBundle = new Bundle();
                    newBundle.putStringArrayList("info", info);

                    // target path
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    // pass info
                    intent.putExtras(newBundle);

                    // execute
                    startActivity(intent);
                    finish();
                }

                //incorrect
                else { Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show(); }
            }
        });

        // initialize new account button
        createAcc = (Button) findViewById(R.id.create_account);

        // new account button function
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}