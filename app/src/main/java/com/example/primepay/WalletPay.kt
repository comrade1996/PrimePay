package com.example.primepay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import kotlinx.android.synthetic.main.activity_wallet_pay.*
import org.json.JSONObject

class WalletPay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_pay)
        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.phoneLayoutWP, "^[0-9]{9}\$",R.string.err_phone_number)
        val btn_click_me = findViewById(R.id.proceedWP) as Button
        // set on-click listener
        val backButton = findViewById<Button>(R.id.backBbuttonWP)
        val params = JSONObject(getIntent().getStringExtra("data"));
        btn_click_me.setOnClickListener {
            if(mAwesomeValidation.validate()){
                params.put("phonenumber",phoneWP.text.toString())
                Toast.makeText(this, params.toString(), Toast.LENGTH_SHORT).show()
                val intent = Intent(baseContext, CustomKeyboard::class.java)
                intent.putExtra("data", params.toString())

                startActivity(intent)
            }

        }

        backButton.setOnClickListener {
            this.finish()
        }


    }
}
