package com.example.rfbank.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rfbank.data.UserRepository
import com.example.rfbank.model.ContactsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ContactsState())
    val state = _state.asStateFlow()

    private var fetchUsersJob: Job? = null

    init {
        viewModelScope.launch {
            userRepository.users.collect { users ->
                _state.update { it.copy(it.usersState.copy(users = users)) }
            }
        }
    }

    fun fetchUsers() {
        if (fetchUsersJob?.isActive == true) return

        _state.update { it.copy(isLoadingUsers = true) }
        fetchUsersJob = userRepository.fetchUsers().also {
            it.invokeOnCompletion {
                fetchUsersJob?.invokeOnCompletion {
                    _state.update { it.copy(isLoadingUsers = false) }
                }
            }
        }
    }
}