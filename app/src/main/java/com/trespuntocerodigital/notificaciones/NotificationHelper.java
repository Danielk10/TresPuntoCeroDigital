package com.trespuntocerodigital.notificaciones;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.trespuntocerodigital.tresdigital.MainActivity;
import com.trespuntocerodigital.tresdigital.R;
import com.trespuntocerodigital.graficos.Textura2D;

public class NotificationHelper {

    public static void mostrarNotificacionPromocion(
            Context context, String titulo, String mensaje) {

        // Crear la intención que abrirá la app cuando el usuario toque la notificación
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Crear la notificación
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, NotificationUtils.CHANNEL_ID)
                        .setSmallIcon(
                                R.drawable.ic_launcher_foreground) // Cambia este ícono según sea
                        // necesario
                        .setLargeIcon(
                                new Textura2D(
                                                MainActivity.recurso
                                                        .cargarTextura("logo.jpg")
                                                        .getBipmap(),
                                                150,
                                                100)
                                        .getBipmap())
                        .setContentTitle(titulo)
                        .setContentText(mensaje)
                        .setStyle(
                                new NotificationCompat.BigPictureStyle()
                                        .bigPicture(
                                                new Textura2D(
                                                                MainActivity.recurso
                                                                        .cargarTextura("logo.jpg")
                                                                        .getBipmap(),
                                                                300,
                                                                250)
                                                        .getBipmap()))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true); // Elimina la notificación cuando se toca

        // Mostrar la notificación
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    public static void mostrarNotificacionPromocionPersanslizada(
            Context context, String titulo, String mensaje) {

        // Crear la intención que abrirá la app cuando el usuario toque la notificación
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, NotificationUtils.CHANNEL_ID)
                        .setLargeIcon(
                                new Textura2D(
                                                MainActivity.recurso
                                                        .cargarTextura("logo.jpg")
                                                        .getBipmap(),
                                                150,
                                                100)
                                        .getBipmap())
                        .setContentTitle(titulo)
                        .setContentText(mensaje)
                        .setStyle(
                                new NotificationCompat.BigPictureStyle()
                                        .bigPicture(
                                                new Textura2D(
                                                                MainActivity.recurso
                                                                        .cargarTextura("logo.jpg")
                                                                        .getBipmap(),
                                                                300,
                                                                250)
                                                        .getBipmap()))
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true); // Elimina la notificación cuando se toca

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(1, builder.build());
        }
    }
}
