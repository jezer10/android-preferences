package pe.edu.upeu.preferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var checkBox: CheckBox
    lateinit var logButton: Button
    lateinit var usernameText: EditText
    lateinit var passwordText: EditText

    var firstUser:UserModel=UserModel("Rodrigo","Elpepe")


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logButton = findViewById(R.id.btn_main_auth)
        checkBox = findViewById(R.id.cb_main_credentials)
        usernameText = findViewById(R.id.et_main_username)
        passwordText = findViewById(R.id.et_main_password)
        var rodrigo = loadPreferences(applicationContext)
        Toast.makeText(
            applicationContext,
            "${rodrigo.getString("password", "rodrigo")}",
            Toast.LENGTH_SHORT
        )
            .show()
        if (rodrigo.getBoolean("savedCredentials", false)) {
            startActivity(Intent(applicationContext, DashboardActivity::class.java))
        }
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        logButton.setOnClickListener {
            saveCredentials(
                usernameText.text.toString(),
                passwordText.text.toString(),
                checkBox.isChecked,
                this
            )
        }

    }

    private fun saveCredentials(
        username: String,
        password: String,
        save: Boolean,
        context: Context
    ) {

        if (username == "" || password == "") {
            Toast.makeText(applicationContext, "Invalid Username Or Password", Toast.LENGTH_SHORT)
                .show()
            return
        }
        if (save) {
            val edit = loadPreferences(context).edit()
            edit.putString("username", username)
            edit.putString("password", password)
            edit.putBoolean("savedCredentials", save)
            edit.apply()
            startActivity(Intent(applicationContext, DashboardActivity::class.java))

        } else {
            Toast.makeText(
                applicationContext,
                "Logged without save credentials service",
                Toast.LENGTH_SHORT
            )
                .show()
            startActivity(Intent(applicationContext, DashboardActivity::class.java))


        }
    }


    companion object {
        fun loadPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences("credentials", Context.MODE_PRIVATE)
        }
    }


}