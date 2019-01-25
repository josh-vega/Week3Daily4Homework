package com.example.week3daily4homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.week3daily4homework.Constants.REPO_URL;

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.rvView);

    //    OkHttpHelper.asyncOkHttpApiCall(REPO_URL);    This code makes app crash...
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void repoEvent(RepoEvent event){
        if(event != null){
            RepoResponse response = event.getResponse();
            List<RepoResponse> resultArrayList = Arrays.asList(response);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            rvAdapter = new RecyclerViewAdapter(resultArrayList);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(rvAdapter);
        }
    }
}
