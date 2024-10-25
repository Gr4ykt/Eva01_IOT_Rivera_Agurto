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

class RegisterPanel : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.register)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register_panel)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnregister: Button = findViewById(R.id.buttonRegister)

        btnregister.setOnClickListener {
            register_user()
        }
    }

    // ITERACION DE LA LISTA DE USUARIOS Y COMPROBACION DE SI EL USUARIO NO EXISTE
    private fun it_users_checked(text: String): Boolean {
        for (user in users) {
            if (user == text) {
                return true
            }
        }
        return false
    }

    // COMPROBACION SI LA CONTRASEÑA ES IGUAL EN AMBOS CAMPOS
    private fun checkFieldsPassword(text1: String, text2: String): Boolean {
        return text1 == text2
    }

    private fun register_user() {
        try {
            val UtextField: TextInputEditText = findViewById(R.id.U_text)
            val PtextField: TextInputEditText = findViewById(R.id.P_text)
            val RPtextField: TextInputEditText = findViewById(R.id.RP_text)
            val LogRegister: TextView = findViewById(R.id.log_register)

            // Obtener el texto de los campos correctamente
            val Utext = UtextField.text.toString()
            val Ptext = PtextField.text.toString()
            val RPtext = RPtextField.text.toString()

            if (!it_users_checked(Utext) && checkFieldsPassword(Ptext, RPtext)) {
                // Si el usuario no existe y las contraseñas coinciden
                users.add(Utext)
                passwords.add(Ptext)
                val intent = Intent(this, MainActivity::class.java); startActivity(intent)

            } else {
                // Si el usuario ya existe o las contraseñas no coinciden
                if (it_users_checked(Utext) && checkFieldsPassword(Ptext, RPtext) == false) {
                    LogRegister.text = "Las contraseñas no coinciden y el usuario ya existe"
                } else if (it_users_checked(Utext)){
                    LogRegister.text = "El usuario ya existe"
                } else {
                    LogRegister.text = "Las contraseñas no coinciden"
                }
            }
        } catch (e: Exception) {
            Log.e("Error al ejecutar", e.message.toString())
        }
    }
}
