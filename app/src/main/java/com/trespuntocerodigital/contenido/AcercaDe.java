package com.trespuntocerodigital.contenido;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
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
import com.trespuntocerodigital.graficos.Textura2D;

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
            if (cursor.moveToFirst()) {

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
        // Crear el layout principal con un fondo degradado
        LinearLayout acercaLayout = new LinearLayout(contexto);
        acercaLayout.setOrientation(LinearLayout.VERTICAL);
        acercaLayout.setPadding(32, 32, 32, 32);
        acercaLayout.setGravity(Gravity.CENTER);
        // Crear un fondo degradado acorde con el logo
        GradientDrawable fondoDegradado =
                new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[] {Color.parseColor("#2196F3"), Color.parseColor("#3F51B5")});
        fondoDegradado.setCornerRadius(0f); // Sin bordes redondeados
        acercaLayout.setBackground(fondoDegradado);

        // Logo de la empresa
        ImageView logoImageView = new ImageView(contexto);
        logoImageView.setImageBitmap(new Textura2D(imagen, 640, 500).getBipmap());

        LinearLayout.LayoutParams logoParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        500); // Hacer que ocupe todo el ancho
        logoParams.setMargins(0, 0, 0, 24);
        logoImageView.setLayoutParams(logoParams);
        logoImageView.setScaleType(ImageView.ScaleType.CENTER_CROP); // Ajuste de la imagen

        // Contenedor para los textos
        LinearLayout textoContainer = new LinearLayout(contexto);
        textoContainer.setOrientation(LinearLayout.VERTICAL);
        textoContainer.setBackgroundColor(
                Color.parseColor("#80000000")); // Fondo negro semitransparente
        textoContainer.setPadding(24, 24, 24, 24);
        textoContainer.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams textoParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        textoParams.setMargins(0, -150, 0, 0); // Superponer sobre la imagen
        textoContainer.setLayoutParams(textoParams);

        // Título de la empresa con estilo
        TextView titleTextView = new TextView(contexto);
        titleTextView.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
        titleTextView.setText("3.0 Digital");
        titleTextView.setTextSize(30);
        titleTextView.setTypeface(null, Typeface.BOLD);
        titleTextView.setTextColor(Color.WHITE);
        titleTextView.setGravity(Gravity.CENTER);
        titleTextView.setPadding(0, 0, 0, 16);
        titleTextView.setShadowLayer(1.5f, -1, 1, Color.BLACK);

        // Descripción del servicio técnico autorizado
        TextView descriptionTextView = new TextView(contexto);
        descriptionTextView.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
        descriptionTextView.setText(descripcion);
        descriptionTextView.setTextSize(18);
        descriptionTextView.setTextColor(Color.LTGRAY);
        descriptionTextView.setGravity(Gravity.CENTER);
        descriptionTextView.setPadding(0, 0, 0, 16);

        // Información adicional con estilo más ligero
        TextView additionalInfoTextView = new TextView(contexto);
        additionalInfoTextView.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
        additionalInfoTextView.setText(
                "Como servicio técnico autorizado, garantizamos el mejor soporte para sus productos. "
                        + "Contamos con profesionales capacitados y un compromiso sólido con la satisfacción de nuestros clientes.");
        additionalInfoTextView.setTextSize(16);
        additionalInfoTextView.setTextColor(Color.parseColor("#D1D1D1"));
        additionalInfoTextView.setGravity(Gravity.CENTER);
        additionalInfoTextView.setPadding(0, 16, 0, 0);

        // Añadir los textos al contenedor
        textoContainer.addView(titleTextView);
        textoContainer.addView(descriptionTextView);
        textoContainer.addView(additionalInfoTextView);

        // Añadir elementos al layout principal
        acercaLayout.addView(logoImageView);
        acercaLayout.addView(textoContainer);

        // Agregar el layout creado al contenedor principal
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
