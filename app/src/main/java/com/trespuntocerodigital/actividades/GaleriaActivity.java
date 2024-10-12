package com.trespuntocerodigital.actividades;

import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.trespuntocerodigital.contenido.Galeria;

public class GaleriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Crear un FrameLayout programáticamente
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setId(FrameLayout.generateViewId()); // Generar un ID para el FrameLayout
        setContentView(frameLayout);

        // Verifica si el savedInstanceState es nulo para evitar la recreación del fragmento
        if (savedInstanceState == null) {
            // Crea el fragmento de equipo
            Galeria equipoFragment = new Galeria(this, "Nuestro Equipo");

            // Agrega el fragmento al contenedor (FrameLayout)
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(frameLayout.getId(), equipoFragment).commit();
        }
    }
}
