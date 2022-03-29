package com.example.worldcinema;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class CreateCollection extends AppCompatActivity {
    private MaterialButton btnChooseIcon;
    private Bundle bundle;
    private ImageView iconChoose;
    private Button btnSaveCollection;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_collection);

        btnChooseIcon = findViewById(R.id.btnChooseIcon);
        iconChoose = findViewById(R.id.iconChoose);
        btnSaveCollection = findViewById(R.id.btnSaveCollection);
        btnSaveCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateCollection.this, MainActivity.class));
            }
        });
        bundle = getIntent().getExtras();
        if (bundle != null){
            iconChoose.setImageResource(bundle.getInt("icon"));
        }
        btnChooseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(CreateCollection.this, ChooseIconActivity.class));
            }
        });
    }

    private void goBack(View view){
        finish();
    }
}
