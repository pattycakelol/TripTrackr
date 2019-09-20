package com.example.triptrackr.Retrofit

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IMyService {
    @POST ("register")
    @FormUrlEncoded
    fun registerUser (@Field("id") id: String,
                      @Field("name") name: String,
                      @Field("password") password: String): Observable<String>

    @POST ("login")
    @FormUrlEncoded
    fun loginUser (@Field("id") id: String,
                   @Field("password") password: String): Observable<String>

}