package com.example.week3daily4homework;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.week3daily4homework.Constants.BASE_URL;

public class MainActivity extends AppCompatActivity {
    TextView tvName, tvLogin, tvCompany, tvLocation;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);
        tvLogin = findViewById(R.id.tvUsername);
        tvCompany = findViewById(R.id.tvCompany);
        tvLocation = findViewById(R.id.tvLocation);
        imageView = findViewById(R.id.ivPic);

        OkHttpHelper.asyncOkHttpApiCall(BASE_URL);

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
    public void userEvent(UserEvent event){
        if(event != null){
            UserResponse userResponse = event.getUserResponse();
            Glide.with(this).load(userResponse.getAvatarUrl()).into(imageView);
            tvName.setText("Name: " + userResponse.getName());
            tvLogin.setText("Login: " +userResponse.getLogin());
            tvCompany.setText("Company: " + userResponse.getCompany());
            tvLocation.setText("Location: " + userResponse.getLocation());
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
