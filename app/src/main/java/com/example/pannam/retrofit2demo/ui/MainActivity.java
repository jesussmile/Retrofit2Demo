package com.example.pannam.retrofit2demo.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pannam.retrofit2demo.R;
import com.example.pannam.retrofit2demo.adapter.FlowerAdapter;
import com.example.pannam.retrofit2demo.model.Flower;
import com.example.pannam.retrofit2demo.model.api.FlowerService;
import com.example.pannam.retrofit2demo.model.api.RestManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<List<Flower>> {
    private RecyclerView mRecyclerView;
    private RestManager mManager;
    private FlowerAdapter mFlowerAdapter;
    private ProgressDialog mProgressDialog;
    private Button mRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        configViews();

        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load();
            }
        });

        //Create an instance on {@link RestManager}
        mManager = new RestManager();
        load();
        //to cancel from button
        // listCall.cancel();
    }

    private void load() {
        FlowerService service = mManager.getFlowerService();
        Call<List<Flower>> listCall = service.getAllFlowers();

        mFlowerAdapter.reset();
        showProgressDialog();

        //call enqueue in main thread not execute
        listCall.enqueue(this);
    }

    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading content..");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();
    }

    /**
     * This method is necessary to configure the views
     */
    private void configViews() {
        mRefresh = (Button) findViewById(R.id.button);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        mFlowerAdapter = new FlowerAdapter();

        mRecyclerView.setAdapter(mFlowerAdapter);
    }

    @Override
    public void onResponse(Response<List<Flower>> response) {
        hideProgress();
        if (response.isSuccess()) {
            mFlowerAdapter.addFlowers(response.body());
        }
    }

    @Override
    public void onFailure(Throwable t) {
        hideProgress();
        Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void hideProgress() {
        mProgressDialog.hide();
    }

    @Override
    protected void onDestroy() {
        mProgressDialog = null;
        super.onDestroy();
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
