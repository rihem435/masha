package com.example.masha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val email = findViewById<EditText>(R.id.edit_text_email_login)
        val password = findViewById<EditText>(R.id.edit_text_pwd_login)
        val login = findViewById<Button>(R.id.button_login)
        login.setOnClickListener {
            when {
                TextUtils.isEmpty(email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter name",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter password",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                else -> {
                    val email: String = email.text.toString().trim { it <= ' ' }
                    val password: String = password.text.toString().trim { it <= ' ' }
                    //Log In using firebaseAuth
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            task ->
                            if (task.isSuccessful){
                                Toast.makeText(
                                    this@LoginActivity,
                                    "you are logged in successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                //val intent = Intent(this@LoginActivity,MainActivity::class.java)
                               /// intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                //intent.pu
                            }else {
                                //if the login is not successful then show error message
                                Toast.makeText(this@LoginActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                    }
                }
            }
        }
    }
