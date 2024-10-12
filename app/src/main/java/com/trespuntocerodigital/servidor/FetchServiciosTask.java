package com.trespuntocerodigital.servidor;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// AsyncTask para realizar la solicitud HTTP
		public class FetchServiciosTask extends AsyncTask<String, Void, String> {
		  
		  private Context contexto;

public FetchServiciosTask(Context contexto){
  
  this.contexto = contexto;
  
  
  
  
}
			@Override
			protected String doInBackground(String... urls) {
				StringBuilder result = new StringBuilder();
				try {
					URL url = new URL(urls[0]);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.connect();

					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String line;
					while ((line = reader.readLine()) != null) {
						result.append(line);
					}
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
				return result.toString();
			}

			@Override
			protected void onPostExecute(String result) {
				if (result != null) {
					try {
						// Parsear JSON
						JSONArray jsonArray = new JSONArray(result);
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject servicio = jsonArray.getJSONObject(i);
							String nombre = servicio.getString("nombre");
							String descripcion = servicio.getString("descripcion");

							// Aquí puedes actualizar la UI con los datos
							// Ejemplo: mostrar en el TextView
							Toast.makeText(contexto, nombre + ": " + descripcion, Toast.LENGTH_LONG).show();
						}
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(contexto, "Error al procesar datos", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(contexto, "Error de conexión", Toast.LENGTH_SHORT).show();
				}
			}
		}