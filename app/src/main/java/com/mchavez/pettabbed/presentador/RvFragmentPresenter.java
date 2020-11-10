package com.mchavez.pettabbed.presentador;

import android.content.Context;

import com.mchavez.pettabbed.fragments.IFragment_Rv;
import com.mchavez.pettabbed.adapter.Mascota;
import com.mchavez.pettabbed.datos.PetConstr;

import java.util.ArrayList;

public class RvFragmentPresenter implements IRvFragmentPresenter {

        static IFragment_Rv iFragment_rv;
        private Context context;
        private PetConstr petConstr;
        private ArrayList<Mascota> pets;

        public RvFragmentPresenter(IFragment_Rv iFragment_rv, Context context) {
            RvFragmentPresenter.iFragment_rv = iFragment_rv;
            this.context = context;
            obtenerContactosBaseDatos();
            //obtenerMediosRecientes();
        }

        @Override
        public void obtenerContactosBaseDatos() {

            petConstr = new PetConstr(context);
            pets = petConstr.obtenerDatos();

            mostrarContactosRV();
        }

        @Override
        public void mostrarContactosRV() {
            iFragment_rv.inicializarAdaptadorRV(iFragment_rv.crearAdaptador(pets));
            iFragment_rv.generarLinearLayoutVertical();
        }
    }

