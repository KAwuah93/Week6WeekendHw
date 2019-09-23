package com.example.week6weekendhw.view.activities

import android.app.LauncherActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.week6weekendhw.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Using the handler to delay the Toast
        Handler().postDelayed({
            Log.d("DELAY", "HERE IS THE DELAY MESSAGE")
            Toast.makeText(this,"3 seconds to mars boy",Toast.LENGTH_LONG).show()

            //Single line of the activity and the Intent
            startActivity(Intent(this , MenuActivity::class.java))
        },3000)



    }
}
