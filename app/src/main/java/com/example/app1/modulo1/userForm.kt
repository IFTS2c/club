package com.example.app1.modulo1

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.app1.R
import com.example.app1.modulo1.tools.toolsVal

class userForm : AppCompatActivity() {
    val bbdd = BBDD(this)
    val t = toolsVal()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_form)

        val username:String = intent.extras?.getString("username").orEmpty()
        val nombreApellido:String = intent.extras?.getString("nombreApellido").orEmpty()
        val userSelected = bbdd.leerUnDato(username)
        val userNameText = findViewById<TextView>(R.id.userName)
        val categoriaUser = findViewById<TextView>(R.id.categoria)

        if (userSelected.asociado){
            categoriaUser.text = "Socio"
        } else {
            categoriaUser.text = "No Socio"
        }

        userNameText.text = "Bienvenido " + nombreApellido

        val btnActividad = findViewById<TextView>(R.id.btnActividad)
        val btnPagar = findViewById<TextView>(R.id.btnPagar)
        val btnCarnet = findViewById<TextView>(R.id.btnCarnet)

        btnActividad.setOnClickListener(){

        }

        btnPagar.setOnClickListener(){

        }

        btnCarnet.setOnClickListener(){

        }

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
    /*fun leerUnDato(username:String):UsuarioDB{
        val bbdd=BBDD(this)
        var res:UsuarioDB = bbdd.leerUnDato(username)
        Log.i("modulo1",res.toString())
        return res
    }*/
}