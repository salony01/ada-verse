package com.example.adaverse

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val i=Intent(context,LiveTvActivity::class.java)
        intent!!.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context,0,i, PendingIntent.FLAG_IMMUTABLE)
        val builder=NotificationCompat.Builder(context!!,"foxandroid")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("AdaVerse alarm manager")
            .setContentText("Subscribe for more android related content")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.PRIORITY_HIGH)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManager= NotificationManagerCompat.from(context)
       // notificationManager.notify(123,builder.build())
    }
}