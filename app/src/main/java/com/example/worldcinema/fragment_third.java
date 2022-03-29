package com.example.worldcinema;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.worldcinema.network.Collection;
import com.example.worldcinema.network.adapter.CollectionAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class fragment_third extends Fragment {

    private ImageView imageView;
    private CollectionAdapter collectionAdapter;
    private ArrayList<Collection> collections = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    public fragment_third(){

    }
    public static fragment_third newInstance(String param1, String param2){
        fragment_third fragment_third = new fragment_third();
        return fragment_third;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null){

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        imageView = view.findViewById(R.id.image_add_collection);
        recyclerView = view.findViewById(R.id.recyclerCollections);
        // TODO Нормальный onclick
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CreateCollection.class));
            }
        });
        collections.add(new Collection(R.drawable.main_vector_1_1, "Избранное"));
        collections.add(new Collection(R.drawable.main_vector_2_1, "Когда-нибудь"));
        collections.add(new Collection(R.drawable.collect_1, "В поездку"));

        collectionAdapter = new CollectionAdapter(getContext(), collections);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(collectionAdapter);
        return view;

    }
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_third, null);
//
//        ListView listView = (ListView) v.findViewById(R.id.listview);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            }
//        });
//        ArrayList<HashMap<String, String>> myArrList = new ArrayList<HashMap<String, String>>();
//        HashMap<String, String> map;
//
//        map = new HashMap<String, String>();
//        map.put("Name", "Избранное");
//        myArrList.add(map);
//
//        map=new HashMap<String, String>();
//        map.put("Name","Когда-нибудь");
//        myArrList.add(map);
//
//        map=new HashMap<String, String>();
//        map.put("Name","В поездку");
//        myArrList.add(map);
//
//        SimpleAdapter adapter = new SimpleAdapter(
//                getActivity(),
//                myArrList,
//                R.layout.listview,
//                new String[]{"Name"},
//                new int[]{R.id.listview});
//        listView.setAdapter(adapter);
//        return v;
//    }
}