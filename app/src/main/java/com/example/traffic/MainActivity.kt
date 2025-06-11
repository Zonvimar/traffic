package com.example.traffic

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var redLight: View
    private lateinit var yellowLight: View
    private lateinit var greenLight: View
    private lateinit var switchButton: Button

    private var currentState = 0
    private val STATE_KEY = "current_state"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redLight = findViewById(R.id.redLight)
        yellowLight = findViewById(R.id.yellowLight)
        greenLight = findViewById(R.id.greenLight)
        switchButton = findViewById(R.id.switchButton)

        if (savedInstanceState != null) {
            currentState = savedInstanceState.getInt(STATE_KEY, 0)
        }

        updateLights()

        switchButton.setOnClickListener {
            when (currentState) {
                0 -> currentState = 1
                1 -> currentState = 2
                2 -> currentState = 3
                3 -> currentState = 0
            }
            updateLights()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_KEY, currentState)
        super.onSaveInstanceState(outState)
    }

    private fun updateLights() {
        setLightColor(redLight, R.color.gray_light)
        setLightColor(yellowLight, R.color.gray_light)
        setLightColor(greenLight, R.color.gray_light)

        when (currentState) {
            0 -> setLightColor(redLight, R.color.red_light)
            1 -> setLightColor(yellowLight, R.color.yellow_light)
            2 -> setLightColor(greenLight, R.color.green_light)
            3 -> setLightColor(yellowLight, R.color.yellow_light)
        }
    }

    private fun setLightColor(light: View, colorResId: Int) {
        val background = light.background.mutate() as GradientDrawable
        background.setColor(ContextCompat.getColor(this, colorResId))
    }
}