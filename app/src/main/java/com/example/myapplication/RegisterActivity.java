package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.common.IntentKeys;
import com.example.myapplication.dao.DatabaseHandler;

public class RegisterActivity extends AppCompatActivity {

    private Spinner campusSpinner;

    private EditText fullName;

    private EditText username;

    private EditText password;

    private RadioGroup role;

    private Button register;

    private DatabaseHandler databaseHandler = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        campusSpinner = findViewById(R.id.campusSpinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.campus, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campusSpinner.setAdapter(arrayAdapter);
        fullName = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        role = findViewById(R.id.role);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isError = false;
                if (fullName.getText().toString().isEmpty()) {
                    fullName.setError("Full Name is Required");
                    isError = true;
                }
                if (username.getText().toString().isEmpty()) {
                    username.setError("Username is Required");
                    isError = true;
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("Password is Required");
                    isError = true;
                }
                if (fullName.getText().toString().isEmpty()) {
                    fullName.setError("Full Name is Required");
                    isError = true;
                }
                if (role.getCheckedRadioButtonId() == -1) {
                    isError = true;
                }
                if (isError) {
                    return;
                } else {
                    databaseHandler.insert(fullName.getText().toString(), username.getText().toString(), password.getText().toString(),
                            ((RadioButton) findViewById(role.getCheckedRadioButtonId())).getText().toString(), campusSpinner.getSelectedItem().toString());
                }
            }
        });
    }
}