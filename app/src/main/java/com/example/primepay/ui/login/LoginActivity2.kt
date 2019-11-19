package com.example.primepay.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
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

import com.example.primepay.R
//import com.example.primepay.api.WolfRequest
import com.example.primepay.api.apiHandler
//import com.example.primepay.api.APIController
//import com.example.primepay.api.ServiceVolley
import com.example.primepay.ui.dash
//import com.github.kittinunf.fuel.Fuel
import org.json.JSONObject


class LoginActivity2 : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_login)
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        login.setOnClickListener {

            val apihand = apiHandler()
        println("ddddddddddddddddddddd")
        apihand.sendcall2(this,"http://www.scientia-sd.com/api/login")
                val intent = Intent(baseContext, dash::class.java)

                startActivity(intent)
            this.finish()

        }

    }
    private fun updateUiWithUser() {

//         val test =DownloadData()
//        val x =   test.execute("dfsdf")
//        println(x)

//        val service = ServiceVolley()
//        val apiController = APIController(service)

        val path = "example_endpoint"
        val params = JSONObject()
        params.put("email", "admin@admin.com")
        params.put("password", "password")

//
//        title: 'foo',
//        body: 'bar',
//        userId: 1
//        var user = ArrayList<LoggedInUser>()
       val apihand = apiHandler()
//        println("ddddddddddddddddddddd")
//        apihand.sendPostRequest("http://www.scientia-sd.com/api/login",params,this)
//        apiController.post("todos/1", params) { response ->
//            // Parse the result
//            println(response)
//        }


        val welcome = getString(R.string.welcome)
//        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "welcome ",
            Toast.LENGTH_LONG
        ).show()

        val i = Intent(this, dash::class.java)
        startActivity(i)
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}


