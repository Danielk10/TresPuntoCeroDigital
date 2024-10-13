package com.trespuntocerodigital.notificaciones;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import android.app.NotificationManager;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
      
        // Mostrar la notificación de promoción cuando el `AlarmManager` se active
        NotificationHelper.mostrarNotificacionPromocion(
                context,
                "Promoción del día",
                "Aprovecha un 20% de descuento en todos los servicios.");

        if ("com.trespuntocerodigital.notificaciones.PROMOTION_NOTIFICATION".equals(intent.getAction())) {
            // Lógica para mostrar la notificación de promoción
            NotificationHelper.mostrarNotificacionPromocionPersanslizada(
                    context,
                    "Promoción del día",
                    "Aprovecha un 20% de descuento en todos los servicios.");
        }
    }
}
