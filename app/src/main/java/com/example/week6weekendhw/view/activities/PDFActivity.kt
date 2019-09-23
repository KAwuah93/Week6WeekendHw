package com.example.week6weekendhw.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.week6weekendhw.R
import kotlinx.android.synthetic.main.activity_pdf.*

class PDFActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)
        pdfView.fromAsset("sample.pdf").load()

    }
}
