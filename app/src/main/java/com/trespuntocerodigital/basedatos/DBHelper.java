package com.trespuntocerodigital.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TresDigital.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabla para equipo
        String createTableEquipo =
                "CREATE TABLE equipo ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "nombre TEXT, "
                        + "apellido TEXT, "
                        + "telefono TEXT, "
                        + "correo TEXT, "
                        + "especialidad TEXT, "
                        + "foto TEXT)";
        db.execSQL(createTableEquipo);

        // Tabla para acerca de
        String createTableAcercaDe =
                "CREATE TABLE acerca_de ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "imagen TEXT, "
                        + "titulo TEXT, "
                        + "descripcion TEXT)";
        db.execSQL(createTableAcercaDe);

        // Tabla para contacto
        String createTableContacto =
                "CREATE TABLE contacto ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "telefono TEXT, "
                        + "correo TEXT, "
                        + "direccion TEXT)";
        db.execSQL(createTableContacto);

        // Tabla para galería
        String createTableGaleria =
                "CREATE TABLE galeria ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "imagen TEXT, "
                        + "video TEXT, "
                        + "descripcion TEXT)";
        db.execSQL(createTableGaleria);

        // Tabla para servicios
        String createTableServicios =
                "CREATE TABLE servicios ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "imagen TEXT, "
                        + "nombre_servicio TEXT, "
                        + "descripcion_servicio TEXT, "
                        + "precio_servicio REAL)";
        db.execSQL(createTableServicios);

        // Tabla para productos
        String createTableProductos =
                "CREATE TABLE productos ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "imagen TEXT, "
                        + "nombre_producto TEXT, "
                        + "descripcion_producto TEXT, "
                        + "precio_producto REAL)";
        db.execSQL(createTableProductos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS equipo");
        db.execSQL("DROP TABLE IF EXISTS acerca_de");
        db.execSQL("DROP TABLE IF EXISTS contacto");
        db.execSQL("DROP TABLE IF EXISTS galeria");
        db.execSQL("DROP TABLE IF EXISTS servicios");
        db.execSQL("DROP TABLE IF EXISTS productos");
        onCreate(db);
    }
}
