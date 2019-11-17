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

//    companion object {
//        private class DownloadData : AsyncTask<String, Void, String>() {
//            private val TAG = "DownloadData"
//
//            override fun onPostExecute(result: String?) {
//                super.onPostExecute(result)
//                Log.d(TAG, "onPostExecute: parameter is $result")
//            }
//
//            override fun doInBackground(vararg url: String?): String {
//                val example =  ApiRoute();
//                var json = example.bowlingJson("Jesse", "Jake")
//                var response = example.post("https://jsonplaceholder.typicode.com/todos/1",json)
//                println("GGGGGGGGGAAAAAAAAAAAAAFFFFFFFFFFAAAAAAAAAAARrrrrrrr")
//                println(response.toString())
//
//                return response.length()
//             }
//        }
//    }



    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_login)
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity2, Observer {
            val loginState = it ?: return@Observer



            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity2, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {

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
        apihand.sendPostRequest("http://www.scientia-sd.com/api/login",params,this)
//        apiController.post("todos/1", params) { response ->
//            // Parse the result
//            println(response)
//        }


        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
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


