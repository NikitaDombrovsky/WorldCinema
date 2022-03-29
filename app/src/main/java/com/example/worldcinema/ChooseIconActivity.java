package com.example.worldcinema;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.network.adapter.IconAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChooseIconActivity extends AppCompatActivity {
    private ImageView imageView;
    private GridView gridIcons;
    private List<IconItem> iconList = new ArrayList<>();
    private IconAdapter iconAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_icon);
        gridIcons = findViewById(R.id.gridIcons);
        imageView = findViewById(R.id.goBackFromChooseIcons);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iconAdapter = new IconAdapter(this, R.layout.icon_item, iconList);
        setIconList();
        gridIcons.setAdapter(iconAdapter);
    }
    private void setIconList(){
        for (int i=0; i<36; i++){
            iconList.add(new IconItem(R.drawable.collect_1));

        }
        iconAdapter.notifyDataSetChanged();

    }
}
