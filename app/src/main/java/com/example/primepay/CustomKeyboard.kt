package com.example.primepay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import kotlinx.android.synthetic.main.activity_custom_keyboard.*
import org.jpos.security.EncryptedPIN
import org.json.JSONObject
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


class CustomKeyboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_keyboard)
        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.pinView, "^[0-9]{4}\$", R.string.err_pin)
        val btn_click_me = findViewById<Button>(R.id.validate_button)
        val params = JSONObject(getIntent().getStringExtra("data"))
        amountCK.text = params.getString("amount")
        btn_click_me.setOnClickListener {
            if (mAwesomeValidation.validate()) {
                //var en = EncryptedPIN()
                // params.put("pin",encrypt(pinView.text.toString(),"hoooola"))
                //Toast.makeText(this, "worked!", Toast.LENGTH_LONG).show()
                // println(customizedEncrypt().encrypt(pinView.text.toString()))
//                this.finish()
                println(encrypt(pinView.text.toString(),"0e329232ea6d0d73"))
            }

        }

//        backButton.setOnClickListener {
//            this.finish()
//        }

    }


    private fun String.toHexByteArray(): ByteArray {
        val bytes = ByteArray(this.length / 2)
        for (i in bytes.indices) {
            bytes[i] = this.substring(i * 2, i * 2 + 2).toInt(16).toByte()
        }
        return bytes
    }

    private fun ByteArray.printHexBytes() {
        for (b in this) {
            val bb = if (b >= 0) b.toInt() else b + 256
            print(bb.toString(16).padStart(2, '0'))
        }
        println()
    }

    private fun encrypt(string: String, encryptionKey: String) {

        val keyBytes = encryptionKey.toHexByteArray()
        val key = SecretKeySpec(keyBytes, "DES")
        val encCipher = Cipher.getInstance("DES")
        encCipher.init(Cipher.ENCRYPT_MODE, key)
        val plainBytes = string.toHexByteArray()
        val encBytes = encCipher.doFinal(plainBytes)
        return encBytes.printHexBytes()

    }

//    fun customizedEncrypt(): Encryption {
//
//        var encryption: Encryption? = null
//        // we also can generate an entire new Builder
//        try {
//            encryption = Encryption.Builder()
//                .setKeyLength(8)
//                .setKeyAlgorithm("DES")
//                .setCharsetName("UTF8")
//                .setIterationCount(65536)
//                .setKey("0E329232EA6D0D73")
//                .setDigestAlgorithm("SHA1")
//                .setSalt("A beautiful salt")
//                .setBase64Mode(Base64.DEFAULT)
//                .setAlgorithm("DES")
//                .setSecureRandomAlgorithm("SHA1PRNG")
//                .setSecretKeyType("PBKDF2WithHmacSHA1")
//                .setIv(byteArrayOf(29, 88, -79, -101, -108, -38, -126, 90))
//                .build()
//        } catch (e: NoSuchAlgorithmException) {
//            println("Something wrong: $e")
//        }
//        return encryption!!
//    }
}
