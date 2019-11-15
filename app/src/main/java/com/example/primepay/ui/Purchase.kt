package com.example.primepay.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.example.primepay.R




class Purchase : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)


        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.purcheseAmount, RegexTemplate.NOT_EMPTY, R.string.err_amount)
        val btn_click_me = findViewById(R.id.purcheseProceed) as Button
        // set on-click listener
        btn_click_me.setOnClickListener {
            mAwesomeValidation.validate();
        }


    }
}
