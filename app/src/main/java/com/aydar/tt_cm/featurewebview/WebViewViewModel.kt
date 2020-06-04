package com.aydar.tt_cm.featurewebview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aydar.tt_cm.data.model.User
import kotlinx.coroutines.launch

class WebViewViewModel(
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User>
        get() = _userLiveData

    fun saveUser(user: User) {
        viewModelScope.launch {
            saveUserUseCase.invoke(user)
        }
    }

    fun getUser(id: String) {
        viewModelScope.launch {
            val user = getUserUseCase.invoke(id)
            _userLiveData.postValue(user)
        }
    }
}