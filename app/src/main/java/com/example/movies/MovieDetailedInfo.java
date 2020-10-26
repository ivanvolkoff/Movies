package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.movies.activities.MainActivity;

public class MovieDetailedInfo extends AppCompatActivity {
    private TextView infoview;
    private MainActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detailed_info);
        infoview = findViewById(R.id.infoTv);

        Bundle bundle = new  Bundle();
        String searchedMovie = bundle.getString("searchedMovie");
        infoview.setText(searchedMovie);
    }
}