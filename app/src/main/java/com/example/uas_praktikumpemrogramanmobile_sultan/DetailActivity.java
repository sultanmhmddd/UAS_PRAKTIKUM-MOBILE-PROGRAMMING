package com.example.uas_praktikumpemrogramanmobile_sultan;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private boolean isFavorite = false;
    private AppDatabase db;
    private Animal animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = AppDatabase.getInstance(this);
        animal = (Animal) getIntent().getSerializableExtra("animal");

        ImageButton btnBack = findViewById(R.id.btnBack);
        ImageButton btnFav = findViewById(R.id.btnFav);
        ImageView imgDetail = findViewById(R.id.imgDetail);
        TextView tvName = findViewById(R.id.tvNameDetail);
        TextView tvLatin = findViewById(R.id.tvLatinDetail);
        TextView tvOrigin = findViewById(R.id.tvOriginDetail);
        TextView tvStatus = findViewById(R.id.tvStatusDetail);
        TextView tvDesc = findViewById(R.id.tvDescriptionDetail);

        btnBack.setOnClickListener(v -> finish());

        if (animal != null) {
            tvName.setText(animal.getNama());
            tvLatin.setText(animal.getNamaLatin());
            tvOrigin.setText("Asal: " + animal.getAsal());
            tvStatus.setText("Status: " + animal.getStatus());
            tvDesc.setText(animal.getDeskripsi());

            if (animal.getStatus().equalsIgnoreCase("Aman")) {
                tvStatus.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
            } else {
                tvStatus.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
            }

            Glide.with(this)
                    .load(animal.getFoto())
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .into(imgDetail);

            checkFavoriteStatus(btnFav);

            btnFav.setOnClickListener(v -> toggleFavorite(btnFav));
        }
    }

    private void checkFavoriteStatus(ImageButton btnFav) {
        new Thread(() -> {
            FavoriteAnimal fav = db.animalDao().getFavoriteById(animal.getId());
            isFavorite = (fav != null);
            runOnUiThread(() -> {
                if (isFavorite) {
                    btnFav.setImageResource(android.R.drawable.btn_star_big_on);
                } else {
                    btnFav.setImageResource(android.R.drawable.btn_star_big_off);
                }
            });
        }).start();
    }

    private void toggleFavorite(ImageButton btnFav) {
        new Thread(() -> {
            if (isFavorite) {
                db.animalDao().deleteFavorite(new FavoriteAnimal(animal));
                isFavorite = false;
                runOnUiThread(() -> {
                    btnFav.setImageResource(android.R.drawable.btn_star_big_off);
                    Toast.makeText(this, "Dihapus dari favorit", Toast.LENGTH_SHORT).show();
                });
            } else {
                db.animalDao().insertFavorite(new FavoriteAnimal(animal));
                isFavorite = true;
                runOnUiThread(() -> {
                    btnFav.setImageResource(android.R.drawable.btn_star_big_on);
                    Toast.makeText(this, "Ditambahkan ke favorit", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
}
