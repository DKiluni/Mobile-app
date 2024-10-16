package com.example.assignment1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment1.R

class SecondActivity : AppCompatActivity() {
    private lateinit var editText: EditText // Declare editText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val receivedMessageView: TextView = findViewById(R.id.receivedMessage)
        editText = findViewById(R.id.editTextSecond)
        val sendButton: Button = findViewById(R.id.sendButtonSecond)

        // Get the URI from the intent
        intent?.data?.let { data: Uri ->
            val message = data.schemeSpecificPart // Extract the message
            receivedMessageView.text = "Received: $message"
        }

        sendButton.setOnClickListener {
            val replyMessage = editText.text.toString()
            // Sending the data back to MainActivity using intent extras
            val replyIntent = Intent().apply {
                putExtra("reply", replyMessage) // Using intent extras to send data
            }
            startActivity( replyIntent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}