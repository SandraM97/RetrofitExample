package com.example.user.films;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("api/films/")
    Call<FilmResponse> getFilms();

    @GET("/api/people/")
    Call<PeopleResponse> getPeople(@Query("page") String pagenumber);

    @GET("/api/planets/")
    Call<PlanetsResponse> getPlanets(@Query("page") String pagenumeber);
}
