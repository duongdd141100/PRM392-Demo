package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        RadioGroup.OnCheckedChangeListener {

    private Spinner campusSpinner;

    private String campusPosition;

    private RadioGroup roles;

    private RadioButton admin, manager, staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });

        campusSpinner = findViewById(R.id.campusSpinner);
        campusSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> arrayAdapter =ArrayAdapter.createFromResource(this,
                R.array.campus, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campusSpinner.setAdapter(arrayAdapter);

        roles = findViewById(R.id.roles);
        roles.setOnCheckedChangeListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        campusPosition = parent.getItemAtPosition(position).toString();
        Toast.makeText(MainActivity.this, "Choosed " + campusPosition, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private String roleSelected;

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        roleSelected = ((RadioButton) findViewById(checkedId)).getText().toString();
        Toast.makeText(MainActivity.this, "Choosed " + roleSelected, Toast.LENGTH_SHORT).show();
    }
}