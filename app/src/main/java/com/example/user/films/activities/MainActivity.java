package com.example.user.films.activities;

import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.films.Api;
import com.example.user.films.ApiClient;
import com.example.user.films.PaginationScrollListener;
import com.example.user.films.adapters.CharactersAdapter;
import com.example.user.films.adapters.FilmAdapter;
import com.example.user.films.response.FilmResponse;
import com.example.user.films.Films;
import com.example.user.films.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FilmAdapter filmAdapter;
    RecyclerView recyclerView;
    List<Films> filmsList=new ArrayList<>();
    ProgressBar progressBar;
    private boolean isLoading = false;

    Api api;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        filmAdapter=new FilmAdapter(filmsList,this);
        recyclerView.setAdapter(filmAdapter);




        api= ApiClient.getClient().create(Api.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadPage();
            }
        },1000);

    }

    private Call<FilmResponse> filmResponseCall(){
        return api.getFilms();
    }

    private void loadPage(){
        filmResponseCall().enqueue(new Callback<FilmResponse>() {
            @Override
            public void onResponse(Call<FilmResponse> call, Response<FilmResponse> response) {
                try{
                    List<Films> films=response.body().getResults();
                    progressBar.setVisibility(View.GONE);
                    filmAdapter.addAll(films);

                }
                catch (NullPointerException e) {
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
