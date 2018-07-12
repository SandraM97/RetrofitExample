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

/**
 * Created by sandr on 7/11/2018.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private List<People> peopleList;
    private Context context;

    private static int currentPosition = 0;

    public PeopleAdapter(List<People> peopleList, Context context) {
        this.peopleList = peopleList;
        this.context = context;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_characters, viewGroup, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PeopleViewHolder peopleViewHolder, final int position) {
        People people = peopleList.get(position);
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
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public void addItem(List<People> people)
    {
        peopleList.addAll(people);
        notifyItemInserted(getItemCount()-1);
    }


    class PeopleViewHolder extends RecyclerView.ViewHolder {
        TextView name, height, mass, hairColor, skinColor, eyeColor, birthYear, gender, created, edited, url;
        TableLayout tableLayout;
        RecyclerView films, starships, vehicles, species;

        PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            height = (TextView) itemView.findViewById(R.id.height);
            mass = (TextView) itemView.findViewById(R.id.mass);

            hairColor = (TextView) itemView.findViewById(R.id.hairColor);
            skinColor = (TextView) itemView.findViewById(R.id.skinColor);
            eyeColor = (TextView) itemView.findViewById(R.id.eyeColor);
            birthYear=itemView.findViewById(R.id.birthYear);
            gender=itemView.findViewById(R.id.gender);
            created=itemView.findViewById(R.id.created);
            edited=itemView.findViewById(R.id.edited);
            url=itemView.findViewById(R.id.url);


            tableLayout = (TableLayout) itemView.findViewById(R.id.tableLayout2);
        }
    }
}