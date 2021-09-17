package pe.edu.upeu.preferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DashboardActivity : AppCompatActivity() {
    lateinit var buttonLogout:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        buttonLogout= findViewById(R.id.btn_dashboard_logout)
        buttonLogout.setOnClickListener {
            val mainActivity= MainActivity.loadPreferences(applicationContext)
            val edit = mainActivity.edit()
            edit.putBoolean("savedCredentials",false)
            edit.apply()
            startActivity(Intent(applicationContext,MainActivity::class.java))

        }
    }
}