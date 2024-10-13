package com.trespuntocerodigital.notificaciones;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.trespuntocerodigital.digital.MainActivity;
import com.trespuntocerodigital.tresdigital.R;

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
        /*
                // Crear RemoteViews programáticamente
                RemoteViews notificationLayout = new RemoteViews(context.getPackageName(), 0);
                // Configurar y añadir el ImageView
                int imageViewId = View.generateViewId();
                notificationLayout.setImageViewBitmap(
                        imageViewId, MainActivity.recurso.cargarTextura("logo.jpg").getBipmap());

                // Configurar y añadir el TextView para el título
                int titleTextViewId = View.generateViewId();
                notificationLayout.setTextViewText(titleTextViewId, "¡Promoción Especial!");
                notificationLayout.setTextColor(titleTextViewId, Color.BLACK);
                notificationLayout.setTextViewTextSize(titleTextViewId, 0, 18);

                // Configurar y añadir el TextView para el mensaje
                int messageTextViewId = View.generateViewId();
                notificationLayout.setTextViewText(
                        messageTextViewId, "No te pierdas nuestra última oferta.");
                notificationLayout.setTextColor(messageTextViewId, Color.GRAY);
                notificationLayout.setTextViewTextSize(messageTextViewId, 0, 14);
        */
        // Crear la notificación
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, NotificationUtils.CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher) // Cambia este ícono según sea
                        // necesario
                        .setLargeIcon(MainActivity.recurso.cargarTextura("logo.jpg").getBipmap())
                        .setContentTitle(titulo)
                        .setContentText(mensaje)
                        /*  .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                                                .setCustomContentView(notificationLayout)
                                                .setCustomBigContentView(
                                                        notificationLayout) // Muestra el diseño personalizado en modo
                                                // expandido
                        */ .setStyle(
                                new NotificationCompat.BigPictureStyle()
                                        .bigPicture(
                                                MainActivity.recurso
                                                        .cargarTextura("logo.jpg")
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
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(titulo)
                        .setContentText(mensaje)
                        .setStyle(
                                new NotificationCompat.BigPictureStyle()
                                        .bigPicture(
                                                MainActivity.recurso
                                                        .cargarTextura("logo.jpg")
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
