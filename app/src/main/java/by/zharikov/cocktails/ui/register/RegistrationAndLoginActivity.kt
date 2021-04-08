package by.zharikov.cocktails.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.zharikov.cocktails.MainActivity
import by.zharikov.cocktails.R
import by.zharikov.cocktails.databinding.ActivityLogInBinding
import by.zharikov.cocktails.ui.utils.showAlert

private const val TAG = "tag"

class RegistrationAndLoginActivity : AppCompatActivity() {
    private var _binding: ActivityLogInBinding? = null
    private val binding: ActivityLogInBinding
        get() = _binding!!
    private lateinit var registrationAndSigningViewModel: RegistrationAndSigningViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registrationAndSigningViewModel =
            ViewModelProvider(this).get(RegistrationAndSigningViewModel::class.java)

        ifUserIsLogged()

        with(binding) {
            registerTextView.setOnClickListener {
                startActivity(Intent(this@RegistrationAndLoginActivity, SignInActivity::class.java))
            }
            loginButton.setOnClickListener {
                logInUser()
            }

            forgetPasswordTextView.setOnClickListener {
                val resetEmail = EditText(this@RegistrationAndLoginActivity)
                recoveryPassword(resetEmail)
            }
        }


    }

    private fun ifUserIsLogged() {
        registrationAndSigningViewModel.ifLogInUser {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun logInUser() {
        with(binding) {
            val email = emailLogInEditText.text.toString()
            val password = passwordLogInEditText.text.toString()

            if (email.isEmpty()) {
                emailLogInEditText.error = getString(R.string.email_is_required)
                emailLogInEditText.requestFocus()
                return
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailLogInEditText.error = getString(R.string.please_provide_valid_email)
                emailLogInEditText.requestFocus()
                return
            }
            if (password.isEmpty()) {
                passwordLogInEditText.error = getString(R.string.password_is_required)
                passwordLogInEditText.requestFocus()
                return
            }

            progressBarLogin.visibility = View.VISIBLE
            registrationAndSigningViewModel.logInUser(email, password)
            registrationAndSigningViewModel.userMutableLiveData.observe(
                this@RegistrationAndLoginActivity, Observer {
                    if (it != null) {
                        if (it.isEmailVerified) {
                            Log.d(TAG, "Verification is success")
                            startActivity(
                                Intent(
                                    this@RegistrationAndLoginActivity,
                                    MainActivity::class.java
                                )
                            )
                        } else {
                            Log.d(TAG, "Verification don't success")
                            Toast.makeText(
                                this@RegistrationAndLoginActivity,
                                R.string.verify_your_email,
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
                }
            )
            progressBarLogin.visibility = View.GONE
        }
    }


    private fun recoveryPassword(resetEmail: EditText) {
        showAlert(
            title = R.string.title_reset_password,
            message = R.string.enter_your_reset_email,
            email = resetEmail,
            positiveButtonResId = R.string.positive_button,
            negativeButtonResId = R.string.negative_button,
            positiveButtonFun = { ->
                val newEmail = resetEmail.text.toString()
                registrationAndSigningViewModel.resetPasswordUser(newEmail)
            }

        )

    }


}