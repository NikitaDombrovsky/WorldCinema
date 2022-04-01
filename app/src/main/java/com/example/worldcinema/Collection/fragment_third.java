package com.example.worldcinema.Collection;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.worldcinema.R;
import com.example.worldcinema.network.Collection;
import com.example.worldcinema.network.adapter.CollectionAdapter;

import java.util.ArrayList;



public class fragment_third extends Fragment {

    private ImageView imageView;
    private CollectionAdapter collectionAdapter;
    private ArrayList<Collection> collections = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        imageView = view.findViewById(R.id.image_add_collection);
        recyclerView = view.findViewById(R.id.recyclerCollections);
        // Заполнение RecyplerView
        imageView.setOnClickListener(view1 -> startActivity(new Intent(getContext(), CreateCollection.class)));
        collections.add(new Collection(R.drawable.main_vector_1_1, "Избранное"));
        collections.add(new Collection(R.drawable.main_vector_2_1, "Когда-нибудь"));
        collections.add(new Collection(R.drawable.collect_1, "В поездку"));

        collectionAdapter = new CollectionAdapter(getContext(), collections);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(collectionAdapter);
        return view;

    }

}