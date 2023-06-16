package com.example.myapplication.repository;

import android.content.Context;

import com.example.myapplication.WordRoomDatabase;
import com.example.myapplication.dao.WordDAO;
import com.example.myapplication.entity.Word;

import java.util.List;

public class WordRepository {

    private WordDAO wordDAO;

    public WordRepository(Context context) {
        wordDAO = WordRoomDatabase.getInstance(context).wordDAO();
    }

    public void createWord(Word word) {
        wordDAO.insert(word);
    }

    public void updateWord(Word word) {
        wordDAO.update(word);
    }

    public List<Word> findAll() {
        return wordDAO.findAll();
    }

    public Word findById(Integer id) {
        return wordDAO.findById(id);
    }
}
