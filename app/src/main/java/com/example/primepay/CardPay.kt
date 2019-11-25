package com.example.primepay

import android.content.Context
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.primepay.api.apiHandler
import com.sunmi.pay.hardware.aidl.AidlConstants
import com.sunmi.pay.hardware.aidlv2.AidlErrorCodeV2
import com.sunmi.pay.hardware.aidlv2.bean.PinPadConfigV2
import com.sunmi.pay.hardware.aidlv2.pinpad.PinPadListenerV2
import com.sunmi.pay.hardware.aidlv2.pinpad.PinPadOptV2
import com.sunmi.pay.hardware.aidlv2.readcard.CheckCardCallbackV2
import com.sunmi.pay.hardware.aidlv2.readcard.ReadCardOptV2
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.json.JSONObject

class CardPay : AppCompatActivity() {

    private var mPinPadOptV2: PinPadOptV2? = null
    private var mReadCardOptV2: ReadCardOptV2? = null

    private lateinit var mLoadingDialog: LoadingActivity
    private var mCardNo: String? = null


    private val PIN_INIT = 1
    private val PIN_CLICK_NUMBER = 2
    private val PIN_CLICK_PIN = 3
    private val PIN_CLICK_CONFIRM = 4
    private val PIN_CLICK_CANCEL = 5
    private val PIN_ERROR = 6


    private val mLooper = Looper.myLooper()
    private val mHandler = object : Handler(mLooper!!) {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                PIN_INIT -> {
                    //dismissLoadingDialog()
                    initPinPad()
                }
                PIN_CLICK_NUMBER -> {
                }
                PIN_CLICK_PIN -> Toast.makeText(applicationContext, "Pin Clicked", Toast.LENGTH_LONG).show()
                PIN_CLICK_CONFIRM -> Toast.makeText(applicationContext, "Confirm Clicked", Toast.LENGTH_LONG).show()
                PIN_CLICK_CANCEL -> Toast.makeText(applicationContext,"user cancel", Toast.LENGTH_LONG).show()
                PIN_ERROR -> Toast.makeText(applicationContext,"error:" + msg.obj + " -- " + msg.arg1, Toast.LENGTH_LONG).show()
            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_pay)

       initView()
       checkCard()
      val params = JSONObject()
            var amount = getIntent().getDoubleExtra("amount",0.0)
        val editor2 = getSharedPreferences("userData", Context.MODE_PRIVATE)

