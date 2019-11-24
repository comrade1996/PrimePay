package com.example.primepay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import kotlinx.android.synthetic.main.activity_e15.*
import org.json.JSONObject

class E15 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e15)
        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.invoiceLayoutE15,"^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,2})?\\s*\$" ,R.string.err_invoice_number)
        mAwesomeValidation.addValidation(this, R.id.phoneNumberLayoutTU,  "^[0-9]{9}\$", R.string.err_phone_number)
        val btn_click_me = findViewById(R.id.proceedE15) as Button
        // set on-click listener
        val backButton = findViewById(R.id.backButtonE15) as Button

        // set on-click listener
        btn_click_me.setOnClickListener {

            var params = JSONObject()
            if(mAwesomeValidation.validate()) {
                println(paymentMethodSpinner.selectedItem.toString())


                when (serviceMethodSpinner.selectedItemPosition) {
                    0 -> params.put("serviceId", "2")
                    1 -> params.put("serviceId", "6")


                }

                params.put("payeeId","0010050001")
                params.put("invoiceNumber",invoiceE15.text.toString())
                params.put("phoneNumber", phoneNumberE15.text.toString())
                if (paymentMethodSpinner.selectedItemPosition == 0) {
                    val intent = Intent(baseContext, CardPay::class.java)
                    intent.putExtra("data", params.toString())
                    startActivity(intent)
                }
                if (paymentMethodSpinner.selectedItemPosition == 1) {
                    val intent = Intent(baseContext, WalletPay::class.java)
                    intent.putExtra("data", params.toString())
                    startActivity(intent)
                }
            }
        }

        backButton.setOnClickListener {
            this.finish()
        }

    }}
