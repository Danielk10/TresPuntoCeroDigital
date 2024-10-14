package com.trespuntocerodigital.contenido;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trespuntocerodigital.basedatos.DBHelper;
import com.trespuntocerodigital.graficos.Textura2D;

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
        // Crear el layout principal del servicio
        LinearLayout servicioLayout = new LinearLayout(contexto);
        servicioLayout.setOrientation(LinearLayout.VERTICAL);
        servicioLayout.setPadding(16, 16, 16, 16);
        servicioLayout.setBackgroundColor(Color.parseColor("#1D1D1D")); // Fondo oscuro
        servicioLayout.setElevation(8);

        LinearLayout.LayoutParams servicioParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        servicioParams.setMargins(0, 0, 0, 24);
        servicioLayout.setLayoutParams(servicioParams);

        // Imagen del servicio
        ImageView imagenView = new ImageView(contexto);

        imagenView.setImageBitmap(new Textura2D(imagen, 640, 30).getBipmap());

        LinearLayout.LayoutParams imagenParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        imagenView.setLayoutParams(imagenParams);
        imagenView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // Texto del nombre del servicio
        TextView nombreTextView = new TextView(contexto);
        nombreTextView.setText(nombre);
        nombreTextView.setTextSize(20);
        nombreTextView.setTextColor(Color.WHITE);
        nombreTextView.setTypeface(null, Typeface.BOLD);
        nombreTextView.setPadding(0, 16, 0, 8);

        // Texto de la descripción del servicio
        TextView descripcionTextView = new TextView(contexto);
        descripcionTextView.setText(descripcion);
        descripcionTextView.setTextSize(16);
        descripcionTextView.setTextColor(Color.LTGRAY);

        // Texto del precio del servicio
        TextView precioTextView = new TextView(contexto);
        precioTextView.setText("Precio: $" + precio);
        precioTextView.setTextSize(16);
        precioTextView.setTextColor(Color.parseColor("#4CAF50")); // Verde oscuro
        precioTextView.setPadding(0, 8, 0, 16);

        // Crear el botón de WhatsApp
        Button whatsappButton = new Button(contexto);
        whatsappButton.setText("Contactar por WhatsApp");
        whatsappButton.setTextColor(Color.WHITE);
        whatsappButton.setBackgroundColor(Color.parseColor("#25D366")); // Verde típico de WhatsApp
        whatsappButton.setPadding(16, 8, 16, 8);
        whatsappButton.setTypeface(null, Typeface.BOLD);
        whatsappButton.setAllCaps(false); // Texto en minúsculas

        // Configurar el layout del botón
        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(0, 16, 0, 0); // Margen superior al botón
        whatsappButton.setLayoutParams(buttonParams);

        // Configurar el evento OnClick para abrir WhatsApp
        whatsappButton.setOnClickListener(
                v -> {
                    String numeroTelefono =
                            "+58 424-4655356"; // Reemplaza con el número de teléfono de la empresa
                    String mensaje = "Hola, estoy interesado en obtener más información.";
                    String url = "https://wa.me/" + numeroTelefono + "?text=" + Uri.encode(mensaje);

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    contexto.startActivity(intent);
                
                       });

        // Layout de texto y botón
        LinearLayout contenidoLayout = new LinearLayout(contexto);
        contenidoLayout.setOrientation(LinearLayout.VERTICAL);
        contenidoLayout.addView(nombreTextView);
        contenidoLayout.addView(descripcionTextView);
        contenidoLayout.addView(precioTextView);

        // Añadir la imagen y el contenido al layout principal
        servicioLayout.addView(imagenView);
        servicioLayout.addView(contenidoLayout); // Añadir el botón de WhatsApp al diseño principal
        servicioLayout.addView(whatsappButton);

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
