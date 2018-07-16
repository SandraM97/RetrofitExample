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
import com.example.user.films.Vehicles;

import java.nio.charset.CodingErrorAction;
import java.util.List;

public class VehiclesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;
    List<Vehicles> vehiclesList;
    Context context;

    private static int currentPosition=0;

    public VehiclesAdapter(List<Vehicles> vehiclesList, Context context) {
        this.vehiclesList = vehiclesList;
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
        View v1 = inflater.inflate(R.layout.item_list_vehicles, parent, false);
        viewHolder = new VehiclesViewHolder(v1);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Vehicles vehicles=vehiclesList.get(position);
        switch (getItemViewType(position))
        {
            case ITEM:
                VehiclesViewHolder vehiclesViewHolder=(VehiclesViewHolder)holder;
                vehiclesViewHolder.name.setText(vehicles.getName());
                vehiclesViewHolder.model.setText(vehicles.getModel());
                vehiclesViewHolder.manufacturer.setText(vehicles.getManufacturer());
                vehiclesViewHolder.cost.setText(vehicles.getCost_in_credits());
                vehiclesViewHolder.length.setText(vehicles.getLength());
                vehiclesViewHolder.maxspeed.setText(vehicles.getMax_atmosphering_speed());
                vehiclesViewHolder.crew.setText(vehicles.getCrew());
                vehiclesViewHolder.passengers.setText(vehicles.getPassengers());
                vehiclesViewHolder.cargo.setText(vehicles.getCargo_capacity());
                vehiclesViewHolder.consumables.setText(vehicles.getConsumables());
                vehiclesViewHolder.vehicleClass.setText(vehicles.getVehicle_class());
                vehiclesViewHolder.created.setText(vehicles.getCreated());
                vehiclesViewHolder.edited.setText(vehicles.getEdited());
                vehiclesViewHolder.url.setText(vehicles.getEdited());
                vehiclesViewHolder.tableLayout.setVisibility(View.GONE);
                if(currentPosition==position)
                {
                    Animation sideDown= AnimationUtils.loadAnimation(context,R.anim.slide_down);
                    vehiclesViewHolder.tableLayout.setVisibility(View.VISIBLE);
                    vehiclesViewHolder.tableLayout.setAnimation(sideDown);
                }
                vehiclesViewHolder.name.setOnClickListener(new View.OnClickListener() {
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
        return vehiclesList==null? 0: vehiclesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position==vehiclesList.size()-1&&isLoadingAdded)?LOADING:ITEM;
    }

    public void add(Vehicles vehicles) {
        vehiclesList.add(vehicles);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addAll(List<Vehicles> vehicles) {
        for (Vehicles vehicles1 : vehicles) {
            add(vehicles1);
        }
    }

    public void remove(Vehicles vehicles) {
        int position = vehiclesList.indexOf(vehicles);
        if (position > -1) {
            vehiclesList.remove(position);
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
        add(new Vehicles());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = vehiclesList.size() - 1;
        Vehicles item = getItem(position);

        if (item != null) {
            vehiclesList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Vehicles getItem(int position) {
        return vehiclesList.get(position);
    }


    class VehiclesViewHolder extends RecyclerView.ViewHolder {

        TextView name, model, manufacturer, cost, length, maxspeed, crew, passengers, cargo, consumables, vehicleClass, created, edited, url;
        TableLayout tableLayout;
        public VehiclesViewHolder(@NonNull View itemView) {
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
            vehicleClass=itemView.findViewById(R.id.vehicleClass);
            created=itemView.findViewById(R.id.created);
            edited=itemView.findViewById(R.id.edited);
            url=itemView.findViewById(R.id.url);
            tableLayout=itemView.findViewById(R.id.tableLayout5);
        }
    }

    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {

            super(itemView);

        }

    }
}
