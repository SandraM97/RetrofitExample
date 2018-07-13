package com.example.user.films.activities;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.user.films.Api;
import com.example.user.films.ApiClient;
import com.example.user.films.R;
import com.example.user.films.Starships;
import com.example.user.films.adapters.StarshipAdapter;
import com.example.user.films.response.StartshipsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarshipsActivity extends AppCompatActivity {

    List<Starships>starshipsList=new ArrayList<>();
    StarshipAdapter starshipAdapter;
    RecyclerView recyclerView;

    private static String API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starships);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Api api= ApiClient.getClient().create(Api.class);
        starshipAdapter=new StarshipAdapter(starshipsList,getApplicationContext());

        for(int i=0;i<5;i++){
            API_KEY=""+i;
            Call<StartshipsResponse> call=api.getStarships(API_KEY);
            call.enqueue(new Callback<StartshipsResponse>() {
                @Override
                public void onResponse(Call<StartshipsResponse> call, Response<StartshipsResponse> response) {
                    try{
                        starshipsList=response.body().getResults();
                        starshipAdapter.addItem(starshipsList);
                    }
                    catch (NullPointerException e)
                    {
                        e.getMessage();
                    }
                }

                @Override
                public void onFailure(Call<StartshipsResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        recyclerView.setAdapter(starshipAdapter);
    }
}
