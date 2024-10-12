package com.trespuntocerodigital.contenido;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trespuntocerodigital.basedatos.DBHelper;

public class Servicios extends Fragment {

    private String sectionName;
    private Context contexto;
    private LinearLayout diseno;
    private ScrollView scrollView;

    public Servicios(Context contexto, String sectionName) {
        this.contexto = contexto;
        this.sectionName = sectionName;
    }

    private void consultarServicios() {
        DBHelper dbHelper = new DBHelper(contexto);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("servicios", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String imagenPath = cursor.getString(cursor.getColumnIndex("imagen"));
            String nombreServicio = cursor.getString(cursor.getColumnIndex("nombre_servicio"));
            String descripcionServicio =
                    cursor.getString(cursor.getColumnIndex("descripcion_servicio"));
            double precioServicio = cursor.getDouble(cursor.getColumnIndex("precio_servicio"));

            Bitmap imagen = null;
            if (imagenPath != null) {
                imagen = BitmapFactory.decodeFile(imagenPath);
            }

            crearUIServicio(imagen, nombreServicio, descripcionServicio, precioServicio);
        }

        cursor.close();
        db.close();
    }

    private void crearUIServicio(Bitmap imagen, String nombre, String descripcion, double precio) {
        LinearLayout servicioLayout = new LinearLayout(contexto);
        servicioLayout.setOrientation(LinearLayout.HORIZONTAL);
        servicioLayout.setPadding(16, 16, 16, 16);
        servicioLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        servicioLayout.setElevation(4);

        // Imagen del servicio
        ImageView imagenView = new ImageView(contexto);
        imagenView.setImageBitmap(imagen);

        LinearLayout.LayoutParams imagenParams = new LinearLayout.LayoutParams(150, 150);
        imagenParams.setMargins(0, 0, 16, 0);
        imagenView.setLayoutParams(imagenParams);
        imagenView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Layout para el texto
        LinearLayout textoLayout = new LinearLayout(contexto);
        textoLayout.setOrientation(LinearLayout.VERTICAL);

        // Nombre del servicio
        TextView nombreTextView = new TextView(contexto);
        nombreTextView.setText(nombre);
        nombreTextView.setTextSize(18);
        nombreTextView.setTextColor(Color.BLACK);

        // Descripción del servicio
        TextView descripcionTextView = new TextView(contexto);
        descripcionTextView.setText(descripcion);
        descripcionTextView.setTextSize(16);
        descripcionTextView.setTextColor(Color.GRAY);

        // Precio del servicio
        TextView precioTextView = new TextView(contexto);
        precioTextView.setText("Precio: $" + precio);
        precioTextView.setTextSize(14);
        precioTextView.setTextColor(Color.DKGRAY);

        // Añadir el texto al layout de texto
        textoLayout.addView(nombreTextView);
        textoLayout.addView(descripcionTextView);
        textoLayout.addView(precioTextView);

        // Añadir la imagen y el texto al layout principal
        servicioLayout.addView(imagenView);
        servicioLayout.addView(textoLayout);

        // Añadir el layout del servicio al diseño principal
        diseno.addView(servicioLayout);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        scrollView = new ScrollView(contexto);
        scrollView.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        diseno = new LinearLayout(contexto);
        diseno.setOrientation(LinearLayout.VERTICAL);
        diseno.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        diseno.setPadding(16, 16, 16, 16);

        consultarServicios();

        scrollView.addView(diseno);
        return scrollView;
    }
}
