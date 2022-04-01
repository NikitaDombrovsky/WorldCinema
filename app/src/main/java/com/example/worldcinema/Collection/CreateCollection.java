package com.example.worldcinema.Collection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.worldcinema.Main.MainActivity;
import com.example.worldcinema.R;

public class CreateCollection extends AppCompatActivity {
    private Button btnChooseIcon;
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
        // Открытие меню и передача картинки (В теории работает)
        btnSaveCollection.setOnClickListener(view -> startActivity(new Intent(CreateCollection.this, MainActivity.class)));
        bundle = getIntent().getExtras();
        if (bundle != null){
            iconChoose.setImageResource(bundle.getInt("icon"));
        }
        btnChooseIcon.setOnClickListener(view -> startActivity(new Intent(CreateCollection.this, ChooseIconActivity.class)));
    }

    private void goBack(View view){
        finish();
    }
}
