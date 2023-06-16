package com.example.myapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word")
public class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id", typeAffinity = 1, index = true)
    private Integer id;

    @ColumnInfo(name = "word")
    @NonNull
    private String word;

    public Word(@NonNull Integer id, @NonNull String word) {
        this.id = id;
        this.word = word;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    @NonNull
    public String getWord() {
        return word;
    }

    public void setWord(@NonNull String word) {
        this.word = word;
    }
}
