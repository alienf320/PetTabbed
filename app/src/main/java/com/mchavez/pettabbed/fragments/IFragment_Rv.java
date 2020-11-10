package com.mchavez.pettabbed.fragments;

import com.mchavez.pettabbed.adapter.Mascota;
import com.mchavez.pettabbed.adapter.PetAdapter;

import java.util.ArrayList;

public interface IFragment_Rv {

    void generarLinearLayoutVertical();

    void generarGridLayout();

    PetAdapter crearAdaptador(ArrayList<Mascota> pets);

    void inicializarAdaptadorRV(PetAdapter adaptador);
}

