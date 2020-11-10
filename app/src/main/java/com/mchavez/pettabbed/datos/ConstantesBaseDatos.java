package com.mchavez.pettabbed.datos;

public final class ConstantesBaseDatos {

    public static final String DATABASE_NAME = "Pets";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_PETS            = "pet";
    public static final String TABLE_PETS_ID         = "id";
    public static final String TABLE_PETS_NOMBRE     = "nombre";
    public static final String TABLE_PETS_FOTO       = "foto";

    public static final String TABLE_LIKES_PET              = "contacto_likes";
    public static final String TABLE_LIKES_PET_ID           = "id";
    public static final String TABLE_LIKES_PET_ID_PETS      = "id_pets";
    public static final String TABLE_LIKES_PET_NUMERO_LIKES = "numero_likes";

    public static final String TABLE_FAV_PETS            = "favorite_pets";
    public static final String TABLE_FAV_PETS_ID         = "id";
    public static final String TABLE_FAV_PETS_NOMBRE     = "nombre_favorito";
    public static final String TABLE_FAV_PETS_FOTO       = "foto_favorito";
    public static final String TABLE_FAV_RECIENTE       = "time_like";

}