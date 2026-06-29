package com.example.uas_praktikumpemrogramanmobile_sultan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class TumbuhanFragment extends Fragment {

    private RecyclerView rvList;
    private AnimalAdapter adapter;
    private List<Animal> animalList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        rvList = view.findViewById(R.id.rvList);
        rvList.setLayoutManager(new GridLayoutManager(getContext(), 2));

        adapter = new AnimalAdapter(animalList, animal -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("animal", animal);
            startActivity(intent);
        });
        rvList.setAdapter(adapter);

        loadData();
        return view;
    }

    private void loadData() {
        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getContext());
            List<Animal> data = db.animalDao().getByType("Tumbuhan");
            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    animalList.clear();
                    animalList.addAll(data);
                    adapter.notifyDataSetChanged();
                });
            }
        }).start();
    }
}
