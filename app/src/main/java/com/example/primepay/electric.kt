package com.example.primepay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle

import kotlinx.android.synthetic.main.activity_electric.*
import kotlinx.android.synthetic.main.activity_electric.paymentMethodSpinner
import org.json.JSONObject

class electric : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electric)

     val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.meterNoLayout,  "^[0-9]{1,13}\$", R.string.err_meter_number)
        mAwesomeValidation.addValidation(this, R.id.amountLayoutEL, "^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,2})?\\s*\$" ,R.string.err_amount)
        val btn_click_me = findViewById(R.id.proceedEL) as Button
        val backButton = findViewById<Button>(R.id.backButtonEL)
        // set on-click listener
        btn_click_me.setOnClickListener {

            if(mAwesomeValidation.validate())
            {
                var params = JSONObject()
                params.put("amount",amountEL.text.toString())
                params.put("meterNumber",meterEL.text.toString())
                println(paymentMethodSpinner.selectedItem.toString())
                if(paymentMethodSpinner.selectedItemPosition == 0)
                {
                    val intent = Intent(baseContext, CardPay::class.java)
                    intent.putExtra("data", params.toString())
                    startActivity(intent)
                }
                if(paymentMethodSpinner.selectedItemPosition == 1)
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
