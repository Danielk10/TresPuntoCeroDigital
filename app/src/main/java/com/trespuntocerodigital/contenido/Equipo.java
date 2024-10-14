package com.trespuntocerodigital.contenido;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trespuntocerodigital.basedatos.DBHelper;
import com.trespuntocerodigital.graficos.Textura2D;

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
            String correo = cursor.getString(cursor.getColumnIndex("correo"));
            String especialidad = cursor.getString(cursor.getColumnIndex("especialidad"));
            String foto = cursor.getString(cursor.getColumnIndex("foto"));

            Bitmap bitmap = null;
            // Crear un ImageView para la imagen
            if (foto != null && cursor.getCount() > 0) {

                bitmap = BitmapFactory.decodeFile(foto);
            }

            crearUIEquipo(nombre, apellido, telefono, correo, especialidad, bitmap);
        }

        cursor.close();
        db.close();
    }

    private void crearUIEquipo(
            String nombre,
            String apellido,
            String telefono,
            String correo,
            String especialidad,
            Bitmap foto) {
        // Layout principal del equipo
        RelativeLayout equipoLayout = new RelativeLayout(contexto);
        equipoLayout.setPadding(16, 16, 16, 16);

        // Fondo degradado para el layout
        GradientDrawable fondoDegradado =
                new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[] {
                            Color.parseColor("#e0f7fa"), Color.parseColor("#006064")
                        } // Degradado claro a oscuro
                        );
        fondoDegradado.setCornerRadius(16); // Bordes redondeados
        equipoLayout.setBackground(fondoDegradado);
        equipoLayout.setElevation(8); // Sombra

        // Imagen del miembro del equipo
        ImageView fotoImageView = new ImageView(contexto);

        fotoImageView.setImageBitmap(new Textura2D(foto, 200, 350).getBipmap());

        // Ajuste de la imagen a un formato vertical
        RelativeLayout.LayoutParams fotoParams =
                new RelativeLayout.LayoutParams(200, 350); // Dimensiones verticales
        fotoParams.setMargins(0, 0, 16, 0);
        fotoImageView.setLayoutParams(fotoParams);
        fotoImageView.setScaleType(ImageView.ScaleType.FIT_CENTER); // Ajuste sin recortar
        fotoImageView.setId(View.generateViewId());

        // Layout para el texto
        LinearLayout textoLayout = new LinearLayout(contexto);
        textoLayout.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams textoParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        textoParams.addRule(RelativeLayout.RIGHT_OF, fotoImageView.getId());
        textoParams.setMargins(16, 0, 0, 0);
        textoLayout.setLayoutParams(textoParams);
        textoLayout.setId(View.generateViewId());

        // Nombre del miembro
        TextView nombreTextView = new TextView(contexto);
        nombreTextView.setText(nombre + " " + apellido);
        nombreTextView.setTextSize(22);
        nombreTextView.setTextColor(Color.WHITE);
        nombreTextView.setTypeface(null, Typeface.BOLD);

        // Especialidad del miembro
        TextView especialidadTextView = new TextView(contexto);
        especialidadTextView.setText(especialidad);
        especialidadTextView.setTextSize(18);
        especialidadTextView.setTextColor(Color.parseColor("#B3E5FC"));

        // Correo del miembro
        TextView correoTextView = new TextView(contexto);
        correoTextView.setText("Correo: " + correo);
        correoTextView.setTextSize(16);
        correoTextView.setTextColor(Color.parseColor("#E1F5FE"));

        // Teléfono del miembro
        TextView telefonoTextView = new TextView(contexto);
        telefonoTextView.setText("Tel: " + telefono);
        telefonoTextView.setTextSize(16);
        telefonoTextView.setTextColor(Color.parseColor("#B3E5FC"));

        // Añadir el texto al layout de texto
        textoLayout.addView(nombreTextView);
        textoLayout.addView(especialidadTextView);
        textoLayout.addView(correoTextView);
        textoLayout.addView(telefonoTextView);

        // Botón de WhatsApp
        Button whatsappButton = new Button(contexto);
        whatsappButton.setText("WhatsApp");
        whatsappButton.setTextSize(14);
        whatsappButton.setTextColor(Color.WHITE);
        whatsappButton.setBackgroundColor(Color.parseColor("#25D366")); // Color verde de WhatsApp
        whatsappButton.setPadding(16, 16, 16, 16);

        // Bordes redondeados para el botón
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(50);
        shape.setColor(Color.parseColor("#25D366"));
        whatsappButton.setBackground(shape);

        RelativeLayout.LayoutParams whatsappParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        whatsappParams.addRule(RelativeLayout.BELOW, textoLayout.getId());
        whatsappParams.addRule(RelativeLayout.RIGHT_OF, fotoImageView.getId());
        whatsappParams.setMargins(16, 16, 0, 0);
        whatsappButton.setLayoutParams(whatsappParams);

        // Acción al hacer clic en el botón de WhatsApp
        whatsappButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://wa.me/" + "+58 " + telefono));
                        contexto.startActivity(intent);
                    }
                });

        // Añadir la imagen, el texto y el botón al layout principal
        equipoLayout.addView(fotoImageView);
        equipoLayout.addView(textoLayout);
        equipoLayout.addView(whatsappButton);

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
