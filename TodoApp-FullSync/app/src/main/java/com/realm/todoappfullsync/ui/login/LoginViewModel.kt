package com.realm.todoappfullsync.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.realm.todoappfullsync.Constants
import io.realm.ObjectServerError
import io.realm.SyncCredentials
import io.realm.SyncUser


sealed class LoginState
object WaitingForUser : LoginState()
data class InvalidInput(val usernameError: String?, val passwordError: String?) : LoginState()
object LoginPending : LoginState()
data class LoginSuccess(val user: SyncUser) : LoginState()
data class LoginError(val reason: String, val error: ObjectServerError) : LoginState()


class LoginViewModel : ViewModel() {

    private val state = MutableLiveData<LoginState>(

        when (SyncUser.current() != null) {
            true -> LoginSuccess(SyncUser.current())
            false -> WaitingForUser
        }
    )

    /**
     * Observe the state of the login
     */
    val loginState: LiveData<LoginState>
        get() = state

    fun attemptLogin(userName: String, password: String, createUser: Boolean) { // Reset errors.
        if (userName.isEmpty() || password.isEmpty()) {
            val userError: String? = if (userName.isEmpty()) "Missing username" else null
            val passwordError: String? = if (password.isEmpty()) "Missing password" else null
            state.postValue(InvalidInput(userError, passwordError))
            return
        }

        state.postValue(LoginPending)
        val credentials = SyncCredentials.usernamePassword(userName, password, createUser)

        SyncUser.logInAsync(credentials, Constants.AUTH_URL, object : SyncUser.Callback<SyncUser?> {
            override fun onSuccess(user: SyncUser?) {
                state.postValue(LoginSuccess(user!!))
            }

            override fun onError(error: ObjectServerError) {
                Log.e(TAG, error.toString())
                state.postValue(
                    LoginError(
                        "Uh oh something went wrong! (check your logcat please)",
                        error
                    )
                )
            }
        })
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }
}