package com.example.worldcinema.network.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.worldcinema.Collection.CreateCollection;
import com.example.worldcinema.Collection.IconItem;
import com.example.worldcinema.R;

import java.util.ArrayList;
import java.util.List;

public class IconAdapter extends ArrayAdapter<IconItem> {
    private List<IconItem> iconsList = new ArrayList<>();
    private int customLayoutId;
    private Context context;

    private LinearLayout linearLayout;
    public IconAdapter(@NonNull Context context, int resource, @NonNull List<IconItem> _iconsList){
        super(context, resource, _iconsList);
        this.context = context;
        iconsList = _iconsList;
        customLayoutId = resource;
    }
    @Override
    public int getCount(){
        return super.getCount();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(customLayoutId, null);
        }

        ImageView imageView = v.findViewById(R.id.img_icon);
        IconItem iconItem  =  iconsList.get(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CreateCollection.class);
                intent.putExtra("icon", iconItem.getIcon_id());
                context.startActivity(intent);
            }
        });
        imageView.setImageResource(iconItem.getIcon_id());

        return v;
    }
}
