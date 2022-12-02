package com.marouene.meteoapp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Watherapi {

    @GET("weather/{ville}")
public Call<Meteo> getWeather(@Path("ville") String ville);

}
