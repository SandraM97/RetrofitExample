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

import com.example.user.films.People;
import com.example.user.films.R;

import java.util.List;


public class PeopleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<People> peopleList;
    private Context context;

    private static int currentPosition = 0;
    private boolean isLoadingAdded = false;

    public PeopleAdapter(List<People> people,Context context) {
        this.peopleList = people;
        this.context = context;
    }

    public List<People> getPeopleList() {
        return peopleList;
    }

    public void setPeopleList(List<People> peopleList) {
        this.peopleList = peopleList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
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
        View v1 = inflater.inflate(R.layout.item_list_people, parent, false);
        viewHolder = new PeopleViewHolder(v1);
        return viewHolder;

    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        People people = peopleList.get(position);

        switch (getItemViewType(position)){

            case ITEM:
                final PeopleViewHolder peopleViewHolder=(PeopleViewHolder)holder;
                peopleViewHolder.name.setText(people.getName());
                peopleViewHolder.height.setText(people.getHeight());
                peopleViewHolder.mass.setText(people.getMass());
                peopleViewHolder.hairColor.setText(people.getHair_color());
                peopleViewHolder.skinColor.setText(people.getSkin_color());
                peopleViewHolder.eyeColor.setText(people.getEye_color());
                peopleViewHolder.birthYear.setText(people.getBirth_year());
                peopleViewHolder.gender.setText(people.getGender());
                peopleViewHolder.created.setText(people.getCreated());
                peopleViewHolder.edited.setText(people.getEdited());
                peopleViewHolder.url.setText(people.getUrl());


                peopleViewHolder.tableLayout.setVisibility(View.GONE);

                if (currentPosition == position) {
                    Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

                    peopleViewHolder.tableLayout.setVisibility(View.VISIBLE);

                    peopleViewHolder.tableLayout.startAnimation(slideDown);
                }

                peopleViewHolder.name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        currentPosition = position;

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
        return peopleList==null? 0: peopleList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position==peopleList.size()-1&&isLoadingAdded)?LOADING:ITEM;
    }

    public void add(People p) {
        peopleList.add(p);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addAll(List<People> people) {
        for (People p : people) {
            add(p);
        }
    }

    public void remove(People people) {
        int position = peopleList.indexOf(people);
        if (position > -1) {
            peopleList.remove(position);
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
        add(new People());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = peopleList.size() - 1;
        People item = getItem(position);

        if (item != null) {
            peopleList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public People getItem(int position) {
        return peopleList.get(position);
    }



    class PeopleViewHolder extends RecyclerView.ViewHolder {
        TextView name, height, mass, hairColor, skinColor, eyeColor, birthYear, gender, created, edited, url;
        TableLayout tableLayout;
        RecyclerView films, starships, vehicles, species;
        PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            height = itemView.findViewById(R.id.height);
            mass = itemView.findViewById(R.id.mass);
            hairColor = itemView.findViewById(R.id.hairColor);
            skinColor = itemView.findViewById(R.id.skinColor);
            eyeColor = itemView.findViewById(R.id.eyeColor);
            birthYear = itemView.findViewById(R.id.birthYear);
            gender = itemView.findViewById(R.id.gender);
            created =itemView.findViewById(R.id.created);
            edited = itemView.findViewById(R.id.edited);
            url = itemView.findViewById(R.id.url);


            tableLayout = (TableLayout) itemView.findViewById(R.id.tableLayout2);
        }
    }
    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {

            super(itemView);

        }

    }


}