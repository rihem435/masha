package com.example.masha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignInActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val singin = findViewById<Button>(R.id.button_singIn)
        val email = findViewById<EditText>(R.id.edit_text_email)
        val password = findViewById<EditText>(R.id.edit_text_password)
        singin.setOnClickListener {
        when{

            TextUtils.isEmpty(email.text.toString().trim{it <=' '})-> {
                Toast.makeText(
                    this@SignInActivity,
                    "Please enter email",
                    Toast.LENGTH_SHORT
                ).show()
            }
            TextUtils.isEmpty(password.text.toString().trim{it <=' '})-> {
                Toast.makeText(
                    this@SignInActivity,
                    "Please enter password",
                    Toast.LENGTH_SHORT
                ).show()

        }
        else -> {
            val email: String = email.text.toString().trim{it <=' '}
            val password: String = password.text.toString().trim{ it <= ' '}
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult>
                    {task ->
                        //if the registration is successfly done
                        if(task.isSuccessful){
                            //firebase registered user
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(
                                this@SignInActivity,
                                "you are registered successfly :)",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent =
                                Intent(this@SignInActivity, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", firebaseUser.uid)
                            intent.putExtra("email", email)
                            startActivity(intent)
                            finish()
                        }else{
                            //if the registering is not successful then show error message.
                            Toast.makeText(
                                this@SignInActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
               }
           }
        }



    }
}