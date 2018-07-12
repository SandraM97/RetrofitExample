package com.example.user.films;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    FilmAdapter filmAdapter;
    RecyclerView recyclerView,Characters;
    List<Films> filmsList;
    Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Characters=(RecyclerView) findViewById(R.id.characters);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        api=ApiClient.getClient().create(Api.class);
        Call<FilmResponse> call=api.getFilms();
        call.enqueue(new Callback<FilmResponse>() {
           @Override
           public void onResponse(Call<FilmResponse> call, Response<FilmResponse> response) {
                filmsList=response.body().getResults();
                filmAdapter=new FilmAdapter(filmsList,getApplicationContext());
                recyclerView.setAdapter(filmAdapter);
              /*  Call<PeopleResponse> peopleResponseCall=api.getPeople();
                peopleResponseCall.enqueue(new Callback<PeopleResponse>() {
                   @Override
                   public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                       List<People> peopleList=response.body().getResults();
                       for (int i=0;i<filmsList.size();i++)
                       {
                           for(int j=0;j<peopleList.size();j++)
                           {
                               if( filmsList.get(i).getCharacters().equals(peopleList.get(j).getUrl()))
                               {


                               }

                           }
                       }

                   }

                   @Override
                   public void onFailure(Call<PeopleResponse> call, Throwable t) {
                       Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                   }
               });*/

           }

           @Override
           public void onFailure(Call<FilmResponse> call, Throwable t) {
               Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
           }
       });

    }
}
