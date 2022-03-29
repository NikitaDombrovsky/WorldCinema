package com.example.worldcinema.network.adapter;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcinema.R;
import com.example.worldcinema.network.Collection;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.RecyclerHolder> {
    private ArrayList<Collection> collections;
    private LayoutInflater inflater;
    private Context context;

    public CollectionAdapter(@NonNull Context context, ArrayList<Collection> collections) {
        this.context = context;
        this.collections = collections;
        this.inflater = LayoutInflater.from(context);

    }
//    @NonNull
//    @Override
//    public RecyclerHolder onCreateRecyclerHolder(@NonNull RecyclerView parent, int recyclerType){
//
//    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int recyclerType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview, parent, false);
        RecyclerHolder recyclerHolder = new RecyclerHolder(view);
        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        holder.setTextCinema(collections.get(position).getCol_Title());
        holder.iconCollection.setImageResource(collections.get(position).getCol_ItemId());
    }
    @Override
    public int getItemCount() {
        return collections.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder{
        final private ImageView iconCollection;
        final private TextView txtCollection;

        private RecyclerHolder(View view){
            super(view);
            this.iconCollection = (ImageView) view.findViewById(R.id.iconCollection);
            this.txtCollection = (TextView) view.findViewById(R.id.txtCollection);
        }
        public void setTextCinema(String text){
            this.txtCollection.setText(text);
        }
    }
}
