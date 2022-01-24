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

public class ListSearchMovieAdapter extends RecyclerView.Adapter<ListSearchMovieAdapter.ViewHolder> {
    private ArrayList<Movie> dataset;
    private Context context;
    final ListSearchMovieAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Movie item);
    }

    public ListSearchMovieAdapter(Context context, ListSearchMovieAdapter.OnItemClickListener listener) {
        this.context = context;
        dataset = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_search_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie m = dataset.get(position);
        holder.textViewAverageSearchMovie.setText(String.valueOf(m.getVote_average()));
        holder.textViewTitleSearchMovie.setText(m.getTitle());
        holder.textViewReleaseDateSearchMovie.setText(m.getRelease_date());
        if(m.getGenre_ids().length > 0){
            holder.textViewGenresSearchMovie.setText(m.getGenre_ids()[0]);
        }else{
            holder.textViewGenresSearchMovie.setText("No Tiene");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(m);
            }
        });
        Glide.with(context).
                load("https://image.tmdb.org/t/p/w500/" + m.getPoster_path())
                .centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageViewMovieSearchMovie);
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
        private ImageView imageViewMovieSearchMovie;
        private TextView textViewAverageSearchMovie, textViewTitleSearchMovie, textViewReleaseDateSearchMovie, textViewGenresSearchMovie;

        public ViewHolder(View itemView) {
            super(itemView);

            imageViewMovieSearchMovie = (ImageView) itemView.findViewById(R.id.imageViewSearchMovie);
            textViewAverageSearchMovie = (TextView) itemView.findViewById(R.id.textViewAverageSearchMovie);
            textViewTitleSearchMovie = (TextView) itemView.findViewById(R.id.textViewTitleSearchMovie);
            textViewReleaseDateSearchMovie = (TextView) itemView.findViewById(R.id.textViewReleaseDateSearchMovie);
            textViewGenresSearchMovie = (TextView) itemView.findViewById(R.id.textViewGenresSearchMovie);
        }
    }
}

