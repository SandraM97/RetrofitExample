package com.example.user.films.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.films.People;
import com.example.user.films.R;
import com.example.user.films.activities.PeopleActivity;

import java.util.List;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder> {

    private Context context;
    private List<People> people;

    public CharactersAdapter(Context context, List<People> people) {
        this.context = context;
        this.people = people;
    }

    @NonNull
    @Override
    public CharactersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CharactersViewHolder viewHolder=null;
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        viewHolder=getViewHolder(viewGroup,layoutInflater);
        return viewHolder;
    }
    @NonNull
    private CharactersViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {

        CharactersViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_list_people, parent, false);
        viewHolder = new CharactersViewHolder(v1);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CharactersViewHolder charactersViewHolder, int position) {
        People people1=people.get(position);
        charactersViewHolder.name.setText(people1.getName());
        charactersViewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), PeopleActivity.class);
            }
        });

    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    class CharactersViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public CharactersViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
        }
    }
}
