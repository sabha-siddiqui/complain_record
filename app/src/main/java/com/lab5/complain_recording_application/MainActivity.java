package com.lab5.complain_recording_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText login_email, login_pass;
    Button login_button, signup_button;
    DatabaseHelper db;

    public static final String MYPREFERENCES = "MyPrefs";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        db = new DatabaseHelper(this);
        login_email = findViewById(R.id.email);
        login_pass = findViewById(R.id.password);
        login_button = findViewById(R.id.login);
        signup_button = findViewById(R.id.signUp);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });

        sharedPreferences = getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getText().toString();
                String password= login_pass.getText().toString();
                Boolean chkemailpass = db.emailpassword(email, password);
                if(chkemailpass==true) {
                    Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(email, email);
                    editor.commit();
                    Intent in = new Intent(MainActivity.this, User_Dashboard.class);
                    startActivity(in);
                }
                else
                    Toast.makeText(getApplicationContext(),"Wrong Email or Password", Toast.LENGTH_SHORT).show();

            }
        });
    }




}
