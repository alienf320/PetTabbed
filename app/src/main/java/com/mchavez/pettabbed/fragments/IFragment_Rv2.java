package com.mchavez.pettabbed.fragments;

import com.mchavez.pettabbed.adapter.Mascota;
import com.mchavez.pettabbed.adapter.PetAdapter2;

import java.util.ArrayList;

public interface IFragment_Rv2 {
    void generarLinearLayoutVertical();
    void generarGridLayout();

    PetAdapter2 crearAdaptador(ArrayList<Mascota> pets);

    void inicializarAdaptadorRV(PetAdapter2 adaptador);
}
