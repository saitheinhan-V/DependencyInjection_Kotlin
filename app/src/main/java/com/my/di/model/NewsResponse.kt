/*
 * *
 *  * Created by Rafsan Ahmad on 9/27/21, 5:30 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package com.my.di.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(
    val articles: MutableList<NewsData>,
    val status: String,
    val totalResults: Int
): Serializable