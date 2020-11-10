package com.mchavez.pettabbed.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mchavez.pettabbed.adapter.Mascota;

import java.util.ArrayList;

public class BaseDatosFav extends SQLiteOpenHelper {

    private Context context;

    public BaseDatosFav(Context context) {
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
                ConstantesBaseDatos.TABLE_FAV_PETS_NOMBRE + " TEXT, " +
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

    public ArrayList<Mascota> obtenerTodosLosFav() {
        ArrayList<Mascota> pets = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_FAV_PETS +
                    " ORDER BY " + ConstantesBaseDatos.TABLE_FAV_RECIENTE +
                    " DESC LIMIT " + 5;
        SQLiteDatabase db = getWritableDatabase(); // Le saqué this.
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota petActual = new Mascota();
            petActual.setNombre(registros.getString(1));
            petActual.setFoto(registros.getInt(2));
            petActual.setReciente(registros.getInt(3));

            /*
            String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_PET_NUMERO_LIKES+") as likes " +
                    " FROM " + ConstantesBaseDatos.TABLE_LIKES_PET +
                    " WHERE " + ConstantesBaseDatos.TABLE_LIKES_PET_ID_PETS + "=" + PetActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                contactoActual.setRating(registrosLikes.getInt(0));
            }else {
                contactoActual.setRating(0);
            }
            */

            pets.add(petActual);
        }
        db.close();

        return pets;
    }


    public boolean estaVacia2 () {

        String query = "SELECT DISTINCT " + ConstantesBaseDatos.TABLE_FAV_PETS_NOMBRE + " FROM " + ConstantesBaseDatos.TABLE_FAV_PETS;
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