        val backButton = findViewById<Button>(R.id.backBbuttonCP)
        backButton.setOnClickListener {

            params.put("clientId",editor2.getString("clientId","no"))
            params.put("terminalId","19000019")
//            params.put("tranDateTime","111119011852")
            params.put("PAN","6392560096043225")
            params.put("PIN","password")
            params.put("expDate","0112")
            params.put("tranCurrencyCode","SDG")
            params.put("tranAmount",amount)
            params.put("additionalAmount",10)

            var token = editor2.getString("token","no")


            GlobalScope.launch {
                var response: JSONObject?
                val res = async {
            apiHandler.instance.sendcall(this@CardPay,"http://10.0.2.2:8000/api/purchase",params)

                }
//                if(res.await().let { response = it as JSONObject?; it != null  }){
                    println("On Async Block" + res)
                    try {
//                        Looper.prepare()
                        println("On Async Block" + res)

                    }catch (e: Exception){
                        //Looper.prepare()
//                           Toast.makeText(applicationContext, "Exception Catched$e", Toast.LENGTH_LONG).show()
                    }

//                }

            }


            Toast.makeText(this, params.toString(), Toast.LENGTH_LONG).show()
//            this.finish()
        }

    }

    private fun showLoadingDialog(resId: String) {
        runOnUiThread {
            if (mLoadingDialog == null) {
                mLoadingDialog = LoadingActivity(this, resId)
            } else {
                mLoadingDialog.setMessage(resId)
            }
            try {
                mLoadingDialog.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun dismissLoadingDialog() {
        runOnUiThread {
            if (mLoadingDialog != null) {
                try {
                    mLoadingDialog.dismiss()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }


    private fun initView() {
        mPinPadOptV2 = AppClass().mPinPadOptV2
        mReadCardOptV2 = AppClass().mReadCardOptV2
    }

    private fun checkCard() {
        try {
            //showLoadingDialog("Please Swipe")
            Toast.makeText(this, "Start Swiping", Toast.LENGTH_LONG).show()
            val cardType = AidlConstants.CardType.MAGNETIC.value
            Toast.makeText(this, "Card Type : $cardType", Toast.LENGTH_LONG).show()
            mReadCardOptV2!!.checkCard(cardType, mCheckCardCallback, 60)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onBackPressed() {
        cancelCheckCard()
        super.onBackPressed()
    }

    private fun cancelCheckCard() {
        try {
            mReadCardOptV2!!.cancelCheckCard()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun initPinPad() {
        try {
            val pinPadConfig = PinPadConfigV2()
            pinPadConfig.pinPadType = 0
            pinPadConfig.pinType = 0
            pinPadConfig.isOrderNumKey = false
            val panBytes = mCardNo!!.substring(mCardNo!!.length - 13, mCardNo!!.length - 1)
                .toByteArray(charset("US-ASCII"))
            pinPadConfig.pan = panBytes
            pinPadConfig.timeout = 60 * 1000 // input password timeout
            pinPadConfig.pinKeyIndex = 12    // pik index
            pinPadConfig.maxInput = 12
            pinPadConfig.minInput = 0
            mPinPadOptV2!!.initPinPad(pinPadConfig, mPinPadListener)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    private val mPinPadListener = object : PinPadListenerV2.Stub() {

        override fun onPinLength(len: Int) {
            mHandler.obtainMessage(PIN_CLICK_NUMBER, len).sendToTarget()
        }

        override fun onConfirm(i: Int, pinBlock: ByteArray?) {
            if (pinBlock != null) {
                val hexStr = bytes2HexStr(pinBlock)
                mHandler.obtainMessage(PIN_CLICK_PIN, pinBlock).sendToTarget()
            } else {
                mHandler.obtainMessage(PIN_CLICK_CONFIRM).sendToTarget()
            }
        }

        override fun onCancel() {
            mHandler.obtainMessage(PIN_CLICK_CANCEL).sendToTarget()
        }

        override fun onError(code: Int) {
            val msg = AidlErrorCodeV2.valueOf(code).msg
            mHandler.obtainMessage(PIN_ERROR, code, code, msg).sendToTarget()
        }

    }


    private val mCheckCardCallback = object : CheckCardCallbackV2.Stub() {

        @Throws(RemoteException::class)
        override fun findMagCard(bundle: Bundle) {
            val track1 = bundle.getString("TRACK1")
            val track2 = bundle.getString("TRACK2")
            val track3 = bundle.getString("TRACK3")
            runOnUiThread {
                val value = "track1:$track1\ntrack2:$track2\ntrack3:$track3"
            }
            if (track2 != null) {
                val index = track2.indexOf("=")
                if (index != -1) {
                    mCardNo = track2.substring(0, index)
                }
            }
            if (mCardNo != null && mCardNo!!.isNotEmpty()) {
                mHandler.obtainMessage(PIN_INIT).sendToTarget()
            } else {
                Toast.makeText(applicationContext, "Error Getting Card Info", Toast.LENGTH_LONG).show()
            }
        }

        @Throws(RemoteException::class)
        override fun findICCard(atr: String) {

        }

        @Throws(RemoteException::class)
        override fun findRFCard(uuid: String) {

        }

        @Throws(RemoteException::class)
        override fun onError(code: Int, message: String) {
            val error = "onError:$message -- $code"
            Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
            //dismissLoadingDialog()
        }

    }

    fun bytes2HexStr(bytes: ByteArray): String {
        val sb = StringBuilder()
        var temp: String
        for (b in bytes) {
            temp = Integer.toHexString(0xFF and b.toInt()) // check again
            if (temp.length == 1) {
                temp = "0$temp"
            }
            sb.append(temp)
        }
        return sb.toString().toUpperCase()
    }


}
