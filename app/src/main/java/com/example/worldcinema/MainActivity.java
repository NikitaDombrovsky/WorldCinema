//package com.example.worldcinema;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.NavigationUI;
//
//import android.os.Bundle;
//import android.view.MenuItem;
//
//import com.google.android.material.badge.BadgeDrawable;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.navigation.NavigationBarView;
//import com.example.worldcinema.R;
//import com.google.android.material.bottomnavigation.BottomNavigationView;


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
// FortniteBalls@gmail.com
// forntite
    public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    BadgeDrawable badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}
//public class MainActivity extends AppCompatActivity {
//    private  final Fragment firstF = new fragment_first();
//    private  final Fragment secondF = new fragment_second();
//    private  final Fragment thirdF = new fragment_third();
//    private  final Fragment firthF = new fragment_firth();
//    private Fragment active = firstF;
//
//
//    private FragmentManager fragmentManager;
//    BottomNavigationView bottomNavigationView;
//    BadgeDrawable badge;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().add(R.id.main_container, firstF, "home").commit();
//        fragmentManager.beginTransaction().add(R.id.main_container, secondF, "home").hide(secondF).commit();
//        fragmentManager.beginTransaction().add(R.id.main_container, thirdF, "home").hide(thirdF).commit();
//        fragmentManager.beginTransaction().add(R.id.main_container, firthF, "home").hide(firthF).commit();
//
////        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
//
////        NavController navController = Navigation.findNavController(this,  R.id.fragmentContainerView);
////        NavigationUI.setupWithNavController(bottomNavigationView, navController);
//
//        bottomNavigationView = findViewById(R.id.bottom_nav);
////        bottomNavigationView.setOnItemReselectedListener(onNavigationItemSelectedListener);
//    }
//    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()){
//                case R.id.fragment_first:
//                    fragmentManager.beginTransaction().hide(active).show(firstF).commit();
//                    active = firstF;
//                    return true;
//                case R.id.fragment_second:
//                    fragmentManager.beginTransaction().hide(active).show(secondF).commit();
//                    active = secondF;
//                    return true;
//                case R.id.fragment_third:
//                    fragmentManager.beginTransaction().hide(active).show(thirdF).commit();
//                    active = thirdF;
//                    return true;
//                case R.id.fragment_firth:
//                    fragmentManager.beginTransaction().hide(active).show(firthF).commit();
//                    active = firthF;
//                    return true;
//            }
//            return false;
//        }
//    };
//}