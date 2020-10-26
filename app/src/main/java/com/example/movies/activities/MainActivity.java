package com.example.movies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movies.R;
import com.example.movies.data.MovieAdapter;
import com.example.movies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private ArrayList<Movie>movies;
    private RequestQueue requestQueue;
    private EditText search;
    private Button searchButton;
    private String searchMovie;
    JsonObjectRequest request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        search = findViewById(R.id.searchMovieTV);
        searchButton = findViewById(R.id.searchButton);
        movies = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);


        search();


    }
    private void search(){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(movieAdapter==null){
                    searchMovie = search.getText().toString().trim();
                    getMovies(searchMovie);
                }
            }
        });
    }

    private void getMovies(String searchingWord) {
        movies.clear();
        String url = "http://www.omdbapi.com/?s="+searchingWord+"&apikey=dfac6642";
        request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Search");
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String title = jsonObject.getString("Title");
                        String year = jsonObject.getString("Year");
                        String posterUrl = jsonObject.getString("Poster");
                        String imdbID = jsonObject.getString("imdbID");
                        Movie movie = new Movie();
                        movie.setTitle(title);
                        movie.setYear(year);
                        movie.setPostreUrl(posterUrl);
                        movie.setImdbID(imdbID);
                        movies.add(movie);
                    }
                    movieAdapter = new MovieAdapter(MainActivity.this,movies);
                    recyclerView.setAdapter(movieAdapter);
                    movieAdapter = null;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> error.printStackTrace());

        requestQueue.add(request);
    }

}