package com.example.user.films;

import com.example.user.films.response.FilmResponse;
import com.example.user.films.response.PeopleResponse;
import com.example.user.films.response.PlanetsResponse;
import com.example.user.films.response.SpeciesResponse;
import com.example.user.films.response.StartshipsResponse;
import com.example.user.films.response.VehiclesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("api/films/")
    Call<FilmResponse> getFilms();

    @GET("api/people/")
    Call<PeopleResponse> getPeople(@Query("page") Integer pageIndex);

    @GET("/api/planets/")
    Call<PlanetsResponse> getPlanets(@Query("page") String api_key);

    @GET("/api/species/")
    Call<SpeciesResponse> getSpecies(@Query("page") String api_key);

    @GET("/api/vehicles/")
    Call<VehiclesResponse> getVehicles(@Query("page") String api_key);

    @GET("/api/starships/")
    Call<StartshipsResponse> getStarships(@Query("page") String api_key);

}
