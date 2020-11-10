package com.mchavez.pettabbed.datos;

import android.content.ContentValues;
import android.content.Context;

import com.mchavez.pettabbed.adapter.Mascota;
import com.mchavez.pettabbed.adapter.PetAdapter;
import com.mchavez.pettabbed.R;

import java.util.ArrayList;

public class PetConstr {

    private static final int LIKE = 1;
    private Context context;
    private PetAdapter adapter;

    public PetConstr(Context context) {
        this.context = context;
    }


    public ArrayList<Mascota> obtenerDatos(){


        BaseDatos db = new BaseDatos(context);
        if (db.estaVacia()) {
            insertarContactos(db);
        }
        return db.obtenerTodosLosContactos();
    }


    public void insertarContactos(BaseDatos db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Choki");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.pet1);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Ruffo");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.pet2);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Lola");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.pet3);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Krali");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.pet4);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Peli");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.pet5);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Truco");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.pet6);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Timmy");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.pet7);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Babe");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.pet8);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_NOMBRE, "Kape");
        contentValues.put(ConstantesBaseDatos.TABLE_PETS_FOTO, R.drawable.pet9);

        db.insertarContacto(contentValues);

    }


    public void darLikePet(Mascota pet){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_PET_ID_PETS, pet.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES, LIKE);
        db.insertarLikeContacto(contentValues);

        contentValues = new ContentValues();
        String imagen = "pet" + pet.getId();
        int resID = context.getResources().getIdentifier(imagen, "drawable", context.getPackageName());
        contentValues.put(ConstantesBaseDatos.TABLE_FAV_PETS_NOMBRE, pet.getNombre());
        contentValues.put(ConstantesBaseDatos.TABLE_FAV_PETS_FOTO, resID);
        contentValues.put(ConstantesBaseDatos.TABLE_FAV_RECIENTE, pet.getReciente());
        db.insertarFavoritePet(contentValues, pet);
    }



    public int obtenerLikesPet(Mascota pet){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesContacto(pet);
    }


    public ArrayList<Mascota> obtenerDatosFav(){

        BaseDatosFav db = new BaseDatosFav(context);

        /*
        if (db.estaVacia2()) {
            // ### No he preparado nada en caso de que la DB esté vacía
            //insertarContactos(db);
        }

        */
        return db.obtenerTodosLosFav();
    }


}

