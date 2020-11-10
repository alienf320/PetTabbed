package com.mchavez.pettabbed.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.os.Bundle;

import com.mchavez.pettabbed.fragments.IFragment_Rv;
import com.mchavez.pettabbed.adapter.Mascota;
import com.mchavez.pettabbed.adapter.PetAdapter;
import com.mchavez.pettabbed.R;
import com.mchavez.pettabbed.presentador.IRvFragmentPresenter;
import com.mchavez.pettabbed.presentador.RvDetallePetsPresenter;

import java.util.ArrayList;


public class DetallePets extends AppCompatActivity implements IFragment_Rv {

    private RecyclerView rvPets;
    private ArrayList<Mascota> pets;
    private PetAdapter adapter;
    private IRvFragmentPresenter presenter;
    private Context context;
    Toolbar toolbar2;

    public DetallePets() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_pet5);
        context = DetallePets.this;

        establishToolbar();

        rvPets = findViewById(R.id.rvDetallePets);
        presenter = new RvDetallePetsPresenter(this, context);


        /*adapter.agregaPets(new Mascota(R.drawable.pet2, "Ruffo", "9"));
        adapter.agregaPets(new Mascota(R.drawable.pet3, "Lola", "9"));
        adapter.agregaPets(new Mascota(R.drawable.pet4, "Krali", "9"));
        adapter.agregaPets(new Mascota(R.drawable.pet5, "Peli", "9"));
        adapter.agregaPets(new Mascota(R.drawable.pet6, "Truco", "9"));*/
    }

    public void establishToolbar() {
        Toolbar toolbar2 = findViewById(R.id.toolbar_detallePets);
        setSupportActionBar(toolbar2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPets.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void generarGridLayout() {

    }

    @Override
    public PetAdapter crearAdaptador(ArrayList<Mascota> pets) {
        adapter = new PetAdapter(pets);
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(PetAdapter adaptador) {
        rvPets.setAdapter(adapter);
    }
}