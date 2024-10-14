package com.trespuntocerodigital.actividades;

import android.graphics.drawable.GradientDrawable;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.trespuntocerodigital.contenido.Equipo;

public class EquipoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fondo degradado de color azul
        GradientDrawable fondoDegradado =
                new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[] {Color.parseColor("#0033A0"), Color.parseColor("#FFFFFF")});
        fondoDegradado.setCornerRadius(0f);

        // Crear un FrameLayout programáticamente
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setId(FrameLayout.generateViewId()); // Generar un ID para el FrameLayout
        frameLayout.setBackground(fondoDegradado);
        frameLayout.setElevation(8);

        setContentView(frameLayout);

        // Verifica si el savedInstanceState es nulo para evitar la recreación del fragmento
        if (savedInstanceState == null) {
            // Crea el fragmento de equipo
            Equipo equipoFragment = new Equipo(this, "Nuestro Equipo");

            // Agrega el fragmento al contenedor (FrameLayout)
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(frameLayout.getId(), equipoFragment).commit();
        }
    }
}
