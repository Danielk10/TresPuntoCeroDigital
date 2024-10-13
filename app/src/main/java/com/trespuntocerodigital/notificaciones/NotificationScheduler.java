package com.trespuntocerodigital.notificaciones;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class NotificationScheduler {
    public static void programarNotificacion(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        // Crear una intención para disparar la notificación
        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Establecer un tiempo en el futuro (ejemplo: 1 minuto desde ahora)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);

        // Programar la notificación
        alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_HOUR/240,  // Repite cada hora
                pendingIntent);
    }
}
