package com.example.evaluacion1_iot_rivera_agurto

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.inicarSesion)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // PARA INICIAR SESION
        val btnSession: Button = findViewById(R.id.buttonLogin)
        btnSession.setOnClickListener{
            Loggin_check()
        }

        //PARA CAMBIAR IR A PANTALLA REGISTRARSE
        val btnRegister: Button = findViewById(R.id.buttonRegister)
        btnRegister.setOnClickListener{ changeToRegister() }

    }

    // COMPROBAR SI EL USUARIO EXISTE
    private fun it_users_checked(text: String): Boolean {
        for (user in users) {
            if (user == text) {
                return true
            }
        }
        return false
    }

    // COMPROBAR SI LA CONTRASEÑA EXISTE
    private fun it_passwords_checked(text: String) : Boolean{
        for (password in passwords){
            if (password == text){
                return true
            }
        }
        return false
    }

    //CAMBIOS DE PANTALLA
    private fun changeToRegister(){ val intent = Intent(this, RegisterPanel::class.java); startActivity(intent) } // FUNCION QUE CAMBIA A LA PANTALLA REGISTRO

    //COMPROBACION DE INICIO DE SESION
    private fun Loggin_check() {
        val UtextField: TextInputEditText = findViewById(R.id.Utext)
        val PtextField: TextInputEditText = findViewById(R.id.P_text)
        val LogSession: TextView = findViewById(R.id.text_register)

        val Utext = UtextField.text.toString()
        val Ptext = PtextField.text.toString()

        try {
            // Comprobar si el usuario y la contraseña son correctos
            if (it_users_checked(Utext)) {
                val indexUser = users.indexOf(Utext)
                val indexPassword = passwords.indexOf(Ptext)
                if (it_passwords_checked(Ptext)) {
                    if (indexUser == indexPassword) {
                        Log.i("Inicio de sesión", "Inicio de sesión correcto")
                        user_conected = Utext
                        // Cambiar a HomePanel después de iniciar sesión correctamente
                        val intentHome = Intent(this, HomePanel::class.java)
                        startActivity(intentHome)
                    } else {
                        LogSession.text = "El usuario o la contraseña no coinciden"
                    }
                } else {
                    LogSession.text = "Contraseña incorrecta"
                }
            } else {
                LogSession.text = "Usuario no encontrado"
            }
        } catch (e: Exception) {
            Log.i("Error en Loggin_check", "Mensaje de error: ${e.message}")
            e.printStackTrace()
            LogSession.text = "Hubo un error al iniciar sesión: ${e.message}"
        }
    }
}
