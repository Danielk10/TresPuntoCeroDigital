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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trespuntocerodigital.basedatos.DBHelper;
import com.trespuntocerodigital.servidor.ImageDownloaderTask;

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

        LinearLayout serviceLayout = new LinearLayout(contexto);
        serviceLayout.setOrientation(LinearLayout.VERTICAL);
        serviceLayout.setPadding(16, 16, 16, 16);
        serviceLayout.setBackgroundColor(Color.parseColor("#f0f0f0"));
        serviceLayout.setElevation(4); // Añadir sombra

        // Crear el ImageView
        ImageView imageView = new ImageView(contexto);
        LinearLayout.LayoutParams imageParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400);
        imageView.setLayoutParams(imageParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(imagenProducto);
        imageView.setAdjustViewBounds(true);

        // Crear TextView para el nombre del servicio
        TextView nameTextView = new TextView(contexto);
        nameTextView.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
        nameTextView.setText(nombreProducto);
        nameTextView.setTextSize(20);
        nameTextView.setTextColor(Color.BLACK);

        // Crear TextView para la descripción del servicio
        TextView descriptionTextView = new TextView(contexto);
        descriptionTextView.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
        descriptionTextView.setText(descripcionProducto);
        descriptionTextView.setTextSize(16);
        descriptionTextView.setTextColor(Color.DKGRAY);

        // Crear TextView para el precio del servicio
        TextView priceTextView = new TextView(contexto);
        priceTextView.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
        priceTextView.setText("Precio: $" + precioProducto);
        priceTextView.setTextSize(18);
        priceTextView.setTextColor(Color.parseColor("#4CAF50"));

        // Agregar las vistas al layout
        serviceLayout.addView(imageView);
        serviceLayout.addView(nameTextView);
        serviceLayout.addView(descriptionTextView);
        serviceLayout.addView(priceTextView);

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
