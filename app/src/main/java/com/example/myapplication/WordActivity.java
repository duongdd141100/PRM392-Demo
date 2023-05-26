package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.adapter.WordAdapter;

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
    }

    private void addWords() {
        for (int i = 0; i < 50; i ++) {
            words.add("Item " + (i + 1));
        }
    }
}