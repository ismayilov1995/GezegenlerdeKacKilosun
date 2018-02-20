package com.example.ismayil.gezegenlerdekackilosun

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val kG_TO_POUND = 2.20462
    private val pound_to_kg = 0.453592
    val planetArray = listOf(0.38, 0.91, 0.38, 2.34, 1.06, 0.92, 1.19)
    val planetNameArray = listOf("Mercury", "Venus", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune")
    var arrayPos = 0
    var userWeightInPound: Double = 0.0
    var weightInPlanetPound: Double = 0.0
    var weightInPlanetKG: Double = 0.0
    var userWeightInKg: Editable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        userWeightInKg = editText.text

        val mercury = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.mercury)
        val venus = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.venus)
        val mars = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.mars)
        val jupiter = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.jupiter)
        val saturn = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.saturn)
        val uranus = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.uranus)
        val neptune = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.neptune)

        val planetImageArray = ArrayList<Bitmap>()
        planetImageArray.add(mercury)
        planetImageArray.add(venus)
        planetImageArray.add(mars)
        planetImageArray.add(jupiter)
        planetImageArray.add(saturn)
        planetImageArray.add(uranus)
        planetImageArray.add(neptune)



        //Hocam ben burada range 0..7 kullaninca hata veriyordu nedenini anlayamadim

        ivNext.setOnClickListener {
            if (arrayPos in 0..5 && !userWeightInKg.isNullOrEmpty()) {
                arrayPos++
                imageView.setImageBitmap(planetImageArray[arrayPos])
                tVPlanet.text = planetNameArray[arrayPos]
                avtomatikHesabla()
            } else if (userWeightInKg.isNullOrEmpty()) {
                ifEditTextIsEmpt()
            }
        }

        //Ve burada 7..1 e yazmak zorunda kaldim bunu da deneme yanilma yontemiyle yaptim. nedenini anlayamadim
        ivBack.setOnClickListener {
            if (arrayPos in 7 downTo 1 && !userWeightInKg.isNullOrEmpty()) {
                arrayPos--
                imageView.setImageBitmap(planetImageArray[arrayPos])
                tVPlanet.text = planetNameArray[arrayPos]
                avtomatikHesabla()
            } else if (userWeightInKg.isNullOrEmpty()) {
                ifEditTextIsEmpt()
            }
        }



        hesabla.setOnClickListener {
            if (!userWeightInKg.isNullOrEmpty())
                secilenPlanetiHesabla()
            else ifEditTextIsEmpt()
        }

        checkBox.setOnClickListener {
            if (checkBox.isChecked) {
                hesabla.visibility = View.INVISIBLE
            } else {
                hesabla.visibility = View.VISIBLE
            }
        }

    }

    fun avtomatikHesabla() {
        if (checkBox.isChecked) {
            secilenPlanetiHesabla()
        } else {
            //nothing
        }
    }

    fun ifEditTextIsEmpt() {
        Toast.makeText(this, "YUXARIYA KILONUZU GIRIN", Toast.LENGTH_SHORT).show()
    }

    fun secilenPlanetiHesabla() {
        userWeightInPound = kgToPound(userWeightInKg.toString().toDouble())
        weightInPlanetPound = userWeightInPound * planetArray[arrayPos]
        weightInPlanetKG = poundToKg(weightInPlanetPound)
        tvResult.text = weightInPlanetKG.formatla(2).toString()
    }


    fun kgToPound(kg: Double): Double {
        return kg * kG_TO_POUND
    }

    fun poundToKg(pound: Double): Double {
        return pound * pound_to_kg
    }

    fun Double.formatla(kacTaneRakam: Int) = java.lang.String.format("%.${kacTaneRakam}f", this)
}

