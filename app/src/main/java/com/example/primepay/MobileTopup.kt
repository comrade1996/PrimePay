package com.example.primepay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate

class MobileTopup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_topup)
        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.amountLayoutTU, RegexTemplate.NOT_EMPTY, R.string.err_amount)
        mAwesomeValidation.addValidation(this, R.id.phoneNumberTU, RegexTemplate.NOT_EMPTY, R.string.err_amount)
        val btn_click_me = findViewById(R.id.proceedTU) as Button
        // set on-click listener
        btn_click_me.setOnClickListener {
            mAwesomeValidation.validate();
    }
}}
