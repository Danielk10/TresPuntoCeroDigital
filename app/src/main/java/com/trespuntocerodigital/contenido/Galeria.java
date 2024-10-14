package com.trespuntocerodigital.contenido;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trespuntocerodigital.basedatos.DBHelper;
import com.trespuntocerodigital.graficos.Textura2D;

public class Galeria extends Fragment {

    private String sectionName;
    private Context contexto;
    private LinearLayout diseno;
    private ScrollView scrollView;

    private int numerReproducion;

    public Galeria(Context contexto, String sectionName) {
        this.contexto = contexto;
        this.sectionName = sectionName;
        numerReproducion = 0;
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
        TextView tituloGaleria = new TextView(contexto);
        tituloGaleria.setText("Galería 3.0 Digital");
        tituloGaleria.setTextSize(24);
        tituloGaleria.setTextColor(Color.parseColor("#003366")); // Azul oscuro para profesionalismo
        tituloGaleria.setBackgroundColor(Color.parseColor("#E0E0E0")); // Fondo gris claro
        tituloGaleria.setPadding(16, 16, 16, 16);
        tituloGaleria.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tituloGaleria.setTypeface(null, android.graphics.Typeface.BOLD);

        // Añadir el título al diseño principal
        diseno.addView(tituloGaleria);

        cargarGaleria();

        scrollView.addView(diseno);
        return scrollView;
    }

    private void cargarGaleria() {

        DBHelper dbHelper = new DBHelper(contexto);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT imagen, video, descripcion FROM galeria";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String imagen = cursor.getString(cursor.getColumnIndex("imagen"));
                String video = cursor.getString(cursor.getColumnIndex("video"));
                String descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));

                if (imagen != null && !imagen.isEmpty()) {
                    agregarImagen(imagen, descripcion);
                }

                if (video != null && !video.isEmpty()) {
                    agregarVideo(video, descripcion);
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void agregarImagen(String imagePath, String descripcion) {
        ImageView imagenView = new ImageView(contexto);
        // Aquí cargarás la imagen desde el path, asumiendo que tienes una función de carga de
        // imágenes.

        Textura2D textuta = new Textura2D(BitmapFactory.decodeFile(imagePath), 640, 784);

        imagenView.setImageBitmap(textuta.getBipmap());
        // imagenView.setImageURI(Uri.parse(imagePath));
        imagenView.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 784));
        imagenView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        TextView descripcionView = new TextView(contexto);
        descripcionView.setText(descripcion);
        descripcionView.setTextSize(16);
        descripcionView.setTextColor(Color.DKGRAY);
        descripcionView.setPadding(0, 8, 0, 16);

        diseno.addView(imagenView);
        diseno.addView(descripcionView);
    }

    private void agregarVideo(String videoPath, String descripcion) {

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(videoPath);
        Bitmap bitmap = retriever.getFrameAtTime(1000000); // 1 segundo (en microsegundos)

        try {

            retriever.release();

        } catch (Exception e) {

        }

        ImageView muestra = new ImageView(contexto);

        muestra.setImageBitmap(bitmap);

        VideoView videoView = new VideoView(contexto);
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1612));
        videoView.setMediaController(new android.widget.MediaController(contexto));
        videoView.requestFocus();

        TextView descripcionView = new TextView(contexto);
        descripcionView.setText(descripcion);
        descripcionView.setTextSize(16);
        descripcionView.setTextColor(Color.DKGRAY);
        descripcionView.setPadding(0, 8, 0, 16);

        diseno.addView(videoView);

        diseno.addView(descripcionView);

        if (numerReproducion <= 0) {

            // Iniciar automáticamente el video cuando sea visible (opcional)
            videoView.setOnPreparedListener(mp -> videoView.start());
        }

        muestra.setOnClickListener(
                v -> {
                    muestra.setVisibility(View.GONE); // Oculta la miniatura
                    videoView.start();
                });

        numerReproducion++;
    }
}
