package com.example.week6weekendhw.view.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.week6weekendhw.R
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AlertDialog
import android.os.Handler
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        createNotificationChannel()

    }

    fun onClick(view : View){
        when(view.id){
            R.id.btnSmsTransition -> startActivity(Intent(this, SmsActivity::class.java))
            R.id.btnPDF -> startActivity(Intent(this, PDFActivity::class.java))
            R.id.btnMysteryPopup ->{

                val dialog = AlertDialog.Builder(this).setTitle("3 second Dialog Box")
                    .setMessage("Exiting in 3 seconds, Click button below to create notification")
                dialog.setPositiveButton("Create Notification",
                    DialogInterface.OnClickListener { dialog, whichButton -> exitLauncher() })
                val alert = dialog.create()
                alert.show()

                //code to close the dialogue after some time
                // Hide after some seconds
                val handler = Handler()
                val runnable = Runnable {
                    if (alert.isShowing) {
                        alert.dismiss()
                    }
                }

                alert.setOnDismissListener { handler.removeCallbacks(runnable) }

                handler.postDelayed(runnable, 3000)
            }
        }
    }

    fun exitLauncher(){
        createNotification()
    }

    fun createNotification(){
        val intent = Intent(this, MenuActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this,0, intent, 0)

        val builder = NotificationCompat.Builder(this, "1")
            .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
            .setContentTitle("This is the test attempt")
            .setContentText("This is the body text of the notification")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("MORE BODY TEXT BOYO LETS SEE IT GO"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)


        with(NotificationManagerCompat.from(this)) {
            notify(0, builder.build())
        }
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "menuChannel"
            val descriptiontext = "Idk please just work"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1",name,importance).apply {
                description = descriptiontext
            }

            //registering the channel with the system
            val notificationManager : NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
