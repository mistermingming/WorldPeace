package com.sdq.android.mvi

/**
 *
 * @PackageName com.sdq.android.mvi
 * @date 2022/7/21 15:01
 * @author songdongqi
 */
data class NewsUiState(
    val isSignedIn: Boolean = false,
    val isPremium: Boolean = false,
    val newsItems: List<NewsItemUiState> = listOf(),
    val userMessages: List<Message> = listOf()
)

data class NewsItemUiState(
    val title: String,
    val body: String,
    val bookmarked: Boolean = false,
)