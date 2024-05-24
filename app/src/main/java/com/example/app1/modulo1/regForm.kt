package com.example.app1.modulo1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app1.R

class regForm : AppCompatActivity() {
    var bbdd=BBDD(this)
    var usr = UsuarioDB()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reg_form)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/



        val username:String = intent.extras?.getString("username").orEmpty()
        val password:String = intent.extras?.getString("password").orEmpty()
        Log.i("Modulo1","desdePaginaAnterior = ${username}, ${password}")

        var nombreApellidoInput: EditText = findViewById<AppCompatEditText>(R.id.inputnombre)
        nombreApellidoInput.onFocusChangeListener = View.OnFocusChangeListener {
                view, hasFocus -> if (hasFocus) { nombreApellidoInput.hint = "" }
        }
        var dniInput: EditText = findViewById<AppCompatEditText>(R.id.inputDni)
        dniInput.onFocusChangeListener = View.OnFocusChangeListener {
                view, hasFocus -> if (hasFocus) { dniInput.hint = "" }
        }
        var emailInput: EditText = findViewById<AppCompatEditText>(R.id.inputEmail)
        emailInput.onFocusChangeListener = View.OnFocusChangeListener {
                view, hasFocus -> if (hasFocus) { emailInput.hint = "" }
        }

        var btnRegistrarFn = findViewById<AppCompatButton>(R.id.btnRegistrarFn)
        btnRegistrarFn.setOnClickListener {
            val nombreApellidoText:String = nombreApellidoInput.text.toString()
            val dniText:String = dniInput.text.toString()
            val emailText:String = emailInput.text.toString()
            val asociado:Boolean = findViewById<CheckBox>(R.id.chkAsociarse).isChecked()

            var res = crearDatos(username, password, nombreApellidoText, dniText, emailText, asociado)
            Log.i("Modulo1","Nuevo Usuario = username: ${username}, pass: ${password}, nombreAp: ${nombreApellidoText}, dni: ${dniText}, email: ${emailText}, asoc: ${asociado}")

            var usr:UsuarioDB? = leerUnDato(username)
            usr?.let {
                intent = Intent(this, userForm::class.java)
                intent.putExtra("username", username)
                intent.putExtra("nombreApellido", nombreApellidoText)
                startActivity(intent)
            } ?: run {
                Toast.makeText(this, "Hubo un error al intentar registrar el usuario en la base de datos", Toast.LENGTH_SHORT).show()
            }
        }



    }

    fun crearDatos(username:String, password:String, nombreApellido:String, dni:String, email:String, asociado:Boolean):String{
        var usr:UsuarioDB = UsuarioDB(username,password,nombreApellido, dni, email, asociado)
        var res = bbdd.insertar(usr)
        Log.i("modulo1", res.toString())
        return res
    }

    fun leerUnDato(username:String):UsuarioDB{
        var bbdd=BBDD(this)
        var res:UsuarioDB = bbdd.leerUno(username)
        Log.i("modulo1",res.toString())
        return res
    }
}