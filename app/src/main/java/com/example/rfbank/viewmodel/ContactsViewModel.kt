package com.example.rfbank.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rfbank.data.UserRepository
import com.example.rfbank.model.ContactsState
import com.example.rfbank.model.Response
import com.example.rfbank.utils.json
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable.invokeOnCompletion
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

    private var fetchDataJob: Job? = null

    init {
        viewModelScope.launch {
            userRepository.users.collect { users ->
                _state.update { it.copy(it.usersState.copy(users = users)) }
            }
        }
    }

    fun fetchData() {
        if (fetchDataJob?.isActive == true) return

        _state.update { it.copy(isLoadingUsers = true) }
        fetchDataJob = userRepository.fetchUsers().also {
            it.invokeOnCompletion {
                fetchDataJob?.invokeOnCompletion {
                    _state.update { it.copy(isLoadingUsers = false) }
                }
            }
        }
    }
}