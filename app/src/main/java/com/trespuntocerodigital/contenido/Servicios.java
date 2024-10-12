package com.trespuntocerodigital.contenido;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Servicios extends Fragment {

    private String sectionName;
    private Context contexto;

    public Servicios(Context contexto, String sectionName) {
        this.contexto = contexto;
        this.sectionName = sectionName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Crear LinearLayout de manera programática
        LinearLayout layout = new LinearLayout(contexto);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));
        layout.setBackgroundColor(0xFFFFA5A5); // Color de fondo #FFA5A5

        // Crear el botón de manera programática
        Button button = new Button(contexto);
        button.setText("Click here");
        button.setTextSize(24);

        // Añadir el botón al layout
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        buttonParams.setMargins(32, 32, 32, 32); // Margen de 32px alrededor del botón
        button.setLayoutParams(buttonParams);

        layout.addView(button);

        return layout;
    }
}
