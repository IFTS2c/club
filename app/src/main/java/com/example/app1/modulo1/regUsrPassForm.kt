package com.example.app1.modulo1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app1.R

class regUsrPassForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reg_usr_pass_form)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAceptar = findViewById<AppCompatButton>(R.id.btnAceptar)

        btnAceptar.setOnClickListener{
            val username:String = findViewById<AppCompatEditText>(R.id.inputUser).text.toString()
            val pass1:String = findViewById<AppCompatEditText>(R.id.inputPass1).text.toString()
            val pass2:String = findViewById<AppCompatEditText>(R.id.inputPass2).text.toString()
            if (username.isEmpty() || pass1.isEmpty() || pass2.isEmpty()){
                Log.i("Modulo1","Debe completar todos los campos")
                Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pass1 != pass2) {
                Log.i("Modulo1", "No coinciden ambas password")
                Toast.makeText(this, "No coinciden ambas password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                var us:UsuarioDB = leerUnDato(username)
                if (us.username == username) {
                    Log.i("Modulo1", "Usuario existente")
                    Toast.makeText(this, "Usuario existente", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else {
                    val intent = Intent(this, regForm::class.java)
                    intent.putExtra("username", username)
                    intent.putExtra("password", pass1)
                    startActivity(intent)
                }
            }
        }
    }
    fun leerUnDato(username:String):UsuarioDB{
        var bbdd=BBDD(this)
        var res:UsuarioDB = bbdd.leerUno(username)
        if (res == null){
            Log.i("modulo1","Elemento no encontrado")
            return res
        }
        Log.i("modulo1",res.toString())
        return res
    }
}