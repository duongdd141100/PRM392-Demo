package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

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
}