package com.example.primepay.ui
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.primepay.*
import androidx.appcompat.widget.Toolbar
import androidx.core.os.ConfigurationCompat
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.zeugmasolutions.localehelper.LocaleHelperActivityDelegateImpl
import org.json.JSONObject
import java.util.*


class dash : AppCompatActivity(),View.OnClickListener{

    private val localeDelegate = LocaleHelperActivityDelegateImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        localeDelegate.onCreate(this)
        setSupportActionBar(toolbar)


//        val p = findViewById<LinearLayout>(R.id.purchaseCardView)
//        p.setOnClickListener{
//            updateUiWithUser()
//        }

    }



//    private fun updateUiWithUser() {
//        val welcome = getString(R.string.welcome)
//        val displayName = "Purchase"
//        // TODO : initiate successful logged in experience
//        Toast.makeText(
//            applicationContext,
//            "$welcome $displayName",
//            Toast.LENGTH_LONG
//        ).show()
//
//        val i = Intent(this, Purchase::class.java)
//        startActivity(i)
//    }



    override fun onClick(v: View?) {

        var pageName = v?.tag.toString()
        println(pageName)
        if(pageName=="cashIn"){
            val intent = Intent(this, cashIn::class.java)
            startActivity(intent)
        }

        if(pageName=="cardTransfer"){
            val intent = Intent(this, cardTransfer::class.java)
            startActivity(intent)
        }

        if(pageName=="Purchase"){
            val intent = Intent(this, Purchase::class.java)
            startActivity(intent)
        }

        if(pageName=="Customs"){
            val intent = Intent(this, Customs::class.java)
            startActivity(intent)
        }

        if(pageName=="E15"){
            val intent = Intent(this, E15::class.java)
            startActivity(intent)
        }

        if(pageName=="electric"){
            val intent = Intent(this, electric::class.java)
            startActivity(intent)
        }

        if(pageName=="MobileBill"){
            val intent = Intent(this, MobileBill::class.java)
            startActivity(intent)
        }

        if(pageName=="MobileTopup"){
            val intent = Intent(this, MobileTopup::class.java)
            startActivity(intent)
        }

        if(pageName=="MOHE"){
            val intent = Intent(this, MOHE::class.java)
            startActivity(intent)
        }


        if(pageName=="purcheseCashBack"){
            val intent = Intent(this, purcheseCashBack::class.java)
            startActivity(intent)
        }


// To pass any data to next activity
//        intent.putExtra("keyIdentifier", value)
// start your next activity

    }


    fun msgShow(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_cut -> {
                Toast.makeText(applicationContext, "Cut", Toast.LENGTH_SHORT).show()
                val params = mutableMapOf<String, Any>()
                params["email"] = "mhmd@me.me"
                params["password"] = "1234"
                val jsonObj = JSONObject(params as Map<*, *>)
                val request = JsonObjectRequest(
                    Request.Method.POST,"http://www.scientia-sd.com/api/login",jsonObj,
                    Response.Listener { response ->
                    // Process the json
                    try {
                        println(response)
                    }catch (e:Exception){
                        println(e)
                    }

                }, Response.ErrorListener{
                    // Error in request
                        println(it)
                })
                request.retryPolicy = DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                    0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
                    1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
                 val requestQueue: RequestQueue by lazy {
                    // applicationContext is key, it keeps you from leaking the
                    // Activity or BroadcastReceiver if someone passes one in.
                    Volley.newRequestQueue(applicationContext)
                }
                requestQueue.add(request)
            }
            R.id.action_copy -> {
                Toast.makeText(applicationContext, "copy", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_paste -> {
                Toast.makeText(applicationContext, "paste", Toast.LENGTH_SHORT).show()
                val currentLocale = ConfigurationCompat.getLocales(resources.configuration)[0]
                val ara = Locale.Builder().setLanguage("ar").build()
                if(currentLocale == ara){
                    updateLocale(Locale.ENGLISH)
                }else{
                    updateLocale(ara)
                }
                Toast.makeText(applicationContext, "Current Lang is$currentLocale", Toast.LENGTH_SHORT).show()
            }
            R.id.action_new -> {
                Toast.makeText(applicationContext, "new", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true

    }


    //Localization Code
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(localeDelegate.attachBaseContext(newBase))
    }

    override fun onResume() {
        super.onResume()
        localeDelegate.onResumed(this)
    }

    override fun onPause() {
        super.onPause()
        localeDelegate.onPaused()
    }

    open fun updateLocale(locale: Locale) {
        localeDelegate.setLocale(this, locale)
        Locale.setDefault(locale)
        val langPref = getSharedPreferences("language", Context.MODE_PRIVATE)
        val editor = langPref.edit()
        editor.putString("langToLoad", locale.toLanguageTag())
        editor.apply()
    }

    // End Of Localization Code

}
