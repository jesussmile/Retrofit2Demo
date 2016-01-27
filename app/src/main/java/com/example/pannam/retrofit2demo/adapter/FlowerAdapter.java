package com.example.pannam.retrofit2demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pannam.retrofit2demo.R;

/**
 * Created by pannam on 1/27/2016.
 */

//1st
//for the recyclerview & pass a Holder
public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.Holder> {

//3rd hit red light and create override
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
//inflate row_item
       View  row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);

return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    //2nd
    //Pass holder class
    public class Holder extends RecyclerView.ViewHolder{
        public Holder(View itemView){
            super(itemView);
        }
    }


}
