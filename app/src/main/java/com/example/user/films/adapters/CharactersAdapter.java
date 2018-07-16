package com.example.user.films.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.films.Films;
import com.example.user.films.People;
import com.example.user.films.R;
import com.example.user.films.Species;
import com.example.user.films.activities.PeopleActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder> {

    private Context context;
    private Films films;
    private List<String> characters;
    private String[]s;

    public CharactersAdapter(String[] s,Context context ) {
        this.s = s;
        this.context=context;
    }

    @NonNull
    @Override
    public CharactersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items, viewGroup, false);
        CharactersViewHolder holder = new CharactersViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull CharactersViewHolder charactersViewHolder, int position) {
        charactersViewHolder.name.setText(s[position]);

    }

    @Override
    public int getItemCount() {
        return s.length;
    }

    public void addAll(String[] s)
    {
        List<String> arrayList=Arrays.asList(s);
        characters.addAll(arrayList);
        notifyItemInserted(getItemCount()-1);
    }

    class CharactersViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public CharactersViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tv);
        }
    }
}
