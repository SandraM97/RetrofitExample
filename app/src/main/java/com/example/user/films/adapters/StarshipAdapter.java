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

public class StarshipAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;
    List<Starships> starshipsList;
    Context context;
    private static int currentPosition=0;

    public StarshipAdapter(List<Starships> starshipsList, Context context) {
        this.starshipsList = starshipsList;
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
        View v1 = inflater.inflate(R.layout.item_list_starships, parent, false);
        viewHolder = new StarshipViewHolder(v1);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Starships starships=starshipsList.get(position);
        switch (getItemViewType(position)){
            case ITEM:
                StarshipViewHolder starshipViewHolder=(StarshipViewHolder)holder;
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
                break;
            case LOADING:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return starshipsList==null? 0: starshipsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position==starshipsList.size()-1&&isLoadingAdded)?LOADING:ITEM;
    }

    public void add(Starships starships) {
        starshipsList.add(starships);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addAll(List<Starships> starships) {
        for (Starships starships1 : starships) {
            add(starships1);
        }
    }

    public void remove(Starships starships) {
        int position = starshipsList.indexOf(starships);
        if (position > -1) {
            starshipsList.remove(position);
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
        add(new Starships());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = starshipsList.size() - 1;
        Starships item = getItem(position);

        if (item != null) {
            starshipsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Starships getItem(int position) {
        return starshipsList.get(position);
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
    protected class LoadingVH extends RecyclerView.ViewHolder {



        public LoadingVH(View itemView) {

            super(itemView);

        }

    }
}
