package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.databinding.AboutDialogBinding
import com.example.myapplication.databinding.ActivityBaseBinding
import com.example.myapplication.models.Properties
import com.example.myapplication.utils.NamesObject

class BaseActivity : AppCompatActivity() {
    lateinit var binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        countNames()

        binding.cardBoys.setOnClickListener {
            var intent = Intent(this, NamesActivity::class.java)
            intent.putExtra("gender", "m")
            startActivity(intent)
        }
        binding.cardGirls.setOnClickListener {
            var intent = Intent(this, NamesActivity::class.java)
            intent.putExtra("gender", "f")
            startActivity(intent)
        }
        binding.cardSearch.setOnClickListener {
            var intent = Intent(this, NamesActivity::class.java)
            intent.putExtra("gender", "a")
            startActivity(intent)
        }
        binding.cardLiked.setOnClickListener {
            var intent = Intent(this, NamesActivity::class.java)
            intent.putExtra("gender", "like")
            startActivity(intent)
        }
        binding.cardAbout.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this).create()
            val dialogView = AboutDialogBinding.inflate(layoutInflater, binding.root, false)
            alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            alertDialog.setView(dialogView.root)
            alertDialog.show()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    private fun countNames() {
        var boysCount = NamesObject.boys().size
        var girlsCount = NamesObject.girls().size
        binding.tvCountBoys.text = boysCount.toString()
        binding.tvCountGirls.text = girlsCount.toString()
    }
}