package com.example.pannam.retrofit2demo.model.api;

import com.example.pannam.retrofit2demo.model.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Android Developer
 * @version 1.0.0
 * @since 1/28/2016
 */
public interface FlowerService {

    @GET("/feeds/flowers.json")
    Call<List<Flower>> getAllFlowers();
}