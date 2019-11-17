package com.example.primepay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate

class WalletPay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_pay)
        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.phoneLayoutWP,android.util.Patterns.PHONE , R.string.phone_number)
        val btn_click_me = findViewById(R.id.proceedWP) as Button
        // set on-click listener
        val backButton = findViewById<Button>(R.id.backBbuttonWP)
        btn_click_me.setOnClickListener {
            if(mAwesomeValidation.validate()){
                val intent = Intent(baseContext, CustomKeyboard::class.java)
                intent.putExtra("EXTRA_SESSION_ID", "here")
                startActivity(intent)
            }

        }

        backButton.setOnClickListener {
            this.finish()
        }


    }
}
