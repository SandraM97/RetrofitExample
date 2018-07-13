package com.example.user.films.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.user.films.Api;
import com.example.user.films.ApiClient;
import com.example.user.films.adapters.FilmAdapter;
import com.example.user.films.response.FilmResponse;
import com.example.user.films.Films;
import com.example.user.films.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FilmAdapter filmAdapter;
    RecyclerView recyclerView,Characters;
    List<Films> filmsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Characters=findViewById(R.id.characters);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Api api= ApiClient.getClient().create(Api.class);
        Call<FilmResponse> call=api.getFilms();
        call.enqueue(new Callback<FilmResponse>() {
           @Override
           public void onResponse(Call<FilmResponse> call, Response<FilmResponse> response) {
               try {
                   filmsList = response.body().getResults();
                   filmAdapter = new FilmAdapter(filmsList, getApplicationContext());
                   recyclerView.setAdapter(filmAdapter);
               }
               catch (NullPointerException e)
               {
                   e.getMessage();
               }
           }

           @Override
           public void onFailure(Call<FilmResponse> call, Throwable t) {
               Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
           }
       });

    }
}
