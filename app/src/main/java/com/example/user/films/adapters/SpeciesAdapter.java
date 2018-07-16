package com.example.user.films.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.user.films.R;
import com.example.user.films.Species;

import java.util.List;

public class SpeciesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<Species> speciesList;
    Context context;

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;

    private static int currentPosition=0;

    public SpeciesAdapter(List<Species> speciesList, Context context) {
        this.speciesList = speciesList;
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
        View v1 = inflater.inflate(R.layout.item_list_species, parent, false);
        viewHolder = new SpeciesViewHolder(v1);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Species species=speciesList.get(position);
        switch (getItemViewType(position)){
            case ITEM:
                SpeciesViewHolder speciesViewHolder=(SpeciesViewHolder)holder;
                speciesViewHolder.name.setText(species.getName());
                speciesViewHolder.classification.setText(species.getClassification());
                speciesViewHolder.designation.setText(species.getDesignation());
                speciesViewHolder.averageHeight.setText(species.getAverage_height());
                speciesViewHolder.skinColor.setText(species.getSkin_colors());
                speciesViewHolder.hairColor.setText(species.getHair_colors());
                speciesViewHolder.eyeColor.setText(species.getEye_colors());
                speciesViewHolder.averageLifespan.setText(species.getAverage_lifespan());
                speciesViewHolder.homeworld.setText(species.getHomeworld());
                speciesViewHolder.language.setText(species.getLanguage());
                speciesViewHolder.created.setText(species.getCreated());
                speciesViewHolder.edited.setText(species.getEdited());
                speciesViewHolder.url.setText(species.getUrl());

                speciesViewHolder.tableLayout.setVisibility(View.GONE);

                if(currentPosition==position)
                {
                    Animation slideDown= AnimationUtils.loadAnimation(context,R.anim.slide_down);
                    speciesViewHolder.tableLayout.setVisibility(View.VISIBLE);
                    speciesViewHolder.tableLayout.setAnimation(slideDown);
                }
                speciesViewHolder.name.setOnClickListener(new View.OnClickListener() {
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
        return speciesList==null? 0: speciesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position==speciesList.size()-1&&isLoadingAdded)?LOADING:ITEM;
    }

    public void add(Species species) {
        speciesList.add(species);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addAll(List<Species> species) {
        for (Species species1 : species) {
            add(species1);
        }
    }

    public void remove(Species species) {
        int position = speciesList.indexOf(species);
        if (position > -1) {
            speciesList.remove(position);
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
        add(new Species());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = speciesList.size() - 1;
        Species item = getItem(position);

        if (item != null) {
            speciesList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Species getItem(int position) {
        return speciesList.get(position);
    }


    class SpeciesViewHolder extends RecyclerView.ViewHolder{
        TextView name, classification, designation, averageHeight, skinColor, hairColor, eyeColor, averageLifespan,homeworld, language,created, edited, url;
        TableLayout tableLayout;
        RecyclerView people, films;

        public SpeciesViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            classification=itemView.findViewById(R.id.classification);
            designation=itemView.findViewById(R.id.designation);
            averageHeight=itemView.findViewById(R.id.averageHeight);
            skinColor=itemView.findViewById(R.id.skinColor);
            hairColor=itemView.findViewById(R.id.hairColor);
            eyeColor=itemView.findViewById(R.id.eyeColor);
            averageLifespan=itemView.findViewById(R.id.averageLifespan);
            homeworld=itemView.findViewById(R.id.homeworld);
            language=itemView.findViewById(R.id.language);
            created=itemView.findViewById(R.id.created);
            edited=itemView.findViewById(R.id.edited);
            url=itemView.findViewById(R.id.url);
            tableLayout=itemView.findViewById(R.id.tableLayout4);
        }
    }
    protected class LoadingVH extends RecyclerView.ViewHolder {



        public LoadingVH(View itemView) {

            super(itemView);

        }

    }

}
