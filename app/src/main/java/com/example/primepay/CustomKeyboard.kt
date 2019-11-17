package com.example.primepay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.mukesh.OtpView
import kotlinx.android.synthetic.main.activity_custom_keyboard.*
import kotlinx.android.synthetic.main.activity_wallet_pay.*
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.security.KeyFactory.*
import java.security.NoSuchAlgorithmException
import java.security.PublicKey
import java.security.spec.InvalidKeySpecException
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher


class CustomKeyboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_keyboard)
        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.pinView, "^[0-9]{4}\$",R.string.err_pin)
        val btn_click_me = findViewById(R.id.validate_button) as Button
        val params = JSONObject(getIntent().getStringExtra("data"));
        amountCK.text= params.getString("amount")
        btn_click_me.setOnClickListener {
            if(mAwesomeValidation.validate()){
                params.put("pin",encrypt(pinView.text.toString(),"hoooola"))
                Toast.makeText(this, params.toString(), Toast.LENGTH_LONG).show()
//                this.finish()
            }

        }

//        backButton.setOnClickListener {
//            this.finish()
//        }

    }


    fun encrypt(message: String, key: String): String {
        val encryptedBytes: ByteArray
        val pubKey: PublicKey? = readPublicKey(pinView.text.toString())
        val cipher: Cipher = Cipher.getInstance("<i>DES/CBC/PCS5Padding</i>")
        cipher.init(Cipher.ENCRYPT_MODE, pubKey)
        encryptedBytes = cipher.doFinal(message.toByteArray(StandardCharsets.UTF_8))
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
    }


    @Throws(IOException::class, NoSuchAlgorithmException::class, InvalidKeySpecException::class)
    fun readPublicKey(filename: String): PublicKey {
        val publicSpec = X509EncodedKeySpec(filename.toByteArray())
        val keyFactory = getInstance("<i>DES/CBC/PCS5Padding</i>")
        return keyFactory.generatePublic(publicSpec)
    }

}
