package com.example.lab10

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("/comments/{id}")
    suspend fun getComment(@Path("id") id: Int) : Response<Comment>

    @POST("/comments/")
    suspend fun addComment(@Body comment: Comment) : Response<AddCommentResponse>
}