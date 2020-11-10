package com.mchavez.pettabbed.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mchavez.pettabbed.R;

import java.util.ArrayList;
import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetHolder> {

    private List<Mascota> petList;
    Activity activity;
    private Context context;

    public PetAdapter(ArrayList<Mascota> pets, Context context) {
        petList = new ArrayList<>();
        this.petList = pets;
        this.context = context;
        setHasStableIds(true);
    }

    public PetAdapter(ArrayList<Mascota> pets) {
        petList = new ArrayList<>();
        this.petList = pets;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public PetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new PetHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PetHolder holder, int i) {
        PetHolder.init(petList.get(i), context);

    }

    @Override
    public int getItemCount() {
        return petList.size();
    }

    // ### Estos quizás solucionen el problema de el Recycler cambiando las imágenes
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }
    // ###

    public void agregaPets(Mascota mascota) {
        petList.add(mascota);
        notifyDataSetChanged(); // ### Esto puede causar errores
    }
}