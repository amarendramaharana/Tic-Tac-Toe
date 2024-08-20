package com.example.tictactoe

import android.content.Intent
import android.content.res.ColorStateList
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.ToneGenerator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SoundEffectConstants
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tictactoe.databinding.ActivityGameBinding
import com.google.android.material.button.MaterialButton

class GameActivity : AppCompatActivity() {
    lateinit var gameBinding: ActivityGameBinding
    private lateinit var player1: String
    private lateinit var player2: String
    lateinit var btn1: MaterialButton
    lateinit var btn2: MaterialButton
    lateinit var btn3: MaterialButton
    lateinit var btn4: MaterialButton
    lateinit var btn5: MaterialButton
    lateinit var btn6: MaterialButton
    lateinit var btn7: MaterialButton
    lateinit var btn8: MaterialButton
    lateinit var btn9: MaterialButton
    private var count = 0
    private lateinit var winner: String
    private lateinit var toneGenerator: ToneGenerator
    private var mediaPlayer: MediaPlayer? = null
    private var buttonCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameBinding = ActivityGameBinding.inflate(layoutInflater)
        toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        mediaPlayer = MediaPlayer.create(this, R.raw.firecracker)
        setContentView(gameBinding.root)
        receivedBundle()
        onClickListener()
        gameBinding.player1Layout.setBackgroundResource(R.drawable.active_bg)
        gameBinding.player1.text = player1
        gameBinding.player2.text = player2
    }

    private fun onClickListener() {
        gameBinding.btnHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        gameBinding.btnExit.setOnClickListener {
            finish()
        }
        gameBinding.btnrReStart.setOnClickListener {
            resetGame()
        }
    }

    private fun receivedBundle() {
        player1 = intent.getStringExtra("PLAYER1").toString()
        player2 = intent.getStringExtra("PLAYER2").toString()
        winner = player1
    }

    fun Check(view: View) {
        btn1 = gameBinding.btn1
        btn2 = gameBinding.btn2
        btn3 = gameBinding.btn3
        btn4 = gameBinding.btn4
        btn5 = gameBinding.btn5
        btn6 = gameBinding.btn6
        btn7 = gameBinding.btn7
        btn8 = gameBinding.btn8
        btn9 = gameBinding.btn9

        val currentButton = view as Button
        toneGenerator.startTone(ToneGenerator.TONE_CDMA_CONFIRM, 200) // 200ms tone
        if (currentButton.text.equals("")) {
            buttonCount++
            if (count == 0) {
                currentButton.text = "X"
                currentButton.setTextColor(resources.getColor(R.color.btn_color))
                count = 1
                winner = player1
                updateActiveState()
            } else {
                currentButton.text = "O"
                currentButton.setTextColor(resources.getColor(R.color.yelllow))
                winner = player2
                count = 0
                updateActiveState()
            }
            if (!btn1.text.equals("") && btn1.text.equals(btn2.text) && btn2.text.equals(btn3.text)) {
                declareWinner(winner, false)
            } else if (!btn4.text.equals("") && btn4.text.equals(btn5.text) && btn5.text.equals(btn6.text)) {
                declareWinner(winner, false)
            } else if (!btn7.text.equals("") && btn7.text.equals(btn8.text) && btn8.text.equals(btn9.text)) {
                declareWinner(winner, false)
            } else if (!btn1.text.equals("") && btn1.text.equals(btn4.text) && btn4.text.equals(btn7.text)) {
                declareWinner(winner, false)
            } else if (!btn2.text.equals("") && btn2.text.equals(btn5.text) && btn5.text.equals(btn8.text)) {
                declareWinner(winner, false)
            } else if (!btn2.text.equals("") && btn2.text.equals(btn5.text) && btn5.text.equals(btn8.text)) {
                declareWinner(winner, false)
            } else if (!btn3.text.equals("") && btn3.text.equals(btn6.text) && btn6.text.equals(btn9.text)) {
                declareWinner(winner, false)
            } else if (!btn1.text.equals("") && btn1.text.equals(btn5.text) && btn5.text.equals(btn9.text)) {
                declareWinner(winner, false)
            } else if (!btn3.text.equals("") && btn3.text.equals(btn5.text) && btn5.text.equals(btn7.text)) {
                declareWinner(winner, false)
            } else if (buttonCount == 9) {
                declareWinner("", true)
            }
        }

    }

    private fun updateActiveState() {
        if (count == 0) {
            gameBinding.player1Layout.setBackgroundResource(R.drawable.active_bg)
            gameBinding.player2Layout.setBackgroundResource(R.drawable.inactive_bg)
            gameBinding.player1Logo.setBackgroundResource(R.drawable.athlete)
            gameBinding.player2Logo.setBackgroundResource(R.drawable.inactive_athlete)
            gameBinding.player1.setTextColor(resources.getColor(R.color.yelllow))
            gameBinding.player2.setTextColor(resources.getColor(R.color.inactive_text_color))
            gameBinding.player1Value.setTextColor(resources.getColor(R.color.yelllow))
            gameBinding.player2Value.setTextColor(resources.getColor(R.color.inactive_text_color))


        } else {
            gameBinding.player1Layout.setBackgroundResource(R.drawable.inactive_bg)
            gameBinding.player2Layout.setBackgroundResource(R.drawable.active_bg)
            gameBinding.player1Logo.setBackgroundResource(R.drawable.inactive_athlete)
            gameBinding.player2Logo.setBackgroundResource(R.drawable.athlete)
            gameBinding.player1.setTextColor(resources.getColor(R.color.inactive_text_color))
            gameBinding.player2.setTextColor(resources.getColor(R.color.yelllow))
            gameBinding.player2Value.setTextColor(resources.getColor(R.color.yelllow))
            gameBinding.player1Value.setTextColor(resources.getColor(R.color.inactive_text_color))
        }
    }

    private fun declareWinner(winner: String, draw: Boolean) {
        var winingImage = R.drawable.trophy
        if (draw) {
            winingImage = R.drawable.sad
        }
        Dialog.showDialog(this, winner, winingImage, mediaPlayer!!) { dialog ->
            resetGame()
            dialog.dismiss()
            /* if (mediaPlayer!!.isPlaying){
                 mediaPlayer!!.stop()
             }*/

        }

    }

    private fun resetGame() {
        if (count != 0) {
            btn1.text = ""
            btn2.text = ""
            btn3.text = ""
            btn4.text = ""
            btn5.text = ""
            btn6.text = ""
            btn7.text = ""
            btn8.text = ""
            btn9.text = ""
        }
        count = 0
        buttonCount = 0
        updateActiveState()
    }

    override fun onBackPressed() {

    }

    override fun onDestroy() {
        super.onDestroy()
        toneGenerator.release()
        releaseMediaPlayer()
    }

    private fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}