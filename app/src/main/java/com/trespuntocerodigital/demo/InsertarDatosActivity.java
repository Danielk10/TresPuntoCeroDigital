package com.trespuntocerodigital.demo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Dumpable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.diamon.basedatos.DBHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InsertarDatosActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1;
    private static final int REQUEST_VIDEO_PICK = 2;
    private Uri imagenUri;
    private Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Crear ScrollView
        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams scrollParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);

        // Crear LinearLayout principal
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(16, 16, 16, 16);

        // Agregar cada sección de tabla
        mainLayout.addView(crearSeccionEquipo());
        mainLayout.addView(crearSeccionAcercaDe());
        mainLayout.addView(crearSeccionContacto());
        mainLayout.addView(crearSeccionGaleria());
        mainLayout.addView(crearSeccionServicios());
        mainLayout.addView(crearSeccionProductos());

        // Agregar ScrollView y setear el layout principal
        scrollView.addView(mainLayout);
        setContentView(scrollView, scrollParams);
    }

    private LinearLayout crearSeccionEquipo() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(crearTextView("Equipo"));

        Button btnSeleccionarImagen = new Button(this);
        btnSeleccionarImagen.setText("Seleccionar Imagen");
        btnSeleccionarImagen.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        seleccionarImagen();
                    }
                });

        EditText nombre = crearEditText("Nombre");

        EditText apellido = crearEditText("Apellido");

        EditText telefono = crearEditText("Teléfono", InputType.TYPE_CLASS_PHONE);

        EditText correo = crearEditText("Correo", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        EditText especialidad = crearEditText("Especialidad");

        // Crear y añadir EditTexts para cada campo de la tabla Equipo
        layout.addView(nombre);
        layout.addView(apellido);
        layout.addView(telefono);
        layout.addView(correo);
        layout.addView(especialidad);
        layout.addView(btnSeleccionarImagen);

        // Botón para guardar datos
        Button btnGuardar = new Button(this);
        btnGuardar.setText("Guardar Equipo");

        btnGuardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        guardarDatosEquipo(nombre, apellido, telefono, correo, especialidad);
                    }
                });

        layout.addView(btnGuardar);

        return layout;
    }

    private LinearLayout crearSeccionAcercaDe() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(crearTextView("Acerca De"));

        Button btnSeleccionarImagen = new Button(this);
        btnSeleccionarImagen.setText("Seleccionar Imagen");
        btnSeleccionarImagen.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        seleccionarImagen();
                    }
                });

        layout.addView(btnSeleccionarImagen);

        EditText titulo = crearEditText("Título");

        EditText descrpcion = crearEditText("Descripción");

        layout.addView(titulo);

        layout.addView(descrpcion);

        Button btnGuardar = new Button(this);
        btnGuardar.setText("Guardar Acerca De");

        btnGuardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        guardarDatosAcerca(titulo, descrpcion);
                    }
                });
        layout.addView(btnGuardar);

        return layout;
    }

    private LinearLayout crearSeccionContacto() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(crearTextView("Contacto"));

        EditText telefono = crearEditText("Teléfono", InputType.TYPE_CLASS_PHONE);

        EditText correo = crearEditText("Correo", InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        EditText direccion = crearEditText("Dirección");

        layout.addView(telefono);
        layout.addView(correo);
        layout.addView(direccion);

        Button btnGuardar = new Button(this);
        btnGuardar.setText("Guardar Contacto");

        btnGuardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        guardarDatosContacto(telefono, correo, direccion);
                    }
                });
        layout.addView(btnGuardar);

        return layout;
    }

    private LinearLayout crearSeccionGaleria() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(crearTextView("Galeria"));

        Button btnSeleccionarImagen = new Button(this);
        btnSeleccionarImagen.setText("Seleccionar Imagen");
        btnSeleccionarImagen.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        seleccionarImagen();
                    }
                });

        Button btnSeleccionarVideo = new Button(this);
        btnSeleccionarVideo.setText("Seleccionar Video");
        btnSeleccionarVideo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        seleccionarVideo();
                    }
                });

        layout.addView(btnSeleccionarImagen);
        layout.addView(btnSeleccionarVideo);

        EditText descrpcion = crearEditText("Descripción");

        layout.addView(descrpcion);

        Button btnGuardar = new Button(this);
        btnGuardar.setText("Guardar Galería");

        btnGuardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        guardarDatosGaleria(descrpcion);
                    }
                });

        layout.addView(btnGuardar);

        return layout;
    }

    private LinearLayout crearSeccionServicios() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(crearTextView("Servicios"));

        Button btnSeleccionarImagen = new Button(this);
        btnSeleccionarImagen.setText("Seleccionar Imagen");
        btnSeleccionarImagen.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        seleccionarImagen();
                    }
                });

        layout.addView(btnSeleccionarImagen);

        EditText nombre = crearEditText("Nombre del Servicio");
        EditText descripcion = crearEditText("Descripción del Servicio");
        EditText precio =
                crearEditText(
                        "Precio del Servicio",
                        InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        layout.addView(nombre);
        layout.addView(descripcion);
        layout.addView(precio);

        Button btnGuardar = new Button(this);
        btnGuardar.setText("Guardar Servicio");

        btnGuardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        guardarDatosServicio(nombre, descripcion, precio);
                    }
                });
        layout.addView(btnGuardar);

        return layout;
    }

    private LinearLayout crearSeccionProductos() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(crearTextView("Productos"));

        Button btnSeleccionarImagen = new Button(this);
        btnSeleccionarImagen.setText("Seleccionar Imagen");
        btnSeleccionarImagen.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        seleccionarImagen();
                    }
                });

        layout.addView(btnSeleccionarImagen);

        EditText nombre = crearEditText("Nombre del Producto");
        EditText descripcion = crearEditText("Descripción del Producto");
        EditText precio =
                crearEditText(
                        "Precio del Producto",
                        InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        layout.addView(nombre);
        layout.addView(descripcion);
        layout.addView(precio);
        Button btnGuardar = new Button(this);
        btnGuardar.setText("Guardar Producto");

        btnGuardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        guardarDatosProducto(nombre, descripcion, precio);
                    }
                });

        layout.addView(btnGuardar);

        return layout;
    }

    // Método para crear un EditText genérico
    private EditText crearEditText(String hint) {
        return crearEditText(hint, InputType.TYPE_CLASS_TEXT);
    }

    private EditText crearEditText(String hint, int inputType) {
        EditText editText = new EditText(this);
        editText.setHint(hint);
        editText.setInputType(inputType);
        editText.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
        return editText;
    }

    private void seleccionarImagen() {
        Intent intent =
                new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    private void seleccionarVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_VIDEO_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri selectedUri = data.getData();

            if (requestCode == REQUEST_IMAGE_PICK) {
                String rutaImagen =
                        guardarArchivoInterno(
                                selectedUri,
                                "imagen_galeria_" + System.currentTimeMillis() + ".jpg");
                if (rutaImagen != null) {
                    imagenUri = Uri.fromFile(new File(rutaImagen));

                } else {
                    Toast.makeText(this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
                }
            } else if (requestCode == REQUEST_VIDEO_PICK) {
                String rutaVideo =
                        guardarArchivoInterno(
                                selectedUri,
                                "video_galeria_" + System.currentTimeMillis() + ".mp4");
                if (rutaVideo != null) {
                    videoUri = Uri.fromFile(new File(rutaVideo));
                    Toast.makeText(this, "Video guardado: " + rutaVideo, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Error al guardar el video", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private String guardarArchivoInterno(Uri uri, String nombreArchivo) {

        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);

            FileOutputStream outputStream = openFileOutput(nombreArchivo, Context.MODE_PRIVATE);

            if (inputStream != null) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                return getFileStreamPath(nombreArchivo).getAbsolutePath();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void guardarDatosGaleria(EditText editTextDescripcion) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String descripcion = editTextDescripcion.getText().toString();

        if (descripcion.isEmpty() || imagenUri == null || videoUri == null) {
            Toast.makeText(
                            this,
                            "Por favor, complete todos los campos y seleccione una imagen y un video.",
                            Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put("descripcion", descripcion);
        values.put("imagen", imagenUri.getPath());
        values.put("video", videoUri.getPath());

        long newRowId = db.insert("galeria", null, values);
        db.close();

        if (newRowId != -1) {
            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarDatosProducto(
            EditText editTextNombre, EditText editTextDescripcion, EditText editTextPrecio) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String nombre = editTextNombre.getText().toString();
        String descripcion = editTextDescripcion.getText().toString();
        String precio = editTextPrecio.getText().toString();

        if (nombre.isEmpty() || descripcion.isEmpty() || precio.isEmpty() || imagenUri == null) {
            Toast.makeText(
                            this,
                            "Por favor, complete todos los campos y seleccione una imagen y un video.",
                            Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        ContentValues values = new ContentValues();

        values.put("imagen", imagenUri.getPath());
        values.put("nombre_producto", nombre);
        values.put("descripcion_producto", descripcion);
        values.put("precio_producto", Double.parseDouble(precio));

        long newRowId = db.insert("productos", null, values);
        db.close();

        if (newRowId != -1) {
            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarDatosAcerca(EditText editTextTitulo, EditText editTextDescripcion) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String titulo = editTextTitulo.getText().toString();
        String descripcion = editTextDescripcion.getText().toString();

        if (titulo.isEmpty() || descripcion.isEmpty() || imagenUri == null) {
            Toast.makeText(
                            this,
                            "Por favor, complete todos los campos y seleccione una imagen y un video.",
                            Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        ContentValues values = new ContentValues();

        values.put("imagen", imagenUri.getPath());
        values.put("titulo", titulo);
        values.put("descripcion", descripcion);

        long newRowId = db.insert("acerca_de", null, values);
        db.close();

        if (newRowId != -1) {
            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarDatosServicio(
            EditText editTextNombre, EditText editTextDescripcion, EditText editTextPrecio) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String nombre = editTextNombre.getText().toString();
        String descripcion = editTextDescripcion.getText().toString();
        String precio = editTextPrecio.getText().toString();

        if (nombre.isEmpty() || descripcion.isEmpty() || precio.isEmpty() || imagenUri == null) {
            Toast.makeText(
                            this,
                            "Por favor, complete todos los campos y seleccione una imagen y un video.",
                            Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        ContentValues values = new ContentValues();

        values.put("imagen", imagenUri.getPath());
        values.put("nombre_servicio", nombre);
        values.put("descripcion_servicio", descripcion);
        values.put("descripcion_servicio", Double.parseDouble(precio));

        long newRowId = db.insert("servicios", null, values);
        db.close();

        if (newRowId != -1) {
            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarDatosContacto(
            EditText editTextTelefono, EditText editTextCorreo, EditText editTextDireccion) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String telefono = editTextTelefono.getText().toString();
        String correo = editTextCorreo.getText().toString();
        String direccion = editTextDireccion.getText().toString();

        if (telefono.isEmpty() || correo.isEmpty() || direccion.isEmpty()) {
            Toast.makeText(
                            this,
                            "Por favor, complete todos los campos y seleccione una imagen y un video.",
                            Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        ContentValues values = new ContentValues();

        values.put("telefono", telefono);
        values.put("correo", correo);
        values.put("direccion", direccion);

        long newRowId = db.insert("contacto", null, values);
        db.close();

        if (newRowId != -1) {
            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarDatosEquipo(
            EditText editTextNombre,
            EditText editTextApellido,
            EditText editTextTelefono,
            EditText editTextCorreo,
            EditText editTextEspecialidad) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String nombre = editTextNombre.getText().toString();
        String apellido = editTextApellido.getText().toString();
        String telefono = editTextTelefono.getText().toString();
        String correo = editTextCorreo.getText().toString();
        String especialidad = editTextEspecialidad.getText().toString();

        if (nombre.isEmpty()
                || apellido.isEmpty()
                || telefono.isEmpty()
                || correo.isEmpty()
                || especialidad.isEmpty()
                || imagenUri == null) {
            Toast.makeText(
                            this,
                            "Por favor, complete todos los campos y seleccione una imagen y un video.",
                            Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        ContentValues values = new ContentValues();

        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("telefono", telefono);
        values.put("correo", correo);
        values.put("especialidad", especialidad);
        values.put("foto", imagenUri.getPath());

        long newRowId = db.insert("equipo", null, values);
        db.close();

        if (newRowId != -1) {
            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para crear un TextView genérico
    private TextView crearTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(18);
        textView.setPadding(0, 16, 0, 8);
        return textView;
    }
}
