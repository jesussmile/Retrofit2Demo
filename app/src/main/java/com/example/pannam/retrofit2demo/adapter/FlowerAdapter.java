package com.example.pannam.retrofit2demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pannam.retrofit2demo.R;
import com.example.pannam.retrofit2demo.model.Flower;
import com.example.pannam.retrofit2demo.model.helper.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Android Developer
 * @version 1.0.0
 * @since 1/28/2016
 */
public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.Holder> {

    private List<Flower> mFlowerList = new ArrayList<>();

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        View view = holder.itemView;
        ImageView photo = (ImageView) view.findViewById(R.id.flowerPhoto);
        TextView name = (TextView) view.findViewById(R.id.flowerName);
        TextView price = (TextView) view.findViewById(R.id.flowerPrice);

        Flower flower = mFlowerList.get(position);
        name.setText(flower.getName());
        price.setText(Double.toString(flower.getPrice()));

        Glide.with(view.getContext()).load(Constants.HTTP.BASE_URL + "/photos/" + flower.getPhoto()).placeholder(R.mipmap.ic_launcher).into(photo);
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }

    /**
     * This method is used to add all the flowers list from the http request callback
     * To get list of the flowers we use Retrofit2
     *
     * @param flowers The list {@link List} of flowers {@link Flower}
     */
    public void addFlowers(List<Flower> flowers) {
        mFlowerList.addAll(flowers);
        notifyDataSetChanged();
    }

    /**
     * This method is used to reset the values of the list in the adapter
     */
    public void reset() {
        mFlowerList.clear();
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Flower flower = mFlowerList.get(getAdapterPosition());
            Toast.makeText(v.getContext(), "You clicked on " + flower.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}
