package com.trespuntocerodigital.contenido;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trespuntocerodigital.basedatos.DBHelper;

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
        // Teléfono
        TextView telefonoTextView = new TextView(contexto);
        telefonoTextView.setText("Teléfono: " + telefono);
        telefonoTextView.setTextSize(18);

        // Correo
        TextView correoTextView = new TextView(contexto);
        correoTextView.setText("Correo: " + correo);
        correoTextView.setTextSize(18);

        // Dirección
        TextView direccionTextView = new TextView(contexto);
        direccionTextView.setText("Dirección: " + direccion);
        direccionTextView.setTextSize(18);

        // Agregar las vistas al diseño principal
        diseno.addView(telefonoTextView);
        diseno.addView(correoTextView);
        diseno.addView(direccionTextView);
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
