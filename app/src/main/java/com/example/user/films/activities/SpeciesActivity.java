package com.example.user.films.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.films.Api;
import com.example.user.films.ApiClient;
import com.example.user.films.PaginationScrollListener;
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
    ProgressBar progressBar;
    SpeciesAdapter speciesAdapter;
    // Index from which pagination should start (0 is 1st page in our case)
    private static final int PAGE_START = 1;
    // Indicates if footer ProgressBar is shown (i.e. next page is loading)
    private boolean isLoading = false;
    // If current page is the last page (Pagination will stop after this page load)
    private boolean isLastPage = false;
    // total no. of pages to load. Initial load is page 0, after which 2 more pages will load.
    private int TOTAL_PAGES = 4;
    // indicates the current page which Pagination is fetching.
    private int currentPage = PAGE_START;
    LinearLayoutManager linearLayoutManager;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species);

        speciesAdapter=new SpeciesAdapter(speciesList,this);
        recyclerView=findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.progressBar);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(speciesAdapter);



        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {

                loadFirstPage();

            }

        }, 1000);

        recyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;

                currentPage += 1;

                // mocking network delay for API call


                new Handler().postDelayed(new Runnable() {

                    @Override

                    public void run() {

                        loadNextPage();

                    }

                }, 1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
        api= ApiClient.getClient().create(Api.class);

    }

    private Call<SpeciesResponse> speciesResponseCall(){
        return api.getSpecies(currentPage);
    }

    private List<Species> fetchResults(Response<SpeciesResponse> response){
        SpeciesResponse speciesResponse=response.body();
        return speciesResponse.getResults();
    }

    private void loadFirstPage(){
        speciesResponseCall().enqueue(new Callback<SpeciesResponse>() {
            @Override
            public void onResponse(Call<SpeciesResponse> call, Response<SpeciesResponse> response) {
                try{
                    List<Species> speciesList=fetchResults(response);
                    progressBar.setVisibility(View.GONE);
                    speciesAdapter.addAll(speciesList);
                    if (currentPage <= TOTAL_PAGES)
                        speciesAdapter.addLoadingFooter();
                    else isLastPage = true;
                }
               catch (NullPointerException e) {
                   e.getMessage();
               }
            }

            @Override
            public void onFailure(Call<SpeciesResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadNextPage(){
        speciesResponseCall().enqueue(new Callback<SpeciesResponse>() {
            @Override
            public void onResponse(Call<SpeciesResponse> call, Response<SpeciesResponse> response) {
                try{
                    speciesAdapter.removeLoadingFooter();
                    isLoading=false;
                    List<Species> speciesList=fetchResults(response);
                    speciesAdapter.addAll(speciesList);
                    if (currentPage != TOTAL_PAGES) speciesAdapter.addLoadingFooter();
                    else isLastPage = true;
                }
                catch (NullPointerException e) {
                    e.getMessage();
                }
            }

            @Override
            public void onFailure(Call<SpeciesResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
