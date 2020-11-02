package com.mchavez.pettabbed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;


public class DetallePets extends AppCompatActivity {

    private RecyclerView rvPets;
    private PetAdapter adapter;
    Toolbar toolbar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_pet5);

        establishToolbar();

        rvPets = findViewById(R.id.rvPets);
        adapter = new PetAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvPets.setLayoutManager(linearLayoutManager);
        rvPets.setAdapter(adapter);

        adapter.agregaPets(new Mascota(R.drawable.pet2, "Ruffo", "9"));
        adapter.agregaPets(new Mascota(R.drawable.pet3, "Lola", "9"));
        adapter.agregaPets(new Mascota(R.drawable.pet4, "Krali", "9"));
        adapter.agregaPets(new Mascota(R.drawable.pet5, "Peli", "9"));
        adapter.agregaPets(new Mascota(R.drawable.pet6, "Truco", "9"));
    }

    public void establishToolbar() {
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar_detallePets);
        setSupportActionBar(toolbar2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }



}