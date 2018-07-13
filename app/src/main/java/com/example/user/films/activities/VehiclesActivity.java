package com.example.user.films.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.user.films.Api;
import com.example.user.films.ApiClient;
import com.example.user.films.R;
import com.example.user.films.Vehicles;
import com.example.user.films.adapters.VehiclesAdapter;
import com.example.user.films.response.VehiclesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehiclesActivity extends AppCompatActivity {
    List<Vehicles>vehiclesList=new ArrayList<>();
    RecyclerView recyclerView;
    VehiclesAdapter vehiclesAdapter;

    private static String API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Api api= ApiClient.getClient().create(Api.class);
        vehiclesAdapter=new VehiclesAdapter(vehiclesList,getApplicationContext());

        for(int i=0;i<5;i++)
        {
            API_KEY=""+i;

            Call<VehiclesResponse> call=api.getVehicles(API_KEY);
            call.enqueue(new Callback<VehiclesResponse>() {
                @Override
                public void onResponse(Call<VehiclesResponse> call, Response<VehiclesResponse> response) {
                    try {
                        vehiclesList=response.body().getResults();
                        vehiclesAdapter.addItem(vehiclesList);
                    }
                    catch (NullPointerException e)
                    {
                        e.getMessage();
                    }
                }

                @Override
                public void onFailure(Call<VehiclesResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        recyclerView.setAdapter(vehiclesAdapter);
    }
}
