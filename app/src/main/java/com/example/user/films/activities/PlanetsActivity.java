package com.example.user.films.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.user.films.Api;
import com.example.user.films.ApiClient;
import com.example.user.films.Planets;
import com.example.user.films.response.PlanetsResponse;
import com.example.user.films.R;
import com.example.user.films.adapters.PlanetsAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class PlanetsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Planets> planetsList=new ArrayList<>();
    PlanetsAdapter planetsAdapter;
    private static String API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        planetsAdapter = new PlanetsAdapter(planetsList, getApplicationContext());

       Api api = ApiClient.getClient().create(Api.class);

        for (int i=1;i<8;i++)
        {
            API_KEY =""+i;

            Call<PlanetsResponse> call = api.getPlanets(API_KEY);
            call.enqueue(new Callback<PlanetsResponse>() {
                @Override
                public void onResponse(Call<PlanetsResponse> call, Response<PlanetsResponse> response) {
                    try {
                        planetsList = response.body().getResults();

                        planetsAdapter.addItem(planetsList);
                    }
                    catch (NullPointerException e)
                    {
                        e.getMessage();
                    }

                }

                @Override
                public void onFailure(Call<PlanetsResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        recyclerView.setAdapter(planetsAdapter);
    }
}
