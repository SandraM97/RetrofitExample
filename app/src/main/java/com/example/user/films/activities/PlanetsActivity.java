package com.example.user.films.activities;

import android.os.Handler;
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
    ProgressBar progressBar;
    // Index from which pagination should start (0 is 1st page in our case)
    private static final int PAGE_START = 1;
    // Indicates if footer ProgressBar is shown (i.e. next page is loading)
    private boolean isLoading = false;
    // If current page is the last page (Pagination will stop after this page load)
    private boolean isLastPage = false;
    // total no. of pages to load. Initial load is page 0, after which 2 more pages will load.
    private int TOTAL_PAGES = 7;
    // indicates the current page which Pagination is fetching.
    private int currentPage = PAGE_START;
    LinearLayoutManager linearLayoutManager;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets);
        progressBar=findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        planetsAdapter = new PlanetsAdapter(planetsList, this);
        recyclerView.setAdapter(planetsAdapter);

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

        // mocking network delay for API call


        api = ApiClient.getClient().create(Api.class);

    }

    private Call<PlanetsResponse> planetsResponseCall() {
        return api.getPlanets(currentPage);
    }

    private List<Planets> fetchResults(Response<PlanetsResponse> response){
        PlanetsResponse planetsResponse=response.body();
        return planetsResponse.getResults();

    }

    private void loadFirstPage(){
        planetsResponseCall().enqueue(new Callback<PlanetsResponse>() {
            @Override
            public void onResponse(Call<PlanetsResponse> call, Response<PlanetsResponse> response) {
                try {
                    List<Planets> planets = fetchResults(response);
                    progressBar.setVisibility(View.GONE);
                    planetsAdapter.addAll(planets);
                    if (currentPage <= TOTAL_PAGES) planetsAdapter.addLoadingFooter();
                    else isLastPage = true;
                }
                catch (Exception e)
                {
                    e.getMessage();
                }

            }

            @Override
            public void onFailure(Call<PlanetsResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadNextPage() {
        planetsResponseCall().enqueue(new Callback<PlanetsResponse>() {
            @Override
            public void onResponse(Call<PlanetsResponse> call, Response<PlanetsResponse> response) {
                try {
                    planetsAdapter.removeLoadingFooter();
                    isLoading = false;
                    List<Planets> planetsList = fetchResults(response);
                    planetsAdapter.addAll(planetsList);

                    if (currentPage != TOTAL_PAGES) planetsAdapter.addLoadingFooter();
                    else isLastPage = true;
                }
                catch (Exception e)
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
}
