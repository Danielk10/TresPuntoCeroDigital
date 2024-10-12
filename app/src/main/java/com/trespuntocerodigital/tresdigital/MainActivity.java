package com.trespuntocerodigital.tresdigital;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.trespuntocerodigital.actividades.EquipoActivity;
import com.trespuntocerodigital.actividades.GaleriaActivity;
import com.trespuntocerodigital.contenido.AcercaDe;
import com.trespuntocerodigital.contenido.Contacto;
import com.trespuntocerodigital.contenido.Inicio;
import com.trespuntocerodigital.contenido.Productos;
import com.trespuntocerodigital.contenido.Servicios;
import com.trespuntocerodigital.demo.InsertarDatosActivity;
import com.trespuntocerodigital.diseno.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCenter.start(getApplication(), "d472f8c2-2515-4f9a-adab-f55e98cde56d",
                                  Analytics.class, Crashes.class);
                                  

        // Crear ConstraintLayout como el contenedor principal
        ConstraintLayout mainLayout = new ConstraintLayout(this);
        mainLayout.setLayoutParams(
                new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.MATCH_PARENT));
               // Crear el Toolbar de manera programática
        Toolbar toolbar = new Toolbar(this);
        toolbar.setId(View.generateViewId());
        toolbar.setTitle("3.0 Digital");
        toolbar.setBackgroundColor(Color.parseColor("#FD41B7FF"));
        toolbar.setTitleTextColor(Color.WHITE);

        // Configurar el Toolbar como ActionBar
        setSupportActionBar(toolbar);

        ConstraintLayout.LayoutParams toolbarParams =
                new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT);

        // Crear TabLayout
        TabLayout tabLayout = new TabLayout(this);
        tabLayout.setId(View.generateViewId());
        tabLayout.setBackgroundColor(0xFF41B7FF); // Color de fondo #41B7FF
        ConstraintLayout.LayoutParams tabLayoutParams =
                new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT);
        tabLayoutParams.topToBottom = toolbar.getId(); // Posicionar debajo del Toolbar
        mainLayout.addView(tabLayout, tabLayoutParams);
        mainLayout.addView(toolbar, toolbarParams);

        // Crear ViewPager
        ViewPager viewPager = new ViewPager(this);
        viewPager.setId(View.generateViewId());
        ConstraintLayout.LayoutParams viewPagerParams =
                new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT);
        viewPagerParams.topToBottom = tabLayout.getId();
        viewPagerParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        viewPagerParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        viewPagerParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        viewPagerParams.height = 0; // Asigna el peso con constraints
        mainLayout.addView(viewPager, viewPagerParams);

        // Crear lista de fragmentos para cada sección
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Inicio(this, "Inicio"));
        fragments.add(new Productos(this, "Productos"));
        fragments.add(new Servicios(this, "Servicios"));
        fragments.add(new Contacto(this, "Contacto"));
        fragments.add(new AcercaDe(this, "Acerca de"));

        // Configurar adaptador del ViewPager
        SectionsPagerAdapter adapter =
                new SectionsPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        setContentView(mainLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem menuInicio = menu.add(Menu.NONE, 1, 1, "Inicio");

        MenuItem menuProductos = menu.add(Menu.NONE, 2, 2, "Productos");

        MenuItem menuServicios = menu.add(Menu.NONE, 3, 3, "Servicios");

        MenuItem menuGaleria = menu.add(Menu.NONE, 4, 4, "Galeria");

        MenuItem menuHorarioAtencion = menu.add(Menu.NONE, 5, 5, "Horario de Atención");

        MenuItem menuHorarioEquipo = menu.add(Menu.NONE, 6, 6, "Nuestro Equipo");

        MenuItem menuContacto = menu.add(Menu.NONE, 7, 7, "Contacto");

        MenuItem menuAcerca = menu.add(Menu.NONE, 8, 8, "Acerca de");

        MenuItem menudemo = menu.add(Menu.NONE, 9, 9, "Cargar Data ( Ojo Demo))");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Manejar eventos del menú
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show();
                return true;
            case 2:
                Toast.makeText(this, "Productos", Toast.LENGTH_SHORT).show();
                return true;
            case 3:
                Toast.makeText(this, "Servicios", Toast.LENGTH_SHORT).show();
                return true;
            case 4:
                Intent intent4 = new Intent(this, GaleriaActivity.class);
                startActivity(intent4);

                Toast.makeText(this, "Galeria", Toast.LENGTH_SHORT).show();
                return true;
            case 5:
                Toast.makeText(this, "Horarios de Atención", Toast.LENGTH_SHORT).show();
                return true;
            case 6:
                Intent intent5 = new Intent(this, EquipoActivity.class);
                startActivity(intent5);

                Toast.makeText(this, "Nuestro Equipo", Toast.LENGTH_SHORT).show();
                return true;
            case 7:
                Toast.makeText(this, "Contacto", Toast.LENGTH_SHORT).show();
                return true;
            case 8:
                Toast.makeText(this, "Acerca de", Toast.LENGTH_SHORT).show();
                return true;
            case 9:
                Intent intent9 = new Intent(this, InsertarDatosActivity.class);
                startActivity(intent9);
                Toast.makeText(this, "Cargar Data ( Ojo Demo))", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {

        // add event for back button pressed
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
        return true;
    }
}
