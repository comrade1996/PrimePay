package com.example.primepay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import kotlinx.android.synthetic.main.activity_mohe.*
import org.json.JSONObject

class MOHE : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mohe)
        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.seatNumberLayoutMOHE, "^\\s*(?=.*[1-9])\\d*(?:\\.\\d{1,2})?\\s*\$" ,R.string.err_seat_number)
        val btn_click_me = findViewById(R.id.proceedMOHE) as Button
        val backButton = findViewById<Button>(R.id.backButtonMOHE)
        // set on-click listener
        btn_click_me.setOnClickListener {

            if(mAwesomeValidation.validate())
            {
                println("sssaasaa")
                println(courseIdMethodSpinner.selectedItem.toString())
                var params = JSONObject()
                params.put("seatNumber",seatNumberMOHE.text.toString())
                params.put("courseId",getCourseId(courseIdMethodSpinner.selectedItemPosition.toString().toInt()))
                params.put("formKind",getFormKind(formKindMethodSpinner.selectedItemPosition.toString().toInt()))

                println(paymentMethodSpinner.selectedItem.toString())
                if(paymentMethodSpinner.selectedItemPosition == 0)
                {
                    val intent = Intent(this, CardPay::class.java)
                    intent.putExtra("data", params.toString())
                    startActivity(intent)
                }
                if(paymentMethodSpinner.selectedItemPosition == 1)
                {
                    val intent = Intent(this, WalletPay::class.java)
                    intent.putExtra("data", params.toString())
                    startActivity(intent)
                }
            }

        }

        backButton.setOnClickListener {
            this.finish()
        }
    }

    fun getCourseId(selectedCourseId: Int):Int
    {
        when(selectedCourseId) {
            else -> return (selectedCourseId + 1)
        }
    }

    fun getFormKind(selectedFormKind:Int):Number
    {
        when(selectedFormKind) {
            0,1,2-> return (selectedFormKind + 1)
            3,4,5,6 -> return (selectedFormKind + 3)
            else -> return (1)
        }
    }

}