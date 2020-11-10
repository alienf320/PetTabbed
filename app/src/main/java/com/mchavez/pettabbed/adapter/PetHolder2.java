package com.mchavez.pettabbed.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mchavez.pettabbed.R;

public class PetHolder2 extends RecyclerView.ViewHolder {

    private static ImageView imgPet;

    private static TextView rating;
    private static ImageButton btnHueso2;

    public PetHolder2(@NonNull View itemView) {
        super(itemView);

        imgPet = itemView.findViewById(R.id.imgPet);
        rating = itemView.findViewById(R.id.rating);
        btnHueso2 = itemView.findViewById(R.id.btnHueso2);
    }

    public static void init(Mascota mascota) {
        imgPet.setImageResource((mascota.getFoto()));
        rating.setText(String.valueOf(mascota.getRating()));


    }
}