package com.example.user.films;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class PlanetsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Planets> planetsList;
    PlanetsAdapter planetsAdapter;
    Api api;
    private static String PAGE=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        api = ApiClient.getClient().create(Api.class);

            Call<PlanetsResponse> call = api.getPlanets(PAGE);
            call.enqueue(new Callback<PlanetsResponse>() {
                @Override
                public void onResponse(Call<PlanetsResponse> call, Response<PlanetsResponse> response) {
                    planetsList = response.body().getResults();
                    planetsAdapter = new PlanetsAdapter(planetsList, getApplicationContext());
                    recyclerView.setAdapter(planetsAdapter);
                }

                @Override
                public void onFailure(Call<PlanetsResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
    }
}
