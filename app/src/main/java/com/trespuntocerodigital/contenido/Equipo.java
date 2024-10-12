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

public class Equipo extends Fragment {

    private String sectionName;
    private Context contexto;
    private LinearLayout diseno;
    private ScrollView scrollView;

    public Equipo(Context contexto, String sectionName) {
        this.contexto = contexto;
        this.sectionName = sectionName;
    }

    private void consultarEquipo() {
        DBHelper dbHelper = new DBHelper(contexto);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("equipo", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            String apellido = cursor.getString(cursor.getColumnIndex("apellido"));
            String telefono = cursor.getString(cursor.getColumnIndex("telefono"));
            String especialidad = cursor.getString(cursor.getColumnIndex("especialidad"));
            String foto = cursor.getString(cursor.getColumnIndex("foto"));

            Bitmap bitmap = null;
            // Crear un ImageView para la imagen
            if (foto != null && cursor.getCount() > 0) {

                bitmap = BitmapFactory.decodeFile(foto);
            }

            crearUIEquipo(nombre, apellido, telefono, especialidad, bitmap);
        }

        cursor.close();
        db.close();
    }

    private void crearUIEquipo(
            String nombre, String apellido, String telefono, String especialidad, Bitmap foto) {
        LinearLayout equipoLayout = new LinearLayout(contexto);
        equipoLayout.setOrientation(LinearLayout.HORIZONTAL);
        equipoLayout.setPadding(16, 16, 16, 16);
        equipoLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        equipoLayout.setElevation(4);

        // Imagen del miembro del equipo
        ImageView fotoImageView = new ImageView(contexto);
        fotoImageView.setImageBitmap(foto);

        LinearLayout.LayoutParams fotoParams = new LinearLayout.LayoutParams(150, 150);
        fotoParams.setMargins(0, 0, 16, 0);
        fotoImageView.setLayoutParams(fotoParams);
        fotoImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Layout para el texto
        LinearLayout textoLayout = new LinearLayout(contexto);
        textoLayout.setOrientation(LinearLayout.VERTICAL);

        // Nombre del miembro
        TextView nombreTextView = new TextView(contexto);
        nombreTextView.setText(nombre + " " + apellido);
        nombreTextView.setTextSize(18);
        nombreTextView.setTextColor(Color.BLACK);

        // Especialidad del miembro
        TextView especialidadTextView = new TextView(contexto);
        especialidadTextView.setText(especialidad);
        especialidadTextView.setTextSize(16);
        especialidadTextView.setTextColor(Color.GRAY);

        // Teléfono del miembro
        TextView telefonoTextView = new TextView(contexto);
        telefonoTextView.setText("Tel: " + telefono);
        telefonoTextView.setTextSize(14);
        telefonoTextView.setTextColor(Color.DKGRAY);

        // Añadir el texto al layout de texto
        textoLayout.addView(nombreTextView);
        textoLayout.addView(especialidadTextView);
        textoLayout.addView(telefonoTextView);

        // Añadir la imagen y el texto al layout principal
        equipoLayout.addView(fotoImageView);
        equipoLayout.addView(textoLayout);

        // Añadir el layout del equipo al diseño principal
        diseno.addView(equipoLayout);
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
        diseno.setBackgroundColor(Color.parseColor("#F0F0F0")); // Fondo claro para el fragmento

        // Crear y añadir el título estilizado para el fragmento
        TextView tituloEquipo = new TextView(contexto);
        tituloEquipo.setText("Equipo de 3.0 Digital");
        tituloEquipo.setTextSize(24);
        tituloEquipo.setTextColor(Color.parseColor("#003366")); // Azul oscuro para profesionalismo
        tituloEquipo.setBackgroundColor(Color.parseColor("#E0E0E0")); // Fondo gris claro
        tituloEquipo.setPadding(16, 16, 16, 16);
        tituloEquipo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tituloEquipo.setTypeface(null, android.graphics.Typeface.BOLD);

        // Añadir el título al diseño principal
        diseno.addView(tituloEquipo);

        consultarEquipo();

        scrollView.addView(diseno);
        return scrollView;
    }
}
