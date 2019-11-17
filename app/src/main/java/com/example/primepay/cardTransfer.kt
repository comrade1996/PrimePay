package com.example.primepay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.RegexValidator
import android.widget.Button
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate


class cardTransfer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_transfer)
        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.cardNumberLayoutCT,RegexTemplate.NOT_EMPTY, R.string.err_reciveCardNumber)
        mAwesomeValidation.addValidation(this, R.id.amountLayoutCT, RegexTemplate.NOT_EMPTY, R.string.err_amount)
        val btn_click_me = findViewById(R.id.proceedCardTransfer) as Button
        val backButton = findViewById(R.id.backBbuttonCT) as Button
        // set on-click listener
        btn_click_me.setOnClickListener {

            if(mAwesomeValidation.validate())
            {

                val intent = Intent(baseContext, CardPay::class.java)
                intent.putExtra("EXTRA_SESSION_ID", "here")
                startActivity(intent)

            }

        }

        backButton.setOnClickListener {
            this.finish()
        }
    }
}
