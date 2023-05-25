package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.common.IntentKeys;
import com.example.myapplication.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        RadioGroup.OnCheckedChangeListener {

    private Spinner campusSpinner;

    private String campusPosition;

    private RadioGroup roles;

    private RadioButton admin, manager, staff;

    private List<Person> people = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        people.add(new Person("duongdd", "1414", "Do Duc", "Duong", "0979719735", "HN"));
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ( (EditText) findViewById(R.id.username)).getText().toString();
                String password = ( (EditText) findViewById(R.id.password)).getText().toString();
                people.stream().filter(p -> p.getUsername().equals(username) && p.getPassword().equals(password)).collect(Collectors.toList());
                if (people.stream().filter(p -> p.getUsername().equals(username) && p.getPassword().equals(password)).collect(Collectors.toList()).size() > 0) {
                    Person person = people.stream().filter(p -> p.getUsername().equals(username)).findFirst().get();
                    Intent intent = new Intent(MainActivity.this, ProfileDetailActivity.class);
                    intent.putExtra(IntentKeys.person, person);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        campusSpinner = findViewById(R.id.campusSpinner);
        campusSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
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

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("on start");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}