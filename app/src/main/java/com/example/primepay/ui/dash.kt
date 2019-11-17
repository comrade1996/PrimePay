package com.example.primepay.ui
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.primepay.*
import kotlinx.android.synthetic.main.activity_dash.*

class dash : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)
        setSupportActionBar(toolbar)
        val actnBar = supportActionBar
        actnBar!!.setDisplayShowHomeEnabled(true)
        actnBar.show()


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

//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.menu, menu)
//        return true
//    }
//
//    // actions on click menu items
//    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
//
//        R.id.action_profile -> {
//            msgShow("Profile")
//            true
//        }
//        R.id.action_setting -> {
//            msgShow("Setting")
//            true
//        }
//        else -> {
//            // If we got here, the user's action was not recognized.
//            // Invoke the superclass to handle it.
//            super.onOptionsItemSelected(item)
//        }
//    }

    fun msgShow(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_cut -> {
                Toast.makeText(applicationContext, "Cut", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_copy -> {
                Toast.makeText(applicationContext, "copy", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_paste -> {
                Toast.makeText(applicationContext, "paste", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_new -> {
                Toast.makeText(applicationContext, "new", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
