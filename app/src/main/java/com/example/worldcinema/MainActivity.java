package com.example.worldcinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    BadgeDrawable badge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        NavController navController = Navigation.findNavController(this,  R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
//        bottomNavigationView=findViewById(R.id.bottom_nav);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new fragment_first()).commit();
//        badge = bottomNavigationView.getOrCreateBadge(R.id.message);
//        badge.setNumber(1000);
//
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemReselectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item){
//
//            }
//        });
//       bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
//            @Override
//            public boolean onNavigatiomItemSelected(@NonNull MenuItem item) {
//                badge=bottomNavigationView.getBadge(item.getItemId());
//                if (badge !=null){
//                    badge.clearNumber();
//                }
//                Fragment fragment=null;
//
//                switch (item.getItemId()){
//                    case R.id.fragment_first:
//                        fragment=new fragment_first();
//                        break;
//                    case R.id.fragment_second:
//                        fragment=new fragment_second();
//                        break;
//                    case R.id.fragment_third:
//                        fragment=new fragment_third();
//                        break;
//                    case R.id.fragment_firth:
//                        fragment=new fragment_firth();
//                        break;
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,fragment).commit();
//                return true;
//            }
//        });
    }
}