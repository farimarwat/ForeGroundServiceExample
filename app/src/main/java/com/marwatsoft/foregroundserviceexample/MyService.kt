package com.marwatsoft.foregroundserviceexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import androidx.core.app.NotificationCompat

class MyService : Service() {
    val CHANNEL_ID = "SimpleID"
    lateinit var mPlayer:MediaPlayer
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        val intent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,0)
        val notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Testing")
            .setContentText("Service is running")
            .setSmallIcon(R.drawable.ic_baseline_email_24)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1,notification)
        mPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI)
        mPlayer.isLooping = true
        mPlayer.start()
        return START_STICKY
    }

    override fun onDestroy() {
        stopForeground(true)
        stopSelf()
        mPlayer.stop()
        super.onDestroy()
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
    fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID, "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }
}