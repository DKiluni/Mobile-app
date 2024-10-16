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

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextMain)
        val sendButton: Button = findViewById(R.id.main_send_button)

        sendButton.setOnClickListener {
            val message = editText.text.toString()
            val messageUri = Uri.parse("message:$message")
            val intent = Intent(this, SecondActivity::class.java).apply {
                data = messageUri
            }
            startActivityForResult(intent, REQUEST_CODE)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            data?.let {
                val reply = it.getStringExtra("reply")
                val repliedMessageView: TextView = findViewById(R.id.repliedMessage)
                repliedMessageView.text = "Reply: $reply"
            }
        }
    }
}


