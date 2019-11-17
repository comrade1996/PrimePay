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
import org.json.JSONObject


class Purchase : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)


        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.purcheseAmount,"^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,2})?\\s*\$" , R.string.err_amount)

        val btn_click_me = findViewById(R.id.purcheseProceed) as Button
        val backButton = findViewById(R.id.backBbuttonP) as Button

        // set on-click listener
        btn_click_me.setOnClickListener {
                println(amountP.text.toString())
            if(mAwesomeValidation.validate())
            {
                var params = JSONObject()
                params.put("amount",amountP.text.toString())
                println(paymentMethodSpin.selectedItem.toString())
                if(paymentMethodSpin.selectedItem.toString() == "Credit Card")
                {
                    val intent = Intent(baseContext, CardPay::class.java)
                    intent.putExtra("data", params.toString())
                    startActivity(intent)
                }
                if(paymentMethodSpin.selectedItem.toString() == "Mobile Wallet")
                {
                    val intent = Intent(baseContext, WalletPay::class.java)
                    intent.putExtra("data", params.toString())
                    startActivity(intent)
                }
            }

        }

        backButton.setOnClickListener {
            this.finish()
        }



    }
}
