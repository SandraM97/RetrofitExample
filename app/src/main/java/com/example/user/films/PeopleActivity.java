package com.example.user.films;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PeopleAdapter peopleAdapter;
    List<People>people=new ArrayList<>();
    Api api;
    private static String PAGE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        api = ApiClient.getClient().create(Api.class);

        peopleAdapter=new PeopleAdapter(people,getApplicationContext());

        for (int i = 1; i < 10; i++) {
                PAGE =""+i;
            Call<PeopleResponse> call=api.getPeople(PAGE);
            call.enqueue(new Callback<PeopleResponse>() {
                @Override
                public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                    people=response.body().getResults();

                        peopleAdapter.addItem(people);
                }

                @Override
                public void onFailure(Call<PeopleResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
            recyclerView.setAdapter(peopleAdapter);
        }

    }
}
