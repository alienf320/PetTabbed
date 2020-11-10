package com.mchavez.pettabbed.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mchavez.pettabbed.R;

import java.util.ArrayList;
import java.util.List;

public class PetAdapter2 extends RecyclerView.Adapter<PetHolder2> {

    private List<Mascota> petList;

    public PetAdapter2() {
        petList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PetHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view2, parent, false);
        return new PetHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PetHolder2 holder, int i) {
        PetHolder2.init(petList.get(i));
    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    public void agregaPets(Mascota mascota) {
        petList.add(mascota);
        notifyDataSetChanged();
    }
}