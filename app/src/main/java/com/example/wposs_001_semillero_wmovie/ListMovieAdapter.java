package com.example.wposs_001_semillero_wmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wposs_001_semillero_wmovie.models.WMovie;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ViewHolder>{
    private ArrayList<WMovie> dataset;
    private Context context;

    public ListMovieAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WMovie m = dataset.get(position);
        holder.textViewAverage.setText(String.valueOf(m.getVote_average()));
        holder.textViewTitle.setText(m.getTitle());
        holder.textViewReleaseDate.setText(m.getRelease_date());


        Glide.with(context).
                load("https://image.tmdb.org/t/p/w500/"+m.getPoster_path())
                .centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageViewMovie);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<WMovie> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewMovie;
        private TextView textViewAverage;
        private TextView textViewTitle;
        private TextView textViewReleaseDate;
        public ViewHolder(View itemView) {
            super(itemView);

            imageViewMovie = (ImageView) itemView.findViewById(R.id.imageViewMovie);
            textViewAverage = (TextView) itemView.findViewById(R.id.textViewAverage);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewReleaseDate = (TextView) itemView.findViewById(R.id.textViewReleaseDate);
        }
    }
}
