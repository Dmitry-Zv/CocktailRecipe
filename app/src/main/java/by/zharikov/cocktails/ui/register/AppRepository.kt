package by.zharikov.cocktails.ui.register

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import by.zharikov.cocktails.R
import by.zharikov.cocktails.ui.register.user.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AppRepository(
    private val application: Application,
) {
    private val auth: FirebaseAuth = Firebase.auth
    private val dataBase = Firebase.database.reference
    private val userMutableLiveData: MutableLiveData<FirebaseUser> = MutableLiveData()


    fun getUserMutableLiveData() = userMutableLiveData

    fun register(
        firstName: String,
        lastName: String,
        age: String,
        email: String,
        password: String
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    auth.currentUser?.sendEmailVerification()
                        ?.addOnSuccessListener {
//                            Toast.makeText(
//                                application,
//                                R.string.verification_email.toString(),
//                                Toast.LENGTH_SHORT
//                            ).show()
                            val user = User(
                                firstName = firstName,
                                lastName = lastName,
                                age = age,
                                email = email
                            )
                            FirebaseAuth.getInstance()
                                .currentUser?.let {
                                    dataBase.child("user")
                                        .child(it.uid)
                                        .setValue(user)
                                        .addOnCompleteListener {
                                            if (it.isSuccessful) {
                                                userMutableLiveData.postValue(auth.currentUser)
                                            } else {
                                                Toast.makeText(
                                                    application,
                                                    R.string.error.toString() + it.exception?.message,
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                }

                        }
                        ?.addOnFailureListener {
                            Toast.makeText(
                                application,
                                R.string.email_verification_not_sent.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                } else {
                    Toast.makeText(
                        application,
                        R.string.error.toString() + it.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }

    }

    fun logIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    userMutableLiveData.postValue(auth.currentUser)

                } else {
                    Toast.makeText(
                        application, R.string.error.toString() + it.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

    }

    fun resetPassword(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                Toast.makeText(
                    application,
                    R.string.successful_reset_link.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    application,
                    R.string.error_reset_link.toString() + it.message,
                    Toast.LENGTH_SHORT
                ).show()
            }

    }

    fun signOut() {
        auth.signOut()
    }

    fun ifLogIn(ifLogInFun: (() -> Unit)){
        auth.addAuthStateListener {
            if(it.currentUser != null){
                ifLogInFun()
            }
        }
    }
}