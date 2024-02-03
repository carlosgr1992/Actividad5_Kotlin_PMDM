package com.carlos.practica5_pmdm_cgr

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private lateinit var btnColor: Button
    private lateinit var constraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        constraintLayout = findViewById(R.id.constraintLayout)
        btnColor = findViewById(R.id.btnColor)



        btnColor.setOnClickListener {
            // Obtenemos el color actual del fondo
            val currentBackgroundColor = (constraintLayout.background as? ColorDrawable)?.color
            val darkBlueColor = resources.getColor(R.color.dark_blue, theme)
            val lightColor = resources.getColor(R.color.backgroundApp, theme)

            // Compara y cambia el color del fondo
            if (currentBackgroundColor == darkBlueColor) {
                constraintLayout.setBackgroundColor(lightColor)
            } else {
                constraintLayout.setBackgroundColor(darkBlueColor)
            }
        }

        val btnActivity2: Button = findViewById(R.id.btnActivity2)
        btnActivity2.setOnClickListener {
            val intent = Intent(this, Activity2::class.java)
            startActivity(intent)
        }

    }
}
