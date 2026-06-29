package com.example.uas_praktikumpemrogramanmobile_sultan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        ImageView btnFavorite = findViewById(R.id.btnFavorite);
        ImageView btnSearch = findViewById(R.id.btnSearch);

        btnFavorite.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, FavoriteActivity.class));
        });

        // Nanti untuk Search kita buatkan Activity-nya
        btnSearch.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, SearchActivity.class));
        });
        
        // Default fragment
        loadFragment(new HewanFragment());

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            if (item.getItemId() == R.id.nav_hewan) {
                fragment = new HewanFragment();
            } else if (item.getItemId() == R.id.nav_tumbuhan) {
                fragment = new TumbuhanFragment();
            } else if (item.getItemId() == R.id.nav_profil) {
                fragment = new ProfileFragment();
            }
            return loadFragment(fragment);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
