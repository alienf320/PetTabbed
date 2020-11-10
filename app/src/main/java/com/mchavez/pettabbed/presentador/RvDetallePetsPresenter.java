package com.mchavez.pettabbed.presentador;

import android.content.Context;

import com.mchavez.pettabbed.fragments.IFragment_Rv;
import com.mchavez.pettabbed.adapter.Mascota;
import com.mchavez.pettabbed.datos.PetConstr;

import java.util.ArrayList;

public class RvDetallePetsPresenter implements IRvFragmentPresenter {

        static IFragment_Rv iFragment_rv;
        private Context context;
        private PetConstr petConstr;
        private ArrayList<Mascota> pets;

        public RvDetallePetsPresenter(IFragment_Rv iFragment_rv, Context context) {
            RvDetallePetsPresenter.iFragment_rv = iFragment_rv;
            this.context = context;
            obtenerContactosBaseDatos();
            //obtenerMediosRecientes();
        }

        @Override
        public void obtenerContactosBaseDatos() {

            /*
            pets = new ArrayList<>();

            pets.add(new Mascota(R.drawable.pet1, "Choki", 10));
            pets.add(new Mascota(R.drawable.pet2, "Ruffo", 8));
            pets.add(new Mascota(R.drawable.pet3, "Lola", 6));
            pets.add(new Mascota(R.drawable.pet4, "Krali", 5));
            pets.add(new Mascota(R.drawable.pet5, "Peli", 7));
            pets.add(new Mascota(R.drawable.pet6, "Truco", 1));
            pets.add(new Mascota(R.drawable.pet7, "Timmy", 3));
            pets.add(new Mascota(R.drawable.pet8, "Babe", 7));
            pets.add(new Mascota(R.drawable.pet9, "Kapi", 10));
             */

            petConstr = new PetConstr(context);
            pets = petConstr.obtenerDatosFav();

            mostrarContactosRV();
        }

        @Override
        public void mostrarContactosRV() {
            iFragment_rv.inicializarAdaptadorRV(iFragment_rv.crearAdaptador(pets));
            iFragment_rv.generarLinearLayoutVertical();
        }


    }

