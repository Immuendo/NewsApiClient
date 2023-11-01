package com.mundo.newsapiclient.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class APIResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
): Serializable