package com.example.uas_praktikumpemrogramanmobile_sultan;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView rvSearch;
    private AnimalAdapter adapter;
    private List<Animal> searchResult = new ArrayList<>();
    private EditText etSearch;
    private TextView tvNoResult;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        db = AppDatabase.getInstance(this);
        etSearch = findViewById(R.id.etSearch);
        rvSearch = findViewById(R.id.rvSearch);
        tvNoResult = findViewById(R.id.tvNoResult);
        ImageButton btnBack = findViewById(R.id.btnBackSearch);

        btnBack.setOnClickListener(v -> finish());

        rvSearch.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new AnimalAdapter(searchResult, animal -> {
            Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
            intent.putExtra("animal", animal);
            startActivity(intent);
        });
        rvSearch.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                performSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void performSearch(String query) {
        if (query.isEmpty()) {
            searchResult.clear();
            adapter.notifyDataSetChanged();
            tvNoResult.setVisibility(View.GONE);
            return;
        }

        new Thread(() -> {
            List<Animal> data = db.animalDao().searchByName(query);
            runOnUiThread(() -> {
                searchResult.clear();
                searchResult.addAll(data);
                adapter.notifyDataSetChanged();

                if (searchResult.isEmpty()) {
                    tvNoResult.setVisibility(View.VISIBLE);
                } else {
                    tvNoResult.setVisibility(View.GONE);
                }
            });
        }).start();
    }
}
