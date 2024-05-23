package com.example.app1.modulo1

import android.content.Intent
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app1.R


class InicioActivity : AppCompatActivity() {
    var bbdd=BBDD(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio)

        //val users = mutableListOf("Seba","Tian")
        //val passes = mutableListOf("dddd","rrrr")

        //crearDatos("Seba", "dddd", "Seba Mam", "12312334", "SebMa@gmail.com", true)
        //crearDatos("Tian", "ffff", "Tian Rod", "13333334", "tianro@gmail.com", true)
        //crearDatos("jos","xxxx","Jose Alvarez", "12123123", "jesalv@gmail.com", true)
        leerDatos()

        val btnLogin = findViewById<AppCompatButton>(R.id.btnLogin)
        val btnReg = findViewById<AppCompatButton>(R.id.btnReg)

        btnLogin.setOnClickListener{
            val inputUser:String = findViewById<AppCompatEditText>(R.id.inputUser).text.toString()
            val inputPass:String = findViewById<AppCompatEditText>(R.id.inputPass).text.toString()

            if (inputUser.isNotEmpty() && inputPass.isNotEmpty()){
                val solicRead:UsuarioDB = leerUnDato(inputUser)
                Log.i("Modulo1","solicRead ${solicRead.toString()}, inputPass ${inputPass}")
                if (solicRead.password == inputPass){
                    val intent = Intent(this, userForm::class.java)
                    intent.putExtra("username",solicRead.username)
                    intent.putExtra("nombreApellido",solicRead.nombreApellido)
                    startActivity(intent)
                } else {
                    Log.i("Modulo1","Error en login")
                }
            } else {
                return@setOnClickListener
                ////startActivity(intent)
            }
        }

        btnReg.setOnClickListener {
            val intent = Intent(this, regUsrPassForm::class.java)
            startActivity(intent)
        }

        /*fun leerDatos(username:String):UsuarioDB{
            var bbdd=BBDD(this)
            var res:UsuarioDB = bbdd.leerUno(username)
            Log.i("modulo1",res.toString())
            return res
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/



    }

    fun leerUnDato(username:String):UsuarioDB{
        val bbdd=BBDD(this)
        val res:UsuarioDB = bbdd.leerUno(username)
        Log.i("modulo1",res.toString())
        return res
    }

    fun leerDatos():MutableList<UsuarioDB>{
        val resList = bbdd.leer()
        if (resList.size>0) {
            for (i in 0..resList.size-1){
                Log.i("modulo1", "LeerTodos => id: ${resList[i].id} username: ${resList[i].username} pass: ${resList[i].password} nombreApellido: ${resList[i].nombreApellido} dni: ${resList[i].dni} email: ${resList[i].email} asociado: ${resList[i].asociado}")
            }
            return resList
        } else {
            Log.i("Modulo1", "no hay datos ${resList}")
            Toast.makeText(this, "No hay datos coincidentes con la busqueda", Toast.LENGTH_SHORT).show()
            return resList
        }
    }

    fun crearDatos(username:String, password:String, nombreApellido:String, dni:String, email:String, asociado:Boolean):String{
        val usr = UsuarioDB(username,password,nombreApellido, dni, email, asociado)
        val res = bbdd.insertar(usr)
        Log.i("modulo1", res)
        return res
    }

}


