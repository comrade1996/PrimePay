
package com.example.primepay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import kotlinx.android.synthetic.main.activity_purchese_cash_back.*
import org.json.JSONObject

class purcheseCashBack : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchese_cash_back)
        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.amountLayoutPCB, "^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,2})?\\s*\$" , R.string.err_amount)
        mAwesomeValidation.addValidation(this, R.id.backamountLayoutPCB, "^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,2})?\\s*\$" , R.string.err_amount)
        val btn_click_me = findViewById(R.id.proceedPCB) as Button
        val backButton = findViewById<Button>(R.id.backbuttonPCB)
        // set on-click listener
        btn_click_me.setOnClickListener {

            if(mAwesomeValidation.validate())
            {

                var params = JSONObject()
                params.put("amount",amountPCB.text.toString())
                params.put("backamount",backamountPCB.text.toString())
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
