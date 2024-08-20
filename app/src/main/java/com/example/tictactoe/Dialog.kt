package com.example.tictactoe

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButton
import pl.droidsonroids.gif.GifImageView

object Dialog {

    fun showDialog(context: Context, winner:String,image: Int,mediaPlayer: MediaPlayer,onBottomItemClick: (AlertDialog) -> Unit){

val view =LayoutInflater.from(context).inflate(R.layout.dialog_layout,null,false)
        val dialog = AlertDialog.Builder(context)
            .setView(view)
            .setCancelable(false)
            .create()
        dialog.show()
      // Replace 'your_mp3_file' with your MP3 file name in raw folder
        mediaPlayer?.start()
        val btnPlayAgain=view.findViewById<MaterialButton>(R.id.btnPlayAgain)
        val winnerName=view.findViewById<TextView>(R.id.winnerName)
        val textview=view.findViewById<TextView>(R.id.textView)
        val winnerImage=view.findViewById<ImageView>(R.id.wininingImage)
        val gif=view.findViewById<GifImageView>(R.id.winingGif)
        if (winner.isEmpty()){
            textview.text="DRAW"
            winnerName.text=""
            gif.visibility=View.GONE
        }else{
            textview.text="WINNER"
            winnerName.text=winner
            gif.visibility=View.VISIBLE
        }

        winnerImage.setImageResource(image)
        btnPlayAgain.setOnClickListener{
            onBottomItemClick(dialog)
        }
    }

}