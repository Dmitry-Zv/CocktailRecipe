package by.zharikov.cocktails.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser

class RegistrationAndSigningViewModel(application: Application) : AndroidViewModel(application) {
    private val appRepository = AppRepository(application)
    private var _userMutableLiveData = MutableLiveData<FirebaseUser>()
    val userMutableLiveData: LiveData<FirebaseUser>
        get() = _userMutableLiveData

    fun registerUser(
        firstName: String,
        lastName: String,
        age: String,
        email: String,
        password: String,
    ) {
        appRepository.register(firstName, lastName, age, email, password)
        _userMutableLiveData = appRepository.getUserMutableLiveData()
    }

    fun logInUser(email: String, password: String) {
        appRepository.logIn(email, password)
        _userMutableLiveData = appRepository.getUserMutableLiveData()

    }

    fun resetPasswordUser(email: String) {

        appRepository.resetPassword(email)

    }

    fun signOutUser() {
        appRepository.signOut()

    }

    fun ifLogInUser(ifLogInFun: (() -> Unit)) {
        appRepository.ifLogIn(ifLogInFun)
    }

}