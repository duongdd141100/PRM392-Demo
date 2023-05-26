package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.WordActivity;
import com.example.myapplication.WordDetailActivity;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<String> words;

    private Context context;

    public WordAdapter(Context context, List<String> words) {
        this.context = context;
        this.words = words;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.word_item, parent, false);
        return new WordViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
    String word = words.get(position);
    holder.word.setText(word);
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    protected class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView word;
        private WordAdapter adapter;
        public WordViewHolder(@NonNull View itemView, WordAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            this.word = itemView.findViewById(R.id.word);
            word.setOnClickListener(this);
        }

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, word.getText().toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, WordDetailActivity.class);
            intent.putExtra("word", word.getText().toString());
            context.startActivity(intent);
        }
    }
}
