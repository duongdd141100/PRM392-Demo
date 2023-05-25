package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.common.IntentKeys;
import com.example.myapplication.entity.Person;

public class ProfileDetailActivity extends AppCompatActivity {

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        Intent intent = getIntent();
//        Person person = null;
        if (intent != null) {
            person = (Person) intent.getSerializableExtra(IntentKeys.person);
            TextView username = findViewById(R.id.usernameVal);
            username.setText(person.getUsername());

            TextView firstName = findViewById(R.id.firstNameVal);
            firstName.setText(person.getFirstName());

            TextView lastName = findViewById(R.id.lastNameVal);
            lastName.setText(person.getLastName());

            TextView address = findViewById(R.id.addressVal);
            address.setText(person.getAddress());

            TextView phoneNumber = findViewById(R.id.phoneNumberVal);
            phoneNumber.setText(person.getPhoneNumber());
        }
        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ProfileDetailActivity.this, EditProfileActivity.class);
                intent1.putExtra(IntentKeys.person, person);
                startActivity(intent1);
            }
        });
    }
}