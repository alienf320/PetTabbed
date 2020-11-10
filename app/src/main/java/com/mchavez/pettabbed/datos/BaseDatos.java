package com.mchavez.pettabbed.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mchavez.pettabbed.adapter.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCrearTablaContacto = "CREATE TABLE IF NOT EXISTS " + ConstantesBaseDatos.TABLE_PETS + "(" +
                ConstantesBaseDatos.TABLE_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_PETS_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_PETS_FOTO + " INTEGER" +
                ")";

        String queryCrearTablaLikesContacto = "CREATE TABLE IF NOT EXISTS " + ConstantesBaseDatos.TABLE_LIKES_PET + "(" +
                ConstantesBaseDatos.TABLE_LIKES_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES + " INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_PET_ID_PETS + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_PET_ID_PETS + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_PETS + "(" + ConstantesBaseDatos.TABLE_PETS_ID + ")" +
                ")";

        String queryCrearTablaFavoritos = "CREATE TABLE " + ConstantesBaseDatos.TABLE_FAV_PETS + "(" +
                ConstantesBaseDatos.TABLE_FAV_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_FAV_PETS_NOMBRE + " TEXT UNIQUE, " +
                ConstantesBaseDatos.TABLE_FAV_PETS_FOTO + " INTEGER, " +
                ConstantesBaseDatos.TABLE_FAV_RECIENTE + " INTEGER" +
                ")";

        db.execSQL(queryCrearTablaContacto);
        db.execSQL(queryCrearTablaLikesContacto);
        db.execSQL(queryCrearTablaFavoritos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_PETS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_LIKES_PET);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_FAV_PETS);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodosLosContactos() {
        ArrayList<Mascota> pets = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_PETS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota contactoActual = new Mascota();
            contactoActual.setId(registros.getInt(0));
            contactoActual.setNombre(registros.getString(1));
            contactoActual.setFoto(registros.getInt(2));

            String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES+") as likes " +
                    " FROM " + ConstantesBaseDatos.TABLE_LIKES_PET +
                    " WHERE " + ConstantesBaseDatos.TABLE_LIKES_PET_ID_PETS + "=" + contactoActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                contactoActual.setRating(registrosLikes.getInt(0));
            }else {
                contactoActual.setRating(0);
            }

            pets.add(contactoActual);
        }
        db.close();

        return pets;
    }


    public void insertarContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_PETS,null, contentValues);
        db.close();
    }

    public void insertarLikeContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_PET, null, contentValues);
        db.close();
    }


    // Añade la mascota a la DB de favoritos
    public void insertarFavoritePet(ContentValues contentValues, Mascota pet){
        SQLiteDatabase db = this.getWritableDatabase();

        int abc = 0; // Variable que será usada para determinar cuál fue el último añadido

        // Busca el máximo valor en RECIENTE y se lo asigna a abc
        String maximo = "SELECT max ("+ ConstantesBaseDatos.TABLE_FAV_RECIENTE + ")" +
                " FROM " + ConstantesBaseDatos.TABLE_FAV_PETS;

        Cursor cursor = db.rawQuery(maximo, null);

        if (cursor.moveToNext()) {
            abc = cursor.getInt(0);
        }

        // Busca el valor de RECIENTE de la Mascota que acaba de recibir el like
        String queryLikes = "SELECT ("+ ConstantesBaseDatos.TABLE_FAV_RECIENTE + ")" +
                " FROM " + ConstantesBaseDatos.TABLE_FAV_PETS +
                " WHERE " + ConstantesBaseDatos.TABLE_FAV_PETS_NOMBRE + "=?";

        Cursor petFavorita = db.rawQuery(queryLikes, new String[]{pet.getNombre()});

        // Si la mascota no está en la DB, la ingresa sino le suma 1 a su RECIENTE
        if (petFavorita.moveToFirst()){

            String queryActualizar = "UPDATE " + ConstantesBaseDatos.TABLE_FAV_PETS +
                    " SET " + ConstantesBaseDatos.TABLE_FAV_RECIENTE + "=" + (abc + 1) +
                    " WHERE " + ConstantesBaseDatos.TABLE_FAV_PETS_NOMBRE + "='" + pet.getNombre() + "'";
            db.execSQL(queryActualizar);

        } else {
            db.insert(ConstantesBaseDatos.TABLE_FAV_PETS, null, contentValues);

        }
        db.close();
    }


    public int obtenerLikesContacto(Mascota pet){
        int likes = 0;

        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES+")" +
                " FROM " + ConstantesBaseDatos.TABLE_LIKES_PET +
                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_PET_ID_PETS + "="+pet.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }

    public boolean estaVacia () {

        String query = "SELECT DISTINCT " + ConstantesBaseDatos.TABLE_PETS_ID + " FROM " + ConstantesBaseDatos.TABLE_PETS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return false;
            }
            cursor.close();
        }
        return true;
    }
}

