package by.zharikov.cocktails.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.zharikov.cocktails.R
import by.zharikov.cocktails.databinding.ActivitySignInBinding


private const val TAG = "tag"

class SignInActivity : AppCompatActivity() {

    private lateinit var registrationAndSigningViewModel: RegistrationAndSigningViewModel
    private var _binding: ActivitySignInBinding? = null
    private val binding: ActivitySignInBinding
        get() = _binding!!

    override fun onStart() {
        super.onStart()
        registrationAndSigningViewModel.userMutableLiveData.observe(
            this, Observer {
                if (it != null) {

                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registrationAndSigningViewModel =
            ViewModelProvider(this).get(RegistrationAndSigningViewModel::class.java)

        binding.registerButton.setOnClickListener {
            registerUser()
            startActivity(
                Intent(this, RegistrationAndLoginActivity::class.java)
            )

        }
    }

    private fun registerUser() {
        with(binding) {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val age = ageEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val repeatPassword = repeatPasswordEditText.text.toString()

            if (firstName.isEmpty()) {
                firstNameEditText.error = getString(R.string.first_name_is_required)
                firstNameEditText.requestFocus()
                return
            }

            if (lastName.isEmpty()) {
                lastNameEditText.error = getString(R.string.last_name_is_required)
                lastNameEditText.requestFocus()
                return
            }
            if (age.toString().isEmpty()) {
                ageEditText.error = getString(R.string.age_is_required)
                ageEditText.requestFocus()
                return
            }
            if (email.isEmpty()) {
                emailEditText.error = getString(R.string.email_is_required)
                emailEditText.requestFocus()
                return
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEditText.error = getString(R.string.please_provide_valid_email)
                emailEditText.requestFocus()
                return
            }
            if (password.isEmpty()) {
                passwordEditText.error = getString(R.string.password_is_required)
                passwordEditText.requestFocus()
                return
            }
            if (repeatPassword.isEmpty()) {
                repeatPasswordEditText.error = getString(R.string.repeat_password_is_required)
                repeatPasswordEditText.requestFocus()
                return
            }

            if (password.length < 8) {
                passwordEditText.error = getString(R.string.min_password_length)
                passwordEditText.requestFocus()
                return
            }

            if (password != repeatPassword) {
                repeatPasswordEditText.error = getString(R.string.repeated_password_match_password)
                repeatPasswordEditText.requestFocus()
                return
            }
            progressBarRegistration.visibility = View.VISIBLE
            registrationAndSigningViewModel.registerUser(firstName, lastName, age, email, password)

            registrationAndSigningViewModel.userMutableLiveData.observe(
                this@SignInActivity, Observer {
                    if (it != null) {
                        Log.d(TAG, "Registration")
                        Toast.makeText(
                            this@SignInActivity,
                            R.string.success_registration,
                            Toast.LENGTH_SHORT
                        )
                            .show()

                    }

                }
            )


            progressBarRegistration.visibility = View.GONE
        }
    }


}