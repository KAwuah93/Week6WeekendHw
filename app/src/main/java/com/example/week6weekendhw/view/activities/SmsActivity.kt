package com.example.week6weekendhw.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week6weekendhw.R
import android.telephony.SmsManager
import android.Manifest
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_sms.*
import android.widget.Toast

class SmsActivity : AppCompatActivity() {

    //values for the permission checks
    val MY_PERMISSIONS_REQUEST_SEND_SMS =0
    val MY_PERMISSION_READ_PHONE_STATE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)
    }
    fun sendMessage(view: View){
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(etPhoneNumber.text.toString(), null, etTextBody.text.toString(), null, null)
    }
     fun sendSMSMessage(view: View) {
            var phoneNo = etPhoneNumber.getText().toString()
            var message = etTextBody.getText().toString()

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                //if we have no permissions for SMS
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),
                        MY_PERMISSIONS_REQUEST_SEND_SMS)
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.SEND_SMS),
                        MY_PERMISSIONS_REQUEST_SEND_SMS
                    )
                }
            }else{
                if (MY_PERMISSION_READ_PHONE_STATE != PackageManager.PERMISSION_GRANTED) {
                    val smsManager = SmsManager.getDefault()
                    smsManager.sendTextMessage(etPhoneNumber.text.toString(), null, etTextBody.text.toString(), null, null)
                    Toast.makeText(this,"SMS sent", Toast.LENGTH_LONG).show()

                }
            }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray){
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_SEND_SMS -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (MY_PERMISSION_READ_PHONE_STATE != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_PHONE_STATE), MY_PERMISSION_READ_PHONE_STATE)
                    } else {
                        ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.READ_PHONE_STATE), MY_PERMISSION_READ_PHONE_STATE)
                    }
                } else {
                    Toast.makeText(applicationContext,"SMS failed, please try again.", Toast.LENGTH_LONG).show()
                    return
                }
            }
            MY_PERMISSION_READ_PHONE_STATE ->{
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(etPhoneNumber.text.toString(), null, etTextBody.text.toString(), null, null)
                Toast.makeText(
                    applicationContext, "SMS sent.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
