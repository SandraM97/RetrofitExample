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
import com.example.user.films.Starships;

import java.util.List;

public class StarshipAdapter extends RecyclerView.Adapter<StarshipAdapter.StarshipViewHolder>{
    List<Starships> starshipsList;
    Context context;
    private static int currentPosition=0;

    public StarshipAdapter(List<Starships> starshipsList, Context context) {
        this.starshipsList = starshipsList;
        this.context = context;
    }

    @NonNull
    @Override
    public StarshipViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_starships,viewGroup,false);
        return new StarshipAdapter.StarshipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StarshipViewHolder starshipViewHolder, final int position) {
        Starships starships=starshipsList.get(position);
        starshipViewHolder.name.setText(starships.getName());
        starshipViewHolder.model.setText(starships.getModel());
        starshipViewHolder.manufacturer.setText(starships.getManufacturer());
        starshipViewHolder.cost.setText(starships.getCost_in_credits());
        starshipViewHolder.length.setText(starships.getLength());
        starshipViewHolder.maxspeed.setText(starships.getMax_atmosphering_speed());
        starshipViewHolder.crew.setText(starships.getCrew());
        starshipViewHolder.passengers.setText(starships.getPassengers());
        starshipViewHolder.cargo.setText(starships.getCargo_capacity());
        starshipViewHolder.consumables.setText(starships.getConsumables());
        starshipViewHolder.hyperDrieRating.setText(starships.getHyperdrive_rating());
        starshipViewHolder.mglt.setText(starships.getMGLT());
        starshipViewHolder.starshipClass.setText(starships.getStarship_class());
        starshipViewHolder.created.setText(starships.getCreated());
        starshipViewHolder.edited.setText(starships.getEdited());
        starshipViewHolder.url.setText(starships.getUrl());
        starshipViewHolder.tableLayout.setVisibility(View.GONE);

        if(currentPosition==position)
        {
            Animation slideDown= AnimationUtils.loadAnimation(context,R.anim.slide_down);
            starshipViewHolder.tableLayout.setVisibility(View.VISIBLE);
            starshipViewHolder.tableLayout.setAnimation(slideDown);
        }
        starshipViewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPosition=position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return starshipsList.size();
    }

    public void addItem(List<Starships> starships)
    {
        starshipsList.addAll(starships);
        notifyItemInserted(getItemCount()-1);
    }

    class StarshipViewHolder extends RecyclerView.ViewHolder{

        TextView name, model, manufacturer, cost, length, maxspeed, crew, passengers, cargo, consumables,hyperDrieRating,mglt, starshipClass, created, edited, url;
        TableLayout tableLayout;
        public StarshipViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            model=itemView.findViewById(R.id.model);
            manufacturer=itemView.findViewById(R.id.manufacturer);
            cost=itemView.findViewById(R.id.cost);
            length=itemView.findViewById(R.id.length);
            maxspeed=itemView.findViewById(R.id.maxAtmoSpeed);
            crew=itemView.findViewById(R.id.crew);
            passengers=itemView.findViewById(R.id.passengers);
            cargo=itemView.findViewById(R.id.cargoCapacity);
            consumables=itemView.findViewById(R.id.consumables);
            hyperDrieRating=itemView.findViewById(R.id.hyperDriveRating);
            mglt=itemView.findViewById(R.id.mglt);
            starshipClass=itemView.findViewById(R.id.starshipsClass);
            created=itemView.findViewById(R.id.created);
            edited=itemView.findViewById(R.id.edited);
            url=itemView.findViewById(R.id.url);
            tableLayout=itemView.findViewById(R.id.tableLayout6);
        }
    }
}
