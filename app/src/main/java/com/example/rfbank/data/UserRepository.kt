package com.example.rfbank.data

import com.example.rfbank.model.Response
import com.example.rfbank.model.User
import com.example.rfbank.utils.json
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val client: HttpClient,
    dispatchers: Dispatchers,
) {
    private val _users = MutableStateFlow(emptyList<User>())
    val users = _users.asStateFlow()
    private val scope = CoroutineScope(dispatchers.IO)
    private val nextPage = 0

    fun fetchUsers() = scope.launch {
        val response: HttpResponse =
            client.request("https://randomuser.me/api/?page=${nextPage}&results=20&seed=abc") {
                method = HttpMethod.Get
                contentType(ContentType.Application.Json)
            }
        nextPage.inc()
        parseJson(response.bodyAsText())
    }

    private fun parseJson(text: String) {
        val response = json.decodeFromString<Response>(text)
        val newUsers = response.results
        println("Users: $newUsers")
        _users.value += newUsers
    }
}