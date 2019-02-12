package com.example.core.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("result")
    val result: String,
    @SerializedName("errors")
    val errors: MutableList<String>
)