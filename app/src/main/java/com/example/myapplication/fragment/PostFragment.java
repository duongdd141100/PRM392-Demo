package com.example.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.entity.PostInfo;
import com.example.myapplication.fragment.placeholder.PlaceholderContent;
import com.example.myapplication.webservice.MyApiEndPoint;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A fragment representing a list of Items.
 */
public class PostFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PostFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PostFragment newInstance(int columnCount) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    private MyPostRecyclerViewAdapter myPostRecyclerViewAdapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        retrievePostList();
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            myPostRecyclerViewAdapter = new MyPostRecyclerViewAdapter(PlaceholderContent.ITEMS);
            recyclerView.setAdapter(myPostRecyclerViewAdapter);
        }
        return view;
    }

    private void retrievePostList() {
        final String BASE_URL = "https://jsonplaceholder.typicode.com";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        MyApiEndPoint myApiEndPoint = retrofit.create(MyApiEndPoint.class);
        myApiEndPoint.getPostInfos().enqueue(new Callback<List<PostInfo>>() {
            @Override
            public void onResponse(Call<List<PostInfo>> call, Response<List<PostInfo>> response) {
                PlaceholderContent.ITEMS.clear();
                for (PostInfo postInfo : response.body()) {
                    PlaceholderContent.PlaceholderItem placeholderItem
                            = new PlaceholderContent.PlaceholderItem(postInfo.getId(),
                            postInfo.getTitle(),
                            postInfo.getBody());
                    PlaceholderContent.ITEMS.add(placeholderItem);
                }
            }

            @Override
            public void onFailure(Call<List<PostInfo>> call, Throwable t) {
                Log.d(getTag(), "Failed to get posts", t);
            }
        });
    }
}