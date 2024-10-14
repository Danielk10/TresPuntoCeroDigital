package com.trespuntocerodigital.contenido;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.trespuntocerodigital.tresdigital.MainActivity;
import com.trespuntocerodigital.graficos.Textura2D;

public class Inicio extends Fragment {

    private String sectionName;
    private Context contexto;
    private LinearLayout diseno;
    private ScrollView scrollView;

    public Inicio(Context contexto, String sectionName) {
        this.contexto = contexto;
        this.sectionName = sectionName;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        // Crear un ScrollView para contenido desplazable
        scrollView = new ScrollView(contexto);
        scrollView.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // Crear un fondo degradado acorde con el logo
        GradientDrawable fondoDegradado =
                new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[] {Color.parseColor("#2196F3"), Color.parseColor("#3F51B5")});
        fondoDegradado.setCornerRadius(0f); // Sin bordes redondeados

        // Crear un LinearLayout para el contenido
        diseno = new LinearLayout(contexto);
        diseno.setOrientation(LinearLayout.VERTICAL);
        diseno.setPadding(16, 16, 16, 16);
        diseno.setBackground(fondoDegradado);

        // Llamar a métodos para añadir diferentes secciones
        agregarBannerPrincipal();
        agregarPromocion(
                "¡Promoción Especial!", "Obtén un 30% de descuento en tu primer servicio.");
        agregarImagenPublicitaria(MainActivity.recurso.cargarTextura("imagen1.jpg").getBipmap());
        agregarTextoInformativo(
                "Bienvenidos a 3.0 Digital",
                "Somos una empresa líder en soluciones digitales. Conéctate al futuro con nuestros servicios innovadores.  📞 ¿Tienes alguna pregunta o necesitas más información? ¡No dudes en contactarnos al 0424-4655356 o envíanos un mensaje directo aquí en nuestras redes sociales! Estaremos encantados de ayudarte en todo lo que necesites.");

        scrollView.addView(diseno);
        return scrollView;
    }

    private void agregarBannerPrincipal() {
        ImageView bannerPrincipal = new ImageView(contexto);
        
       bannerPrincipal.setImageBitmap(new Textura2D(MainActivity.recurso.cargarTextura("imagen2.jpg").getBipmap(), 640, 400).getBipmap());
        
        bannerPrincipal.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600));
        bannerPrincipal.setScaleType(ImageView.ScaleType.CENTER_CROP);
        diseno.addView(bannerPrincipal);
    }

    private void agregarPromocion(String titulo, String descripcion) {
        LinearLayout promocionLayout = new LinearLayout(contexto);
        promocionLayout.setOrientation(LinearLayout.VERTICAL);
        promocionLayout.setPadding(16, 16, 16, 16);
        promocionLayout.setBackgroundColor(Color.parseColor("#FFE0B2"));
        promocionLayout.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView tituloPromocion = new TextView(contexto);
        tituloPromocion.setText(titulo);
        tituloPromocion.setTextSize(24);
        tituloPromocion.setTextColor(Color.parseColor("#FF6F00"));
        tituloPromocion.setGravity(Gravity.CENTER);

        TextView descripcionPromocion = new TextView(contexto);
        descripcionPromocion.setText(descripcion);
        descripcionPromocion.setTextSize(16);
        descripcionPromocion.setTextColor(Color.parseColor("#5D4037"));
        descripcionPromocion.setGravity(Gravity.CENTER);

        promocionLayout.addView(tituloPromocion);
        promocionLayout.addView(descripcionPromocion);
        diseno.addView(promocionLayout);
    }

    private void agregarImagenPublicitaria(Bitmap imagen) {
        ImageView publicidad = new ImageView(contexto);
        publicidad.setImageBitmap(new Textura2D(imagen, 640, 300).getBipmap());
        publicidad.setLayoutParams(
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        publicidad.setPadding(0, 16, 0, 16);
        publicidad.setScaleType(ImageView.ScaleType.CENTER_CROP);
        diseno.addView(publicidad);
    }

    private void agregarTextoInformativo(String titulo, String contenido) {
        LinearLayout textoLayout = new LinearLayout(contexto);
        textoLayout.setOrientation(LinearLayout.VERTICAL);
        textoLayout.setPadding(16, 16, 16, 16);
        textoLayout.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView tituloTexto = new TextView(contexto);
        tituloTexto.setText(titulo);
        tituloTexto.setTextSize(22);
        tituloTexto.setTextColor(Color.BLACK);
        tituloTexto.setGravity(Gravity.CENTER);

        TextView contenidoTexto = new TextView(contexto);
        contenidoTexto.setText(contenido);
        contenidoTexto.setTextSize(16);
        contenidoTexto.setTextColor(Color.DKGRAY);
        contenidoTexto.setGravity(Gravity.CENTER_HORIZONTAL);

        textoLayout.addView(tituloTexto);
        textoLayout.addView(contenidoTexto);
        diseno.addView(textoLayout);
    }
}
