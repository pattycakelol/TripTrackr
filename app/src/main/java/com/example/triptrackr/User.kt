package com.example.triptrackr

data class User(val username: String, val profileImageUrl: String) {
    constructor() : this("", "")
}