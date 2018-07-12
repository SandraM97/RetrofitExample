package com.example.user.films;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private List<Films> filmsList;
    private Context context;

    private static int currentPosition=0;

    public FilmAdapter(List<Films> filmsList, Context context) {
        this.filmsList = filmsList;
        this.context = context;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FilmViewHolder filmViewHolder, final int position) {
        Films films=filmsList.get(position);
        filmViewHolder.title.setText(films.getTitle());
        filmViewHolder.episodeId.setText(films.getEpisode_id());
        filmViewHolder.opening.setText(films.getOpening_crawl());
        filmViewHolder.director.setText(films.getDirector());
        filmViewHolder.producer.setText(films.getProducer());
        filmViewHolder.releaseDate.setText(films.getRelease_date());
        filmViewHolder.created.setText(films.getCreated());
        filmViewHolder.edited.setText(films.getEdited());
        filmViewHolder.url.setText(films.getUrl());


        filmViewHolder.tableLayout.setVisibility(View.GONE);

        if(currentPosition==position) {
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

            filmViewHolder.tableLayout.setVisibility(View.VISIBLE);

            filmViewHolder.tableLayout.startAnimation(slideDown);
        }

        filmViewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPosition=position;

               notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmsList.size();
    }

    class FilmViewHolder extends RecyclerView.ViewHolder {
        TextView title, episodeId, opening, director, producer, releaseDate, created, edited, url;
        TableLayout tableLayout;
        RecyclerView characters, planets, starships, vehicles, species;
        FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            episodeId=(TextView)itemView.findViewById(R.id.episodeId);
            opening=(TextView)itemView.findViewById(R.id.opening);
            director=(TextView)itemView.findViewById(R.id.director);
            producer=(TextView)itemView.findViewById(R.id.producer);
            releaseDate=(TextView)itemView.findViewById(R.id.releaseDate);
            created=(TextView)itemView.findViewById(R.id.created);
            edited=(TextView)itemView.findViewById(R.id.edited);
            url=(TextView)itemView.findViewById(R.id.url);


            /*characters=(RecyclerView)itemView.findViewById(R.id.characters);
            planets=(RecyclerView)itemView.findViewById(R.id.planets);
            starships=(RecyclerView)itemView.findViewById(R.id.starships);
            vehicles=(RecyclerView)itemView.findViewById(R.id.vehicles);
            species=(RecyclerView)itemView.findViewById(R.id.species);
*/
            tableLayout=(TableLayout)itemView.findViewById(R.id.tableLayout);
        }
    }
}
