package com.example.primepay.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import android.os.Bundle
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
import kotlinx.coroutines.*
//import com.github.kittinunf.fuel.Fuel
import org.json.JSONObject
import java.lang.Exception


class LoginActivity2 : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_login)
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
       val  progressBar =  findViewById(R.id.progressbar) as ProgressBar


        val mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)
        mAwesomeValidation.addValidation(this, R.id.username,RegexTemplate.NOT_EMPTY,R.string.err_username)
        mAwesomeValidation.addValidation(this, R.id.password, RegexTemplate.NOT_EMPTY,R.string.err_msg_password)
        var credentials = JSONObject()
        login.setOnClickListener {

            if(mAwesomeValidation.validate())
            {
                credentials.put("username",username.text.toString())
                credentials.put("password",password.text.toString())
            val apihand = apiHandler()
        println("ddddddddddddddddddddd"+credentials)
               GlobalScope.launch {
                  var response: JSONObject?
                   val res = async {
                       apihand.sendcall(this@LoginActivity2, "http://10.0.2.2:8000/api/login", credentials)
                   }
                   if(res.await().let {response = it as JSONObject?; it != null  }){
                       println("On Async Block" + response)
                       try {
                           Looper.prepare()
                           if(response!!["message"] !== "User not found"){
                               println("You Can Login")

                           }else{
                               println("Engl3")
                           }
                       }catch (e: Exception){
                           Toast.makeText(applicationContext, "Exception Catched$e", Toast.LENGTH_LONG).show()
                       }
                   }
               }
         // var datatemp = apihand.sendcall(this,"http://10.0.2.2:8000/api/login",credentials)


//                datatemp= datatemp.substring(1, datatemp.length -1)
//                var data = JSONObject(datatemp)

                //println(datatemp)
                progressBar.setVisibility(View.VISIBLE)


        }}

    }
}


