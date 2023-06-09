package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.myapplication.adapter.WordAdapter;
import com.example.myapplication.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class WordActivity extends AppCompatActivity {

    List<String> words = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        addWords();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        WordAdapter wordAdapter = new WordAdapter(this, words);
        recyclerView.setAdapter(wordAdapter);
        Button menu = findViewById(R.id.menu_button);
        registerForContextMenu(menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(WordActivity.this, menu);
                popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (R.id.view_profile == item.getItemId()) {
                            showProfile();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Location Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Location Permission NOT Granted", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(this, "Request code is Not handled", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (menu instanceof MenuBuilder) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.view_profile) {
            showProfile();
            requestLocationPermission();
        }
        if (item.getItemId() == R.id.sendNoti) {
            sendNotification();
        }
        return super.onOptionsItemSelected(item);
//        switch (item.getItemId()) {
//            case R.id.settings:
//                showSettings();
//                return true;
//            case R.id.view_profile:
//                showProfile();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.view_profile) {
            showProfile();
        }
        return super.onContextItemSelected(item);
    }

    private void showProfile() {
        Intent intent = new Intent(this, ProfileDetailActivity.class);
        Person person = new Person("duongdd", "1414", "Do", "Duong", "0123", "HN");
        intent.putExtra("person", person);
        startActivity(intent);
    }

    private void showSettings() {
    }

    private void addWords() {
        for (int i = 0; i < 50; i ++) {
            words.add("Item " + (i + 1));
        }
    }

    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("Reson for request location service")
                        .setMessage("Please grant location serice to use app service")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
            }
            requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, 3);
        }
    }

    private void sendNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("Reason for request send notification")
                        .setMessage("Please grant send notification to receive")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
            }
            requestPermissions(new String[] {Manifest.permission.POST_NOTIFICATIONS}, 3);
        }
        String channelId = "001";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.ic_action_favourite)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentTitle("Demo Noti")
                .setContentText("Hello! this is demo message");
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, "demo chanel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(2, builder.build());
    }
}