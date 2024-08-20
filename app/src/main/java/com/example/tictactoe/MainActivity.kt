package com.example.tictactoe

import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SoundEffectConstants
import android.widget.Toast
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.btnStart.setOnClickListener {
            if (mainBinding.player1.text?.isEmpty()!!) {
                Toast.makeText(this, getString(R.string.enter_player1_name), Toast.LENGTH_SHORT).show()
            }
           else if (mainBinding.player2.text?.isEmpty()!!) {
                Toast.makeText(this, getString(R.string.enter_player2_name), Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("PLAYER1", mainBinding.player1.text.toString())
                intent.putExtra("PLAYER2", mainBinding.player2.text.toString())
                startActivity(intent)
                finish()

            }

        }
    }

}