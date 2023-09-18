package com.gilarajip.mypekoapi.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gilarajip.mypekoapi.R
import com.gilarajip.mypekoapi.ui.list.PokemonListActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            lifecycleScope.launch {
                delay(1000)
                Intent(this@MainActivity, PokemonListActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }
        }
    }
}