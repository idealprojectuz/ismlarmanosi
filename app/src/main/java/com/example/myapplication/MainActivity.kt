package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.models.Names
import com.example.myapplication.models.NamesItem
import com.example.myapplication.models.Properties
import com.example.myapplication.utils.NamesObject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"
    lateinit var namesItem: ArrayList<NamesItem>
    lateinit var ismlarList: ArrayList<Properties>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
/*
        val jsonFileString = getJsonDataFromAsset(applicationContext, "names.json")!!
        Log.i("data", jsonFileString)
        val gson = Gson()
        val lisType = object : TypeToken<Names>() {}.type
        val namesList: Names = gson.fromJson(jsonFileString, lisType)
        namesItem = ArrayList()
        namesList.forEach { it ->
            namesItem.add(it)
        }

        var properties = ArrayList<Properties>()

        namesItem.forEach { it ->
            properties.add(it.properties)
        }
        NamesObject.properties = ArrayList()
        NamesObject.properties.addAll(properties)*/
        var scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale)
        binding.text.startAnimation(scaleAnimation)

        scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                loading()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })


    }

    fun loading() {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "names.json")!!
        //Log.i("data", jsonFileString)
        val gson = Gson()
        val lisType = object : TypeToken<Names>() {}.type
        val namesList: Names = gson.fromJson(jsonFileString, lisType)
        namesItem = ArrayList()
        namesList.forEach { it ->
            namesItem.add(it)
        }

        var properties = ArrayList<Properties>()

        namesItem.forEach { it ->
            properties.add(it.properties)
        }
        NamesObject.properties = ArrayList()
        NamesObject.properties.addAll(properties)
        startActivity(Intent(this@MainActivity, BaseActivity::class.java))
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}