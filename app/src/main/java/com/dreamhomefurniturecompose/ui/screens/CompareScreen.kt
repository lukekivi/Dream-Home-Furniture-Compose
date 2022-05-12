package com.dreamhomefurniturecompose.ui.screens

import androidx.compose.runtime.Composable

@Composable
fun CompareScreen(
    onNavClick: (String) -> Unit,
    onBackNavClick: () -> Unit
) {

}

data class CompareScreenData(
    val title: String,
    val price: String,
    val brand: String,
    val collection: String,
    val overview: String,
    val imageUrl: String?
)

sealed class CompareScreenState {
    object Uninitialized: CompareScreenState()
    object NoMatch: CompareScreenState()
    class Error(val msg: String): CompareScreenState()
    class Success(val itemOne: CompareScreenData, val itemTwo: CompareScreenData): CompareScreenState()
}

data class CompareScreenContent(
    val compareScreenState: CompareScreenState,
    val isLoading: Boolean
)