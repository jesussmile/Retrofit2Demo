package com.example.pannam.retrofit2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pannam.retrofit2demo.controller.RestManager;
import com.example.pannam.retrofit2demo.model.Flower;
import com.example.pannam.retrofit2demo.model.adapter.FlowerAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RestManager mManager;
    private FlowerAdapter mFlowerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//for RecyclerView
        configViews();
//getting retrofit from controller / RestManager
        mManager = new RestManager();
        Call<List<Flower>> listCall = mManager.getFlowerService().getAllFlowers();
        //call enqueue in main thread not execute
        listCall.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Response<List<Flower>> response) {
                //add flowers to adapter
                if (response.isSuccess()) {
                    List<Flower> flowerList = response.body();
                    for (int i = 0; i < flowerList.size(); i++) {
                        Flower flower = flowerList.get(i);
                        mFlowerAdapter.addFlower(flower);
                    }


                } else {
                    int sc = response.code();
                    switch (sc){

                    }
                }


            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        //to cancel from button
       // listCall.cancel();
    }

    private void configViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mFlowerAdapter = new FlowerAdapter();

        mRecyclerView.setAdapter(new FlowerAdapter());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
