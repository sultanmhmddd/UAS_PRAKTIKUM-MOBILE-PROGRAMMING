package com.example.uas_praktikumpemrogramanmobile_sultan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private TextView tvStatus;
    private Button btnContinue;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tvStatus = findViewById(R.id.tvStatus);
        btnContinue = findViewById(R.id.btnContinue);
        db = AppDatabase.getInstance(this);

        checkData();

        btnContinue.setOnClickListener(v -> {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            finish();
        });
    }

    private void checkData() {
        new Thread(() -> {
            int count = db.animalDao().getCount();
            if (count > 0) {
                runOnUiThread(() -> {
                    tvStatus.setText("Data sudah siap!");
                    btnContinue.setVisibility(View.VISIBLE);
                });
            } else {
                fetchFromApi();
            }
        }).start();
    }

    private void fetchFromApi() {
        ApiClient.getApiService().getAnimals().enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    saveToDb(response.body());
                } else {
                    tvStatus.setText("Gagal mengambil data dari server");
                }
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                tvStatus.setText("Error: " + t.getMessage());
            }
        });
    }

    private void saveToDb(List<Animal> animals) {
        new Thread(() -> {
            db.animalDao().insertAll(animals);
            runOnUiThread(() -> {
                tvStatus.setText("Data berhasil diunduh dan disimpan!");
                btnContinue.setVisibility(View.VISIBLE);
            });
        }).start();
    }
}
