package com.example.medicalhealthappmobile.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.medicalhealthappmobile.R
import com.example.medicalhealthappmobile.data.remote.FirebaseAuthDataSource
import com.example.medicalhealthappmobile.data.repository.AuthRepositoryImpl
import com.example.medicalhealthappmobile.databinding.FragmentVerifyEmailBinding
import com.example.medicalhealthappmobile.service.AuthService
import com.example.medicalhealthappmobile.ui.activity.LogInActivity

class VerifyEmail : Fragment() {
    private var _binding: FragmentVerifyEmailBinding? = null
    private val binding get() = _binding!!
    private lateinit var authService: AuthService
    private var countDownTimer: CountDownTimer? = null
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
        _binding = FragmentVerifyEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        email?.let { binding.emailEditText.setText(it) }
        binding.otpEditText.visibility = View.GONE
        binding.textViewResendTime.visibility = View.GONE
        binding.changePasswordButton.visibility = View.GONE

        binding.sendOtpButton.setOnClickListener {
            val emailInput = binding.emailEditText.text.toString().trim()
            if (emailInput.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter your email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            authService.sendOtp(emailInput) { success, message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                if (success) {
                    binding.otpEditText.visibility = View.VISIBLE
                    binding.changePasswordButton.visibility = View.VISIBLE
                    binding.otpSentMessage.text = "An OTP has been sent to your email"
                    binding.sendOtpButton.isEnabled = false
                    startResendTimer()
                }
            }
        }

        binding.changePasswordButton.setOnClickListener {
            val emailInput = binding.emailEditText.text.toString().trim()
            val otpInput = binding.otpEditText.text.toString().trim()
            if (otpInput.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter the OTP", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            authService.verifyOtp(emailInput, otpInput) { success, message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                if (success) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ResetPassword.newInstance(emailInput))
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

        binding.textViewBackToLogin.setOnClickListener {
            startActivity(Intent(requireContext(), LogInActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun startResendTimer() {
        binding.textViewResendTime.visibility = View.VISIBLE
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(180000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = (millisUntilFinished / 1000) % 60
                binding.textViewResendTime.text = String.format("Wait %d:%02d to resend", minutes, seconds)
            }

            override fun onFinish() {
                binding.textViewResendTime.visibility = View.GONE
                binding.sendOtpButton.isEnabled = true
                binding.sendOtpButton.text = "Resend OTP"
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer?.cancel()
        _binding = null
    }

    companion object {
        private const val ARG_EMAIL = "email"

        @JvmStatic
        fun newInstance(email: String) =
            VerifyEmail().apply {
                arguments = Bundle().apply {
                    putString(ARG_EMAIL, email)
                }
            }
    }
}