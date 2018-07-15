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
import com.example.user.films.People;
import com.example.user.films.response.PeopleResponse;
import com.example.user.films.R;
import com.example.user.films.adapters.PeopleAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PeopleAdapter peopleAdapter;
    ProgressBar progressBar;
    // Index from which pagination should start (0 is 1st page in our case)
    private static final int PAGE_START = 1;
    // Indicates if footer ProgressBar is shown (i.e. next page is loading)
    private boolean isLoading = false;
    // If current page is the last page (Pagination will stop after this page load)
    private boolean isLastPage = false;
    // total no. of pages to load. Initial load is page 0, after which 2 more pages will load.
    private int TOTAL_PAGES = 9;
    // indicates the current page which Pagination is fetching.
    private int currentPage = PAGE_START;
    LinearLayoutManager linearLayoutManager;
    List<People>people;
    private static int pageIndex;
    Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.progressBar);

        peopleAdapter=new PeopleAdapter(this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(peopleAdapter);

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

       new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {

                loadFirstPage();

            }

        }, 1000);
        api = ApiClient.getClient().create(Api.class);
        loadFirstPage();
    }


    private Call<PeopleResponse> peopleResponseCall() {
        return api.getPeople(currentPage);
    }

        /*for (int i = 1; i < 10; i++) {
               pageIndex=i;
            Call<PeopleResponse> call=api.getPeople(pageIndex);
            call.enqueue(new Callback<PeopleResponse>() {
                @Override
                public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                    try {
                        people=response.body().getResults();

                        peopleAdapter.addItem(people);
                    }
                    catch (NullPointerException e)
                    {
                        e.getMessage();
                    }
                }

                @Override
                public void onFailure(Call<PeopleResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
            recyclerView.setAdapter(peopleAdapter);
        }

    }*/
    private List<People> fetchResults(Response<PeopleResponse> response){
        PeopleResponse peopleResponse=response.body();
        return peopleResponse.getResults();

    }

    private void loadFirstPage(){
        peopleResponseCall().enqueue(new Callback<PeopleResponse>() {
            @Override
            public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                try {
                    List<People> people = fetchResults(response);
                    progressBar.setVisibility(View.GONE);
                    peopleAdapter.addAll(people);
                    if (currentPage <= TOTAL_PAGES) peopleAdapter.addLoadingFooter();
                    else isLastPage = true;
                }
                catch (Exception e)
                {
                    e.getMessage();
                }

            }

            @Override
            public void onFailure(Call<PeopleResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void loadNextPage() {
        peopleResponseCall().enqueue(new Callback<PeopleResponse>() {
            @Override
            public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                peopleAdapter.removeLoadingFooter();
                isLoading = false;

                List<People> peopleList =fetchResults(response);
                peopleAdapter.addAll(peopleList);

                if (currentPage != TOTAL_PAGES) peopleAdapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<PeopleResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}
