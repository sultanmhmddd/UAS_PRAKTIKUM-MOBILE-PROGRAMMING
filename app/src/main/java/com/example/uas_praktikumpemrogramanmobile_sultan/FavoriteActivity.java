package com.example.uas_praktikumpemrogramanmobile_sultan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView rvFavorite;
    private AnimalAdapter adapter;
    private List<Animal> favoriteList = new ArrayList<>();
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        rvFavorite = findViewById(R.id.rvFavorite);
        tvEmpty = findViewById(R.id.tvEmpty);
        ImageButton btnBack = findViewById(R.id.btnBackFav);

        btnBack.setOnClickListener(v -> finish());

        rvFavorite.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new AnimalAdapter(favoriteList, animal -> {
            Intent intent = new Intent(FavoriteActivity.this, DetailActivity.class);
            intent.putExtra("animal", animal);
            startActivity(intent);
        });
        rvFavorite.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavorites();
    }

    private void loadFavorites() {
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(this);
            List<FavoriteAnimal> data = db.animalDao().getAllFavorites();
            
            List<Animal> convertedData = new ArrayList<>();
            for (FavoriteAnimal fav : data) {
                Animal a = new Animal();
                a.setId(fav.getId());
                a.setTipe(fav.getTipe());
                a.setNama(fav.getNama());
                a.setNamaLatin(fav.getNamaLatin());
                a.setAsal(fav.getAsal());
                a.setFoto(fav.getFoto());
                a.setStatus(fav.getStatus());
                a.setDeskripsi(fav.getDeskripsi());
                convertedData.add(a);
            }

            runOnUiThread(() -> {
                favoriteList.clear();
                favoriteList.addAll(convertedData);
                adapter.notifyDataSetChanged();
                
                if (favoriteList.isEmpty()) {
                    tvEmpty.setVisibility(View.VISIBLE);
                } else {
                    tvEmpty.setVisibility(View.GONE);
                }
            });
        }).start();
    }
}
