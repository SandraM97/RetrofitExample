package com.example.user.films.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.user.films.Api;
import com.example.user.films.ApiClient;
import com.example.user.films.R;
import com.example.user.films.Species;
import com.example.user.films.adapters.SpeciesAdapter;
import com.example.user.films.response.SpeciesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpeciesActivity extends AppCompatActivity {

    List<Species> speciesList=new ArrayList<>();
    RecyclerView recyclerView;
    SpeciesAdapter speciesAdapter;

    private static String API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species);


        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        speciesAdapter=new SpeciesAdapter(speciesList,getApplicationContext());

        Api api= ApiClient.getClient().create(Api.class);
        for(int i=0;i<5;i++)
        {
            API_KEY=""+i;
            Call<SpeciesResponse> call=api.getSpecies(API_KEY);
            call.enqueue(new Callback<SpeciesResponse>() {
                @Override
                public void onResponse(Call<SpeciesResponse> call, Response<SpeciesResponse> response) {
                    try{
                        speciesList=response.body().getResults();
                        speciesAdapter.addItem(speciesList);
                    }
                    catch (NullPointerException e)
                    {
                        e.getMessage();
                    }
                }

                @Override
                public void onFailure(Call<SpeciesResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        recyclerView.setAdapter(speciesAdapter);

    }
}
