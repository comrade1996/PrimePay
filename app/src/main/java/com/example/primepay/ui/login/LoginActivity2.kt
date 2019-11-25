package com.example.primepay.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate

import com.example.primepay.R
//import com.example.primepay.api.WolfRequest
import com.example.primepay.api.apiHandler
//import com.example.primepay.api.APIController
//import com.example.primepay.api.ServiceVolley
import com.example.primepay.ui.dash
import com.tapadoo.alerter.Alerter
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
//import com.github.kittinunf.fuel.Fuel
import org.json.JSONObject
import java.lang.Exception


class LoginActivity2 : AppCompatActivity(), CoroutineScope {

    private val job = Job()
    override val coroutineContext = job + Main


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_login)
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loggedIN: Boolean
        val progressBar = findViewById(R.id.progressbar) as ProgressBar


        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(
            this,
            R.id.username,
            RegexTemplate.NOT_EMPTY,
            R.string.err_username
        )
        mAwesomeValidation.addValidation(
            this,
            R.id.password,
            RegexTemplate.NOT_EMPTY,
            R.string.err_msg_password
        )
        var credentials = JSONObject()
        login.setOnClickListener {

            if (mAwesomeValidation.validate()) {
                credentials.put("username", username.text.toString())
                credentials.put("password", password.text.toString())
                val apihand = apiHandler()
                println("ddddddddddddddddddddd" + credentials)
                val progres = Alerter.create(this)
                    .setDuration(5000)
                    .enableProgress(true)
                    .setTitle("Processing")
                    .show()
                    launch {
                        try {
                            var data = async(Dispatchers.IO) {  apihand.sendcall(
                                this@LoginActivity2,
                                "http://10.0.2.2:8000/api/login",
                                credentials) }.await()
                            when{
                                data!!.has("id") -> {
                                    Alerter.hide()
                                    val editor = getSharedPreferences("userData", Context.MODE_PRIVATE).edit()
                                editor.putString("name", data["name"].toString())
                                editor.putString("id", data["id"].toString())
                                editor.putString("username", data["username"].toString())
                                editor.putString("clientId", data["clientId"].toString())
                                editor.putString("terminalId", data["terminalId"].toString())
                                editor.putString("token", data["api_token"].toString())
                                editor.apply()

                                val editor2 = getSharedPreferences("userData", Context.MODE_PRIVATE)
                                println("token" + editor2.getString("token", "no"))

                                val intent = Intent(this@LoginActivity2, dash::class.java)
                                startActivity(intent)
                                }
                                data["message"] == "Invalid password" -> { Alerter.hide(); password.error = "Wrong Password"; password.requestFocus() }
                                data["message"] == "User not found" -> { Alerter.hide(); username.error = "Wrong Username"; username.requestFocus() }
                            }
                        }catch (e:Exception){

                        }
                    }










//                GlobalScope.launch {
//                    var response: JSONObject?
//                    val res = async {
//                        apihand.sendcall(
//                            this@LoginActivity2,
//                            "http://10.0.2.2:8000/api/login",
//                            credentials
//                        )
//                    }
//                    try {
//                        run()
//                        if (res.await().let { response = it as JSONObject?; it != null }) {
//                            println("On Async Block" + response)
//
//                            if (!response!!.has("message")) {
//                                println("You Can Login")
//                                println("it should work")
//                                //logged = true
//                                val editor =
//                                  getSharedPreferences("userData", Context.MODE_PRIVATE).edit()
//                                editor.putString("name", response!!["name"].toString())
//                                editor.putString("id", response!!["id"].toString())
//                                editor.putString("username", response!!["username"].toString())
//                                editor.putString("clientId", response!!["clientId"].toString())
//                                editor.putString("terminalId", response!!["terminalId"].toString())
//                                editor.putString("token", response!!["api_token"].toString())
//                                editor.apply()
//
//                                val editor2 = getSharedPreferences("userData", Context.MODE_PRIVATE)
//                                println("token" + editor2.getString("token", "no"))
//                                val intent = Intent(this@LoginActivity2, dash::class.java)
//                                //progressBar.setVisibility(View.INVISIBLE)
//                                startActivity(intent)
//
//                            } else {
//                                progres!!.setText("Invalid password")
//                                println("Invalid Pass")
//                            }
//                            println(response!!["message"])
//                        }
//                    } catch (e: Exception) {
//                        run()
//                        Toast.makeText(applicationContext, "Exception Catched$e", Toast.LENGTH_LONG)
//                            .show()
//                    }
//                    // var datatemp = apihand.sendcall(this,"http://10.0.2.2:8000/api/login",credentials)
//
//
////                datatemp= datatemp.substring(1, datatemp.length -1)
////                var data = JSONObject(datatemp)
//
//                    //println(datatemp)
//                    progressBar.setVisibility(View.VISIBLE)
//
//
//                }
            }
        }

    }
    public fun run() {
        if (Looper.myLooper() == null) {
            Looper.prepare()
        }
        val thisHandler: Handler

    }
}


