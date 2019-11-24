package com.example.primepay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import kotlinx.android.synthetic.main.activity_customs.*
import org.json.JSONObject

class Customs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customs)
        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.bankCodeLayoutCU,  "^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,2})?\\s*\$", R.string.err_bank_code)
        mAwesomeValidation.addValidation(this, R.id.declarantCodeLayoutCU, "^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,2})?\\s*\$" ,R.string.err_declarant_code)
        val btn_click_me = findViewById(R.id.proceedCU) as Button
        val backButton = findViewById<Button>(R.id.backButtonCU)
        // set on-click listener
        btn_click_me.setOnClickListener {

            if(mAwesomeValidation.validate())
            {
                var params = JSONObject()
                params.put("bankCode",bankCodeCU.text.toString())
                params.put("declarantCode",declarantCodetCU.text.toString())
                params.put("payeeId","0010030003")
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
