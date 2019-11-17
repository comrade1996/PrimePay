package com.example.primepay.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.example.primepay.CardPay
import com.example.primepay.R
import com.example.primepay.WalletPay
import kotlinx.android.synthetic.main.activity_purchase.*


class Purchase : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)


        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.purcheseAmount," ^(0*[1-9][0-9]*(\\.[0-9]+)?|0+\\.[0-9]*[1-9][0-9]*)\$" , R.string.err_amount)
        val btn_click_me = findViewById(R.id.purcheseProceed) as Button
        val backButton = findViewById(R.id.backBbuttonP) as Button

        // set on-click listener
        btn_click_me.setOnClickListener {

            if(mAwesomeValidation.validate())
            {
                println(paymentMethodSpin.selectedItem.toString())
                if(paymentMethodSpin.selectedItem.toString() == "Credit Card")
                {
                    val intent = Intent(baseContext, CardPay::class.java)
                    intent.putExtra("EXTRA_SESSION_ID", "here")
                    startActivity(intent)
                }
                if(paymentMethodSpin.selectedItem.toString() == "Mobile Wallet")
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



    }
}
