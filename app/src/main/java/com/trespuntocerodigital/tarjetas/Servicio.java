package com.trespuntocerodigital.tarjetas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Servicio extends Fragment {
  
    private Context contexto;
    private ViewGroup diseno;
    private String nombre;
    private String descricion;
    private String precio;
    private Bitmap imagen;


    public Servicio(Context contexto, String nombre, String descricion, String precio, Bitmap imagen)
      	{
      
        this.contexto = contexto;
        
        this.nombre = nombre;
        		
        this.descricion = descricion;
        		
        this.precio = precio;
        		
        this.imagen = imagen;
        
        diseno = new LinearLayout(contexto);
        		diseno.setLayoutParams(new LinearLayout.LayoutParams(
        								   LinearLayout.LayoutParams.MATCH_PARENT,
        								   LinearLayout.LayoutParams.MATCH_PARENT
        							   ));
        		diseno.setBackgroundColor(0xFFDFA5A5);
        
      
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     
        diseno.setBackgroundColor(0xFFFFA5A5); 
        
        TextView	 tvNombre = new TextView(contexto);
        		tvNombre.setId(View.generateViewId());
        		tvNombre.setText(nombre);
        		tvNombre.setTextSize(18);
        
        		TextView tvDescripcion = new TextView(contexto);
        		tvDescripcion.setId(View.generateViewId());
        		tvDescripcion.setText(descricion);
        		tvDescripcion.setTextSize(16);
        
        		TextView tvPrecio = new TextView(contexto);
        		tvPrecio.setId(View.generateViewId());
        		tvPrecio.setText(precio);
        		
        		/*ImageView  ivImagenServicio = new ImageView(contexto);
        		ivImagenServicio.setId(View.generateViewId());
        		ivImagenServicio.setAdjustViewBounds(true);
        		ivImagenServicio.setImageBitmap(imagen);
        		diseno.addView(ivImagenServicio);*/
        		
        
        		diseno.addView(tvNombre);
        		diseno.addView(tvDescripcion);
        		diseno.addView(tvPrecio);
        		//diseno.addView(ivImagenServicio);

        return diseno;
    }
}

