package com.example.myapplication.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.entity.Word;

import java.util.List;

@Dao
public interface WordDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Word word);

    @Query("SELECT * FROM word w where w.id = :id")
    Word findById(Integer id);

    @Query("SELECT * FROM word")
    List<Word> findAll();
}
