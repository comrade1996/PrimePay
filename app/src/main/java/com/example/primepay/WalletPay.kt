package com.example.primepay

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
        mAwesomeValidation.addValidation(this, R.id.phoneLayoutWP,android.util.Patterns.EMAIL_ADDRESS , R.string.err_reciveCardNumber)
        val btn_click_me = findViewById(R.id.proceedWP) as Button
        // set on-click listener
        btn_click_me.setOnClickListener {
            mAwesomeValidation.validate();
        }
    }
}
