package com.example.pannam.retrofit2demo.model.api;

import com.example.pannam.retrofit2demo.model.helper.Constants;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * @author Android Developer
 * @version 1.0.0
 * @since 1/28/2016
 */
public class RestManager {

    private FlowerService mFlowerService;

    public FlowerService getFlowerService() {
        if (mFlowerService == null) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HTTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mFlowerService = retrofit.create(FlowerService.class);
        }
        return mFlowerService;
    }
}
