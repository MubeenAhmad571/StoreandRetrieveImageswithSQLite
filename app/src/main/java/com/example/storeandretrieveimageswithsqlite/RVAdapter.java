package com.example.storeandretrieveimageswithsqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVViewHodlerClass> {

ArrayList<ModelClass> ObjectModelClassList;

    public RVAdapter(ArrayList<ModelClass> objectModelClassList) {
        ObjectModelClassList = objectModelClassList;
    }

    @NonNull
    @Override
    public RVViewHodlerClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RVViewHodlerClass(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_row,viewGroup,false));

    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHodlerClass holder, int position) {

        ModelClass objectModelClass=ObjectModelClassList.get(position);
        holder.imageNameTV.setText(objectModelClass.getImageName());
        holder.objectImageView.setImageBitmap(objectModelClass.getImage());


    }

    @Override
    public int getItemCount() {
        return ObjectModelClassList.size();
    }

    public static  class RVViewHodlerClass extends RecyclerView.ViewHolder
    {

        TextView imageNameTV;
        ImageView objectImageView;
        public RVViewHodlerClass(@NonNull View itemView) {
            super(itemView);

            imageNameTV=itemView.findViewById(R.id.sr_imageDetailsTV);
            objectImageView=itemView.findViewById(R.id.sr_imageIV);
        }
    }
}

