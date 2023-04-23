package com.example.semana5_1_preferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtUsu:EditText=findViewById(R.id.txtUsu)
        val txtPass:EditText=findViewById(R.id.txtPass)
        val btnIniciar:Button=findViewById(R.id.btnIniciar)
        val chkRecordar:CheckBox=findViewById(R.id.chkRecordar)
        val txtCom:TextView=findViewById(R.id.txtComentario)

        val pref:SharedPreferences=this.getSharedPreferences("pref1", Context.MODE_PRIVATE)

        val check:Boolean=pref.getBoolean("check",false)
        val usuario:String=pref.getString("usu","")!!
        val passw:String=pref.getString("pass","")!!

        if (check){
            txtUsu.setText(usuario)
            txtPass.setText(passw)
            chkRecordar.isChecked=true
        } else {
            txtUsu.setText(usuario)
            txtPass.setText(passw)
            chkRecordar.isChecked=false
        }

        btnIniciar.setOnClickListener(){
            val usu:String=txtUsu.text.toString()
            val pass:String=txtPass.text.toString()
            val editor:SharedPreferences.Editor=pref.edit()

            if ( usu=="usu" && pass=="upc"){
                if (chkRecordar.isChecked){
                    editor.putString("usu",usu) //clave,valor
                    editor.putString("pass",pass)
                    editor.putBoolean("check",true)
                } else {
                    editor.putString("usu","")
                    editor.putString("pass","")
                    editor.putBoolean("check",false)
                }
                editor.apply()
                editor.commit()
                val intent=Intent(this,MainActivity2::class.java)
                startActivity(intent)
            } else {
                txtCom.text="Usuario y/0 pass incorrectos"
                txtUsu.text.clear()
                txtPass.text.clear()
            }
        }
    }
}