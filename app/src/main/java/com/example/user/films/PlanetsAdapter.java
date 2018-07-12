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

public class PlanetsAdapter extends RecyclerView.Adapter<PlanetsAdapter.PlanetsViewHolder>{

    List<Planets> planetsList;
    Context context;
    private static int currentPosition = 0;

    public PlanetsAdapter(List<Planets> planetsList, Context context) {
        this.planetsList= planetsList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlanetsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_planets, viewGroup, false);
        return new PlanetsAdapter.PlanetsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetsViewHolder planetsViewHolder, final int position) {
        Planets planets=planetsList.get(currentPosition);
        planetsViewHolder.name.setText(planets.getName());
        planetsViewHolder.rotationPeriod.setText(planets.getRotation_period());
        planetsViewHolder.orbitalPeriod.setText(planets.getOrbital_period());
        planetsViewHolder.diameter.setText(planets.getDiameter());
        planetsViewHolder.climate.setText(planets.getClimate());
        planetsViewHolder.terrain.setText(planets.getTerrain());
        planetsViewHolder.surfaceWater.setText(planets.getSurface_water());
        planetsViewHolder.population.setText(planets.getPopulation());
        planetsViewHolder.created.setText(planets.getCreated());
        planetsViewHolder.edited.setText(planets.getEdited());
        planetsViewHolder.url.setText(planets.getUrl());

        planetsViewHolder.tableLayout.setVisibility(View.GONE);

        if(currentPosition==position)
        {
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);
            planetsViewHolder.tableLayout.setVisibility(View.VISIBLE);
            planetsViewHolder.tableLayout.setAnimation(slideDown);
        }

        planetsViewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPosition=position;

                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
       return planetsList.size();
    }

    class PlanetsViewHolder extends RecyclerView.ViewHolder{
        TextView name, rotationPeriod, orbitalPeriod, diameter, climate, gravity, terrain, surfaceWater, population, created, edited, url;
        TableLayout tableLayout;
        RecyclerView residents, films;

        public PlanetsViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            rotationPeriod=itemView.findViewById(R.id.rotationPeriod);
            orbitalPeriod=itemView.findViewById(R.id.orbitalPeriod);
            diameter=itemView.findViewById(R.id.diameter);
            climate=itemView.findViewById(R.id.climate);
            terrain=itemView.findViewById(R.id.terrain);
            surfaceWater=itemView.findViewById(R.id.surfaceWater);
            population=itemView.findViewById(R.id.population);
            created=itemView.findViewById(R.id.created);
            edited=itemView.findViewById(R.id.edited);
            url=itemView.findViewById(R.id.url);
            tableLayout=itemView.findViewById(R.id.tableLayout3);
        }
    }
}
