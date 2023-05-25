package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.common.IntentKeys;
import com.example.myapplication.entity.Person;

public class EditProfileActivity extends AppCompatActivity {

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Intent intent = getIntent();
        EditText firstName = findViewById(R.id.firstNameInput);
        EditText lastName = findViewById(R.id.lastNameInput);
        EditText phoneNumber = findViewById(R.id.phoneNumberInput);
        EditText address = findViewById(R.id.addressInput);
        if (intent != null) {
            person = (Person) intent.getSerializableExtra(IntentKeys.person);
            firstName.setText(person.getFirstName());
            lastName.setText(person.getLastName());
            phoneNumber.setText(person.getPhoneNumber());
            address.setText(person.getAddress());
        }
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.setFirstName(firstName.getText().toString());
                person.setLastName(lastName.getText().toString());
                person.setAddress(address.getText().toString());
                person.setPhoneNumber(phoneNumber.getText().toString());
                Intent intent1 = new Intent(EditProfileActivity.this, ProfileDetailActivity.class);
                intent1.putExtra(IntentKeys.person, person);
                startActivity(intent1);
            }
        });
    }
}