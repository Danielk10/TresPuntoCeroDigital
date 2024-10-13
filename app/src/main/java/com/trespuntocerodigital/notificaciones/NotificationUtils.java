package com.trespuntocerodigital.notificaciones;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class NotificationUtils {
    public static final String CHANNEL_ID = "promociones_channel";

    public static void createNotificationChannel(Context context) {
        // Para Android 8.0 y superior se necesita crear un canal de notificaciones
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Promociones";
            String description = "Canal para promociones de 3.0 Digital";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Registrar el canal con el NotificationManager
            NotificationManager notificationManager =
                    context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
