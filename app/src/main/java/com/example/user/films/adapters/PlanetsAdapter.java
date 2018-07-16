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

import com.example.user.films.Planets;
import com.example.user.films.R;

import java.util.List;

public class PlanetsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    List<Planets> planetsList;
    Context context;
    private boolean isLoadingAdded = false;
    private static int currentPosition = 0;

    public PlanetsAdapter(List<Planets> planetsList, Context context) {
        this.planetsList= planetsList;
        this.context = context;
    }

    public List<Planets> getPlanetsList() {
        return planetsList;
    }

    public void setPlanetsList(List<Planets> planetsList) {
        this.planetsList = planetsList;
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
        View v1 = inflater.inflate(R.layout.item_list_planets, parent, false);
        viewHolder = new PlanetsViewHolder(v1);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Planets planets=planetsList.get(position);
        switch (getItemViewType(position)){
            case ITEM:
                final PlanetsViewHolder planetsViewHolder=(PlanetsViewHolder)holder;
                planetsViewHolder.name.setText(planets.getName());
                planetsViewHolder.rotationPeriod.setText(planets.getRotation_period());
                planetsViewHolder.orbitalPeriod.setText(planets.getOrbital_period());
                planetsViewHolder.diameter.setText(planets.getDiameter());
                planetsViewHolder.climate.setText(planets.getClimate());
                planetsViewHolder.gravity.setText(planets.getGravity());
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
                break;
            case LOADING:
                break;

        }

    }

    @Override
    public int getItemCount() {
        return planetsList==null? 0: planetsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position==planetsList.size()-1&&isLoadingAdded)?LOADING:ITEM;
    }

    public void add(Planets p) {
        planetsList.add(p);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addAll(List<Planets> planets) {
        for (Planets p : planets) {
            add(p);
        }
    }

    public void remove(Planets planets) {
        int position = planetsList.indexOf(planets);
        if (position > -1) {
            planetsList.remove(position);
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
        add(new Planets());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = planetsList.size() - 1;
        Planets item = getItem(position);

        if (item != null) {
            planetsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Planets getItem(int position) {
        return planetsList.get(position);
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
            gravity=itemView.findViewById(R.id.gravity);
            terrain=itemView.findViewById(R.id.terrain);
            surfaceWater=itemView.findViewById(R.id.surfaceWater);
            population=itemView.findViewById(R.id.population);
            created=itemView.findViewById(R.id.created);
            edited=itemView.findViewById(R.id.edited);
            url=itemView.findViewById(R.id.url);
            tableLayout=itemView.findViewById(R.id.tableLayout3);
        }
    }
    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {

            super(itemView);

        }

    }
}
