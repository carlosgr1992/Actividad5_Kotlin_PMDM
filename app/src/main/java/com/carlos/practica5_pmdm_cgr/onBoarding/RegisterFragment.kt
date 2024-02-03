package com.carlos.practica5_pmdm_cgr.onBoarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.carlos.practica5_pmdm_cgr.MainActivity
import com.carlos.practica5_pmdm_cgr.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        auth = Firebase.auth

        val etEmailRegister: EditText = view.findViewById(R.id.etEmailRegister)
        val etPasswordRegister: EditText = view.findViewById(R.id.etPasswordRegister)
        val etRepeatPassword: EditText = view.findViewById(R.id.etRepeatPassword)
        val btnRegister: Button = view.findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val email = etEmailRegister.text.toString().trim()
            val password = etPasswordRegister.text.toString().trim()
            val repeatPassword = etRepeatPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Toast.makeText(context, "Por favor, rellene todos los campos.", Toast.LENGTH_SHORT).show()
            } else if (password != repeatPassword) {
                Toast.makeText(context, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
            } else {
                // Llamada a Firebase para registrar al usuario
                registerUser(email, password)
            }
        }

        return view
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Registro exitoso, navegar a MainActivity
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                } else {
                    // Si el registro falla, manejar excepciones específicas
                    when (task.exception) {
                        is FirebaseAuthWeakPasswordException ->
                            Toast.makeText(context, "La contraseña es demasiado débil.", Toast.LENGTH_SHORT).show()
                        is FirebaseAuthInvalidCredentialsException ->
                            Toast.makeText(context, "Las credenciales son inválidas.", Toast.LENGTH_SHORT).show()
                        is FirebaseAuthUserCollisionException ->
                            Toast.makeText(context, "La cuenta ya existe.", Toast.LENGTH_SHORT).show()
                        else ->
                            Toast.makeText(context, "El registro falló: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

}
