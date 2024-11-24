package com.example.rfbank.model

import kotlinx.serialization.Serializable

data class ContactsState(
    val usersState: UsersState = UsersState(),
    val isLoadingUsers: Boolean = false,
)


data class UsersState(
    val users: List<User> = emptyList(),
)

@Serializable
data class Response(
    val results: List<User>,
)

@Serializable
data class User(
    val name: Name,
    val registered: RegisterDate,
    val nat: String,
    val picture: Picture,
)

@Serializable
data class Name(
    val first: String,
    val last: String,
)

@Serializable
data class RegisterDate(
    val age: Int,
)

@Serializable
data class Picture(
    val thumbnail: String,
)