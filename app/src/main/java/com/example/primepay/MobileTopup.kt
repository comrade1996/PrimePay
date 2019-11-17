package com.example.primepay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import kotlinx.android.synthetic.main.activity_mobile_topup.*
import kotlinx.android.synthetic.main.activity_purchase.*

class MobileTopup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_topup)
        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.amountLayoutTU, RegexTemplate.NOT_EMPTY, R.string.err_amount)
        mAwesomeValidation.addValidation(this, R.id.phoneNumberTU, RegexTemplate.NOT_EMPTY, R.string.err_amount)
        val btn_click_me = findViewById(R.id.proceedTU) as Button
        // set on-click listener
        val backButton = findViewById(R.id.backButtonTU) as Button

        // set on-click listener
        btn_click_me.setOnClickListener {

            if(mAwesomeValidation.validate())
            {
                println(paymentMethodSpinner.selectedItem.toString())
                if(paymentMethodSpinner.selectedItem.toString() == "Credit Card")
                {
                    val intent = Intent(baseContext, CardPay::class.java)
                    intent.putExtra("EXTRA_SESSION_ID", "here")
                    startActivity(intent)
                }
                if(paymentMethodSpinner.selectedItem.toString() == "Mobile Wallet")
                {
                    val intent = Intent(baseContext, WalletPay::class.java)
                    intent.putExtra("EXTRA_SESSION_ID", "here")
                    startActivity(intent)
                }
            }

        }

        backButton.setOnClickListener {
            this.finish()
        }

    }}
