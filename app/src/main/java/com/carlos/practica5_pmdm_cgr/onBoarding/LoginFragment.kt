package com.carlos.practica5_pmdm_cgr.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.carlos.practica5_pmdm_cgr.MainActivity
import com.carlos.practica5_pmdm_cgr.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

// ...

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        auth = Firebase.auth

        val etEmail: EditText = view.findViewById(R.id.etEmail)
        val etPassword: EditText = view.findViewById(R.id.etPassword)
        val btnLogin: Button = view.findViewById(R.id.btnLogin)
        val btnGoToRegister: Button = view.findViewById(R.id.btnGoToRegister)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Por favor, rellena todos los campos.", Toast.LENGTH_SHORT).show()
            } else {
                signInUser(email, password)
            }
        }

        btnGoToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return view
    }

    private fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Inicio de sesión exitoso, navegar a MainActivity
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                } else {
                    // Si el inicio de sesión falla, mostrar un mensaje al usuario
                    Toast.makeText(context, "Inicio de sesión fallido: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

}
