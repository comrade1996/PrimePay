package com.example.primepay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_electric.*

class electric : AppCompatActivity() {

        private var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electric)

     val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.meterNoLayout, RegexTemplate.NOT_EMPTY, R.string.err_amount)
        mAwesomeValidation.addValidation(this, R.id.amountEL, RegexTemplate.NOT_EMPTY, R.string.err_amount)
        val btn_click_me = findViewById(R.id.proceedEL) as Button
        val backButton = findViewById<Button>(R.id.backButtonEL)
        // set on-click listener
        btn_click_me.setOnClickListener {

            if(mAwesomeValidation.validate())
            {
                println(paymentMethodSpinner.selectedItem.toString())
                if(paymentMethodSpinner.selectedItem.toString() == "Credit Card")
                {
                    val intent = Intent(baseContext, CardPay::class.java)
                    intent.putExtra("EXTRA_SESSION_ID", "here")
                    startActivity(intent)
                }
                if(paymentMethodSpinner.selectedItem.toString() == "Mobile Wallet")
                {
                    val intent = Intent(baseContext, WalletPay::class.java)
                    intent.putExtra("EXTRA_SESSION_ID", "here")
                    startActivity(intent)
                }
            }

        }

        backButton.setOnClickListener {
            this.finish()
        }
        }
//
//    override fun onStart() {
//        super.onStart()
//        validateFields()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        if(!disposable.isDisposed) disposable.clear()
//    }
//
//    private fun validateFields(){
//       with(disposable){
//           clear()
//           add(validateInput(meterAmount, amountLayoutCT, { isAmtValid(amountLayoutCT.text.toString())}))
//           add(validateInput(meterNoLayout, meterNo, { isMeterValid(meterNo.text.toString()) }))
//       }
//    }
//
//    private fun isAmtValid(s: String){
//        if(s.isEmpty()){
//            meterAmount.isErrorEnabled = true
//            amountLayoutCT.error = getString(R.string.emptyAmt)
//        }else{
//            meterAmount.isErrorEnabled = false
//        }
//    }
//
//    private fun isMeterValid(s: String){
//        if(s.length < 8){
//            meterNoLayout.isErrorEnabled = true
//            meterNo.error = getString(R.string.meterNoLength)
//        }else{
//            meterNoLayout.isErrorEnabled = false
//        }
//    }
//
//    inline fun validateInput(inputLayout: TextInputLayout, inputView: TextView, crossinline body: () -> Unit): Disposable {
//        return RxView.focusChanges(inputView)
//            .skipInitialValue() // Listen for focus events.
//            .map {
//                if (!it) { // If view lost focus, lambda (our check logic) should be applied.
//                    body()
//                }
//                return@map it
//            }
//            .flatMap { hasFocus ->
//                return@flatMap RxTextView.textChanges(inputView)
//                    .skipInitialValue()
//                    .map {
//                        if (hasFocus && inputLayout.isErrorEnabled) inputLayout.isErrorEnabled = false
//                    } // Disable error when user typing.
//                    .skipWhile({ hasFocus }) // Don't react on text change events when we have a focus.
//                    .doOnEach { body() }
//            }
//            .subscribe { }
//    }

}
