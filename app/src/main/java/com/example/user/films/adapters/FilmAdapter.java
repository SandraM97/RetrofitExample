package com.example.user.films.adapters;

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

import com.example.user.films.Films;
import com.example.user.films.R;

import java.util.Arrays;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private List<Films> filmsList;
    private Context context;
    private boolean isLoadingAdded = false;
    private static int currentPosition=0;
    private CharactersAdapter charactersAdapter;

    public FilmAdapter(List<Films> filmsList, Context context) {
        this.filmsList = filmsList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());

        switch (viewType)
        {
            case ITEM:
                viewHolder=getViewHolder(viewGroup,layoutInflater);
                break;
            case LOADING:
                View v2 = layoutInflater.inflate(R.layout.item_progress, viewGroup, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }
    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {

        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.list_item_films, parent, false);
        viewHolder = new FilmViewHolder(v1);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Films films=filmsList.get(position);
        switch (getItemViewType(position)){
            case ITEM:
                FilmViewHolder filmViewHolder=(FilmViewHolder)holder;
                filmViewHolder.title.setText(films.getTitle());
                filmViewHolder.episodeId.setText(films.getEpisode_id());
                filmViewHolder.opening.setText(films.getOpening_crawl());
                filmViewHolder.director.setText(films.getDirector());
                filmViewHolder.producer.setText(films.getProducer());
                filmViewHolder.releaseDate.setText(films.getRelease_date());
                filmViewHolder.created.setText(films.getCreated());
                filmViewHolder.edited.setText(films.getEdited());
                filmViewHolder.url.setText(films.getUrl());
                charactersAdapter=new CharactersAdapter(films.getCharacters(),context);
                filmViewHolder.characters.setAdapter(charactersAdapter);

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
                break;
            case LOADING:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return filmsList==null? 0 : filmsList.size();
    }
    @Override
    public int getItemViewType(int position) {
        return (position==filmsList.size()-1&&isLoadingAdded)?LOADING:ITEM;
    }

    public void add(Films film) {
        filmsList.add(film);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addAll(List<Films> films) {
        for (Films films1 : films) {
            add(films1);
        }
    }

    public void remove(Films films) {
        int position = filmsList.indexOf(films);
        if (position > -1) {
            filmsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Films());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = filmsList.size() - 1;
        Films item = getItem(position);

        if (item != null) {
            filmsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Films getItem(int position) {
        return filmsList.get(position);
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


            characters=(RecyclerView)itemView.findViewById(R.id.characters);
            planets=(RecyclerView)itemView.findViewById(R.id.planets);
            starships=(RecyclerView)itemView.findViewById(R.id.starships);
            vehicles=(RecyclerView)itemView.findViewById(R.id.vehicles);
            species=(RecyclerView)itemView.findViewById(R.id.species);
            tableLayout=(TableLayout)itemView.findViewById(R.id.tableLayout);
        }
    }
    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {

            super(itemView);

        }

    }

}
