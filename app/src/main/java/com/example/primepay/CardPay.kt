package com.example.primepay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import org.json.JSONObject

class CardPay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_pay)

        val params = JSONObject(getIntent().getStringExtra("data"));
        val backButton = findViewById<Button>(R.id.backBbuttonCP)
        backButton.setOnClickListener {
            params.put("cardnumber","123456789")
            params.put("expdate","01/20")
            Toast.makeText(this, params.toString(), Toast.LENGTH_SHORT).show()
            this.finish()
        }
    }

}
