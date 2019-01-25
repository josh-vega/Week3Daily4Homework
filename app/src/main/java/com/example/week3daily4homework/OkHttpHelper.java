package com.example.week3daily4homework;

import android.util.Log;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.week3daily4homework.Constants.BASE_URL;

public class OkHttpHelper {

    public  static void asyncOkHttpApiCall(final String url){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            String jsonResponse;
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                jsonResponse = response.body().string();
                Gson gson = new Gson();
                if(url==BASE_URL) {
                    UserResponse userResponse = gson.fromJson(jsonResponse, UserResponse.class);
                    EventBus.getDefault().post(new UserEvent(userResponse));
                } else {
                    RepoResponse repoResponse = gson.fromJson(jsonResponse, RepoResponse.class);
                    EventBus.getDefault().post(new RepoEvent(repoResponse));
                }
            }
        });

    }

    public static void syncOkHttpApiCall(String url){
        final OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String jsonResponse = response.body().string();
                    Log.d("TAG", "run: " + jsonResponse);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
