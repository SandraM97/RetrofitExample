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

import com.example.user.films.R;
import com.example.user.films.Species;

import java.util.List;

public class SpeciesAdapter extends RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder>{
    List<Species> speciesList;
    Context context;

    private static int currentPosition=0;

    public SpeciesAdapter(List<Species> speciesList, Context context) {
        this.speciesList = speciesList;
        this.context = context;
    }

    @NonNull
    @Override
    public SpeciesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_species, viewGroup,false);
        return new SpeciesAdapter.SpeciesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeciesViewHolder speciesViewHolder, final int position) {
        Species species=speciesList.get(position);
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
    }

    @Override
    public int getItemCount() {
        return speciesList.size();
    }

    public void addItem(List<Species> species){
        speciesList.addAll(species);
        notifyItemInserted(getItemCount()-1);
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

}
