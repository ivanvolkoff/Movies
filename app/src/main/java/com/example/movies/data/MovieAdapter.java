package com.example.movies.data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.MovieDetailedInfo;
import com.example.movies.R;
import com.example.movies.activities.MainActivity;
import com.example.movies.model.Movie;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;


    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImageView;
        TextView titleTextView;
        TextView yearTextView;


        public MovieViewHolder(View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Pressed:"+  movies.get(getAdapterPosition()).getImdbID(), Toast.LENGTH_SHORT).show();
                    Intent i  = new Intent(context, MovieDetailedInfo.class);
                        i.putExtra("title", movies.get(getAdapterPosition()).getImdbID());
                        context.startActivity(i);
                }

            });



        }


    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie currentMovie = movies.get(position);
        String title = currentMovie.getTitle();
        String year = currentMovie.getYear();
        String posterUrl = currentMovie.getPostreUrl();

        holder.titleTextView.setText(title);
        holder.yearTextView.setText(year);
        Picasso.get().load(posterUrl).fit().centerInside().into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


}
