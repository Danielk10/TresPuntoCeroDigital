package com.trespuntocerodigital.contenido;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trespuntocerodigital.basedatos.DBHelper;
import com.trespuntocerodigital.graficos.Textura2D;

import java.util.ArrayList;
import java.util.List;

public class Productos extends Fragment {

    private String sectionName;
    private Context contexto;
    private LinearLayout diseno;
    private ScrollView scrollView;

    public Productos(Context contexto, String sectionName) {
        this.contexto = contexto;
        this.sectionName = sectionName;
    }

    public List<String> consultarProductos() {
        List<String> listaProductos = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(contexto);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("productos", null, null, null, null, null, null);

        if (cursor != null) {

            while (cursor.moveToNext()) {
                String imagenProducto = cursor.getString(cursor.getColumnIndex("imagen"));
                String nombreProducto = cursor.getString(cursor.getColumnIndex("nombre_producto"));
                String descripcionProducto =
                        cursor.getString(cursor.getColumnIndex("descripcion_producto"));
                double precioProducto = cursor.getDouble(cursor.getColumnIndex("precio_producto"));

                Bitmap bitmap = null;
                // Crear un ImageView para la imagen
                if (imagenProducto != null && cursor.getCount() > 0) {

                    bitmap = BitmapFactory.decodeFile(imagenProducto);
                }

                crearUI(bitmap, nombreProducto, descripcionProducto, "" + precioProducto);

                listaProductos.add(
                        "Producto: "
                                + nombreProducto
                                + ", Descripción: "
                                + descripcionProducto
                                + ", Precio: $"
                                + precioProducto);
            }
        }

        cursor.close();
        db.close();
        return listaProductos;
    }

    private void crearUI(
            Bitmap imagenProducto,
            String nombreProducto,
            String descripcionProducto,
            String precioProducto) {

        // Estilo de la tarjeta (LinearLayout)
        LinearLayout serviceLayout = new LinearLayout(contexto);
        serviceLayout.setOrientation(LinearLayout.VERTICAL);
        serviceLayout.setPadding(24, 24, 24, 24);

        // Crear background con bordes redondeados programáticamente
        GradientDrawable backgroundDrawable = new GradientDrawable();
        backgroundDrawable.setColor(Color.parseColor("#1E1E2C")); // Color de fondo de la tarjeta
        backgroundDrawable.setCornerRadius(30f); // Bordes redondeados
        serviceLayout.setBackground(backgroundDrawable);

        // Agregar sombras en API 21 o superior (minSdkVersion >= 21)
        serviceLayout.setElevation(8);

        // LayoutParams con márgenes
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(16, 16, 16, 16);
        serviceLayout.setLayoutParams(layoutParams);

        // Imagen del producto
        ImageView imageView = new ImageView(contexto);
        LinearLayout.LayoutParams imageParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500);
        imageView.setLayoutParams(imageParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(new Textura2D(imagenProducto, 640, 500).getBipmap());

        // Nombre del producto (TextView)
        TextView nameTextView = new TextView(contexto);
        nameTextView.setText(nombreProducto);
        nameTextView.setTextSize(24);
        nameTextView.setTextColor(Color.WHITE);
        nameTextView.setTypeface(null, Typeface.BOLD);
        nameTextView.setPadding(0, 16, 0, 8); // Espaciado superior

        // Descripción del producto (TextView)
        TextView descriptionTextView = new TextView(contexto);
        descriptionTextView.setText(descripcionProducto);
        descriptionTextView.setTextSize(16);
        descriptionTextView.setTextColor(Color.LTGRAY);
        descriptionTextView.setPadding(0, 8, 0, 8); // Espaciado

        // Precio del producto (TextView)
        TextView priceTextView = new TextView(contexto);
        priceTextView.setText("Precio: $" + precioProducto);
        priceTextView.setTextSize(18);
        priceTextView.setTextColor(Color.parseColor("#4CAF50")); // Color verde
        priceTextView.setTypeface(null, Typeface.BOLD);
        priceTextView.setPadding(0, 8, 0, 0); // Espaciado inferior

        Button whatsappButton = new Button(contexto);
        whatsappButton.setText("Contactar por WhatsApp");
        whatsappButton.setTextColor(Color.WHITE);
        whatsappButton.setBackgroundColor(Color.parseColor("#25D366")); // Verde típico de WhatsApp
        whatsappButton.setPadding(16, 8, 16, 8);
        whatsappButton.setTypeface(null, Typeface.BOLD);
        whatsappButton.setAllCaps(false); // Texto en minúsculas

        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(0, 16, 0, 0); // Margen superior al botón
        whatsappButton.setLayoutParams(buttonParams);

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

        // Agregar las vistas al layout de la tarjeta
        serviceLayout.addView(imageView);
        serviceLayout.addView(nameTextView);
        serviceLayout.addView(descriptionTextView);
        serviceLayout.addView(priceTextView);
        serviceLayout.addView(whatsappButton);

        // Agregar la tarjeta al diseño principal
        diseno.addView(serviceLayout);
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

        consultarProductos();

        scrollView.addView(diseno);

        return scrollView;
    }
}
