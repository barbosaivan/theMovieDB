package com.example.wposs_001_semillero_wmovie.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wposs_001_semillero_wmovie.R;
import com.example.wposs_001_semillero_wmovie.models.Movie;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ViewHolder> {
    private ArrayList<Movie> dataset;
    private Context context;
    final ListMovieAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Movie item);
    }

    public ListMovieAdapter(Context context, ListMovieAdapter.OnItemClickListener listener) {
        this.context = context;
        dataset = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie m = dataset.get(position);
        holder.textViewAverage.setText(String.valueOf(m.getVote_average()));
        holder.textViewTitle.setText(m.getTitle());
        if (m.getGenre_ids().length > 0) {
            holder.textViewGenres.setText(m.getGenre_ids()[0]);
        } else {
            holder.textViewGenres.setText("No Tiene");
        }
        holder.textViewGenres.setText(m.getGenre_ids()[0]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(m);
            }
        });
        Glide.with(context).
                load("https://image.tmdb.org/t/p/w500/" + m.getPoster_path())
                .centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageViewMovie);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addListMovies(ArrayList<Movie> listMovies) {
        dataset.addAll(listMovies);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewMovie;
        private TextView textViewAverage, textViewTitle, textViewReleaseDate, textViewGenres;

        public ViewHolder(View itemView) {
            super(itemView);

            imageViewMovie = (ImageView) itemView.findViewById(R.id.imageViewMovie);
            textViewAverage = (TextView) itemView.findViewById(R.id.textViewAverage);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewReleaseDate = (TextView) itemView.findViewById(R.id.textViewReleaseDate);
            textViewGenres = (TextView) itemView.findViewById(R.id.textViewGenres);
        }
    }
}

