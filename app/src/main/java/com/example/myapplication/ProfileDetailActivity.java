package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
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

    private ActivityResultLauncher<Intent> startActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 1) {
                        resetInfo(result.getData());
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        Intent intent = getIntent();
        resetInfo(intent);
        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ProfileDetailActivity.this, EditProfileActivity.class);
                intent1.putExtra(IntentKeys.person, person);
//                startActivityForResult(intent1, 1);
//                startActivity(intent1);
                startActivity.launch(intent1);
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            resetInfo(data);
//        }
//    }

    private void resetInfo(Intent intent) {
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
    }
}