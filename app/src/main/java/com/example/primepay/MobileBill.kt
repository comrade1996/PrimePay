package com.example.primepay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import kotlinx.android.synthetic.main.activity_mobile_bill.*
import kotlinx.android.synthetic.main.activity_purchase.*
import org.json.JSONObject

class MobileBill : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_bill)
        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.amountLayoutPB,"^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,2})?\\s*\$" ,R.string.err_amount)
        mAwesomeValidation.addValidation(this, R.id.phoneNumberLayoutBP, "^[0-9]{9}\$",R.string.err_phone_number)
        val btn_click_me = findViewById(R.id.proceedBP) as Button
        val backButton = findViewById(R.id.backButtonBP) as Button

        // set on-click listener
        btn_click_me.setOnClickListener {

            if(mAwesomeValidation.validate())
            {

                var params = JSONObject()

                println(paymentMethodSpinner.selectedItem.toString())


                when (telecomMethodSpinner.selectedItemPosition) {
                    0 -> params.put("PayeeId","1000001")
                    1 -> params.put("PayeeId","1000001")
                    2 -> params.put("PayeeId","1000001")

                }
                params.put("amount",amountBP.text.toString())
                params.put("phoneNumber",phoneNumberBP.text.toString())
                if(paymentMethodSpinner.selectedItemPosition == 0)
                {11
                    val intent = Intent(baseContext, CardPay::class.java)
                    intent.putExtra("data",params.toString())
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
