package com.example.medicalhealthappmobile.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.medicalhealthappmobile.R
import com.example.medicalhealthappmobile.data.remote.FirebaseAuthDataSource
import com.example.medicalhealthappmobile.data.repository.AuthRepositoryImpl
import com.example.medicalhealthappmobile.databinding.FragmentResetPasswordBinding
import com.example.medicalhealthappmobile.service.AuthService
import com.example.medicalhealthappmobile.ui.activity.LogInActivity

class ResetPassword : Fragment() {
    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!
    private lateinit var authService: AuthService
    private var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authService = AuthService(requireActivity(), AuthRepositoryImpl(FirebaseAuthDataSource()))
        arguments?.let {
            email = it.getString(ARG_EMAIL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.resetPasswordButton.setOnClickListener {
            val newPassword = binding.newPasswordEditText.text.toString().trim()
            val confirmPassword = binding.confirmPasswordEditText.text.toString().trim()

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPassword != confirmPassword) {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            email?.let { email ->
                authService.resetPassword(email, newPassword) { success, message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    if (success) {
                        startActivity(Intent(requireContext(), LogInActivity::class.java))
                        requireActivity().finish()
                    }
                }
            } ?: Toast.makeText(requireContext(), "Email not found", Toast.LENGTH_SHORT).show()
        }

        binding.textViewBackToLogin.setOnClickListener {
            startActivity(Intent(requireContext(), LogInActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_EMAIL = "email"

        @JvmStatic
        fun newInstance(email: String) =
            ResetPassword().apply {
                arguments = Bundle().apply {
                    putString(ARG_EMAIL, email)
                }
            }
    }
}