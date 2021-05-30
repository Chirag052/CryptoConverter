package com.techone.cryptoconverter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AssetsApi {

    @GET("{country}/{cryptocur}?apikey=72455FBD-DE47-4E6D-805A-8D5A10201D5F")

    Call<ConverterModel> getmodels(@Path("country") String country, @Path("cryptocur") String cryptocur);
}
