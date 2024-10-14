package com.trespuntocerodigital.contenido;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trespuntocerodigital.basedatos.DBHelper;
import com.trespuntocerodigital.tresdigital.MainActivity;
import com.trespuntocerodigital.graficos.Textura2D;

public class Contacto extends Fragment {

    private String sectionName;
    private Context contexto;
    private LinearLayout diseno;
    private ScrollView scrollView;

    public Contacto(Context contexto, String sectionName) {
        this.contexto = contexto;
        this.sectionName = sectionName;
    }

    private void consultarContacto() {
        DBHelper dbHelper = new DBHelper(contexto);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("contacto", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            String telefono = cursor.getString(cursor.getColumnIndex("telefono"));
            String correo = cursor.getString(cursor.getColumnIndex("correo"));
            String direccion = cursor.getString(cursor.getColumnIndex("direccion"));

            crearUIContacto(telefono, correo, direccion);
        }

        cursor.close();
        db.close();
    }

    private void crearUIContacto(String telefono, String correo, String direccion) {
        // Crear un LinearLayout para organizar los elementos de manera vertical
        LinearLayout layout = new LinearLayout(contexto);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(20, 20, 20, 20);

        // Crear un fondo degradado acorde con el logo
        GradientDrawable fondoDegradado =
                new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[] {Color.parseColor("#2196F3"), Color.parseColor("#3F51B5")});
        fondoDegradado.setCornerRadius(0f); // Sin bordes redondeados
        layout.setBackground(fondoDegradado);

        // Nombre de la empresa como título
        TextView tituloTextView = new TextView(contexto);
        tituloTextView.setText("3.0 Digital");
        tituloTextView.setTextSize(24);
        tituloTextView.setTypeface(null, Typeface.BOLD);
        tituloTextView.setTextColor(Color.WHITE);
        tituloTextView.setGravity(Gravity.CENTER);

        // Logo de la empresa
        ImageView logoImageView = new ImageView(contexto);

        logoImageView.setImageBitmap(
                new Textura2D(MainActivity.recurso.cargarTextura("logo.jpg").getBipmap(), 300, 300)
                        .getBipmap());

        LinearLayout.LayoutParams logoParams =
                new LinearLayout.LayoutParams(300, 300); // Ajusta el tamaño según sea necesario
        logoParams.gravity = Gravity.CENTER;
        logoImageView.setLayoutParams(logoParams);

        // Teléfono
        TextView telefonoTextView = new TextView(contexto);
        telefonoTextView.setText("Teléfono: " + telefono);
        telefonoTextView.setTextSize(18);
        telefonoTextView.setGravity(Gravity.CENTER);
        telefonoTextView.setTextColor(Color.WHITE);

        // Correo
        TextView correoTextView = new TextView(contexto);
        correoTextView.setText("Correo: " + correo);
        correoTextView.setTextSize(18);
        correoTextView.setGravity(Gravity.CENTER);
        correoTextView.setTextColor(Color.WHITE);

        // Dirección
        TextView direccionTextView = new TextView(contexto);
        direccionTextView.setText("Dirección: " + direccion);
        direccionTextView.setTextSize(18);
        direccionTextView.setGravity(Gravity.CENTER);
        direccionTextView.setTextColor(Color.WHITE);

        // Botón de WhatsApp
        Button whatsappButton = new Button(contexto);
        whatsappButton.setText("Contactar por WhatsApp");
        whatsappButton.setBackgroundColor(
                Color.parseColor("#25D366")); // Color verde característico de WhatsApp
        whatsappButton.setTextColor(Color.WHITE);
        whatsappButton.setPadding(30, 10, 30, 10);

        // Acción del botón de WhatsApp (reemplaza el número con el correcto)
        whatsappButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url =
                                "https://api.whatsapp.com/send?phone=+58 "
                                        + telefono; // Número de teléfono con código del país
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        contexto.startActivity(intent);
                    }
                });

        // Agregar las vistas al diseño principal
        layout.addView(tituloTextView);
        layout.addView(logoImageView);
        layout.addView(telefonoTextView);
        layout.addView(correoTextView);
        layout.addView(direccionTextView);
        layout.addView(whatsappButton); // Agregar botón de WhatsApp

        // Agregar el layout al diseño principal
        diseno.addView(layout);
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

        consultarContacto();

        scrollView.addView(diseno);
        return scrollView;
    }
}
