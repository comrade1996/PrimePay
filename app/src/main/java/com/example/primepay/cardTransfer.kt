package com.example.primepay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.RegexValidator
import android.widget.Button
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import kotlinx.android.synthetic.main.activity_card_transfer.*
import org.json.JSONObject


class cardTransfer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_transfer)
        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.cardNumberLayoutCT,"^[0-9]{16,19}\$", R.string.err_reciveCardNumber)
        mAwesomeValidation.addValidation(this, R.id.amountLayoutCT, "^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,2})?\\s*\$" ,R.string.err_amount)
        val btn_click_me = findViewById(R.id.proceedCardTransfer) as Button
        val backButton = findViewById(R.id.backBbuttonCT) as Button
        var params = JSONObject()
        // set on-click listener
        btn_click_me.setOnClickListener {


            if(mAwesomeValidation.validate())
            {
                params.put("amount",amountCT.text.toString())
                params.put("cardNumber",cardNUmberCT.text.toString())
                val intent = Intent(baseContext, CardPay::class.java)
                intent.putExtra("data", params.toString())
                startActivity(intent)

            }

        }

        backButton.setOnClickListener {
            this.finish()
        }
    }
}
