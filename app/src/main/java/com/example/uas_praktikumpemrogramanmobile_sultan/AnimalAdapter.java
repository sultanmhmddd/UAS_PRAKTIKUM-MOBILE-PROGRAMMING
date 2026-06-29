package com.example.uas_praktikumpemrogramanmobile_sultan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {
    private List<Animal> animalList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Animal animal);
    }

    public AnimalAdapter(List<Animal> animalList, OnItemClickListener listener) {
        this.animalList = animalList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Animal animal = animalList.get(position);
        holder.tvName.setText(animal.getNama());
        holder.tvLatinName.setText(animal.getNamaLatin());
        holder.tvOrigin.setText(animal.getAsal());

        Glide.with(holder.itemView.getContext())
                .load(animal.getFoto())
                .placeholder(android.R.drawable.ic_menu_report_image)
                .into(holder.imgAnimal);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(animal));
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAnimal;
        TextView tvName, tvLatinName, tvOrigin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAnimal = itemView.findViewById(R.id.imgAnimal);
            tvName = itemView.findViewById(R.id.tvName);
            tvLatinName = itemView.findViewById(R.id.tvLatinName);
            tvOrigin = itemView.findViewById(R.id.tvOrigin);
        }
    }
}
