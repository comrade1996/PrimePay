package com.example.primepay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CardPay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_pay)


        val backButton = findViewById<Button>(R.id.backBbuttonCP)
        backButton.setOnClickListener {
            this.finish()
        }
    }

}
