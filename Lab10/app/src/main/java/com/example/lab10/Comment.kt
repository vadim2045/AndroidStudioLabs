package com.example.lab10

import com.google.gson.annotations.SerializedName

data class Comment (
    @SerializedName("id")
    var id: Int,
    @SerializedName("postId")
    var postId: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("body")
    var body: String,
)