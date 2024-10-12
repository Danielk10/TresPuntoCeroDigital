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

public class AcercaDe extends Fragment {

    private String sectionName;
    private Context contexto;
    private LinearLayout diseno;
    private ScrollView scrollView;

    public AcercaDe(Context contexto, String sectionName) {
        this.contexto = contexto;
        this.sectionName = sectionName;
    }

    private void consultarAcercaDe() {
        DBHelper dbHelper = new DBHelper(contexto);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("acerca_de", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {

                String imagen = cursor.getString(cursor.getColumnIndex("imagen"));
                String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
                String descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));

                Bitmap bitmap = null;
                // Crear un ImageView para la imagen
                if (imagen != null) {

                    bitmap = BitmapFactory.decodeFile(imagen);
                }

                crearUI(bitmap, titulo, descripcion);
            }
        }
        cursor.close();
        db.close();
    }

    private void crearUI(Bitmap imagen, String titulo, String descripcion) {
        LinearLayout acercaLayout = new LinearLayout(contexto);
        acercaLayout.setOrientation(LinearLayout.VERTICAL);
        acercaLayout.setPadding(16, 16, 16, 16);
        acercaLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        acercaLayout.setElevation(4);

        // Logo de la empresa (opcional)
        ImageView logoImageView = new ImageView(contexto);
        logoImageView.setImageBitmap(imagen);
        logoImageView.setAdjustViewBounds(true);
        LinearLayout.LayoutParams logoParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 200);
        logoParams.setMargins(0, 0, 0, 16);
        logoImageView.setLayoutParams(logoParams);
        logoImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        // Título de la empresa
        TextView titleTextView = new TextView(contexto);
        titleTextView.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
        titleTextView.setText(titulo);
        titleTextView.setTextSize(24);
        titleTextView.setTextColor(Color.BLACK);

        // Descripción del servicio técnico autorizado
        TextView descriptionTextView = new TextView(contexto);
        descriptionTextView.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
        descriptionTextView.setText(descripcion);
        descriptionTextView.setTextSize(16);
        descriptionTextView.setTextColor(Color.DKGRAY);

        // Información adicional
        TextView additionalInfoTextView = new TextView(contexto);
        additionalInfoTextView.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
        additionalInfoTextView.setText(
                "Como servicio técnico autorizado, garantizamos el mejor soporte para sus productos. "
                        + "Contamos con profesionales capacitados y un compromiso sólido con la satisfacción de nuestros clientes.");
        additionalInfoTextView.setTextSize(16);
        additionalInfoTextView.setTextColor(Color.GRAY);
        additionalInfoTextView.setPadding(0, 16, 0, 0);

        // Añadir los elementos al layout
        acercaLayout.addView(logoImageView);
        acercaLayout.addView(titleTextView);
        acercaLayout.addView(descriptionTextView);
        // acercaLayout.addView(additionalInfoTextView);

        diseno.addView(acercaLayout);
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

        consultarAcercaDe();

        scrollView.addView(diseno);
        return scrollView;
    }
}
