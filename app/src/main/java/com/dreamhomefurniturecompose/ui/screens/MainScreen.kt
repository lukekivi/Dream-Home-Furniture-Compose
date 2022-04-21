package com.dreamhomefurniturecompose.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamhomefurniturecompose.ui.components.FurnitureCard
import com.dreamhomefurniturecompose.ui.components.FurnitureCardData
import com.dreamhomefurniturecompose.viewmodels.FurnitureDataState
import com.dreamhomefurniturecompose.viewmodels.MainScreenContent
import com.dreamhomefurniturecompose.viewmodels.MainScreenViewModelImpl

@Composable
fun MainScreen(
    vm: MainScreenViewModelImpl = hiltViewModel()   // allow Hilt to provide MainScreen with a viewModel
) {
    val mainScreenContent: MainScreenContent by vm.mainScreenContentFlow.collectAsState(initial = DefaultMainScreenContent)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        when (val furnitureDataState = mainScreenContent.furnitureDataState) {
            is FurnitureDataState.Success -> {
                MainScreenFurnitureList(
                    furnitureCardDataList = furnitureDataState.furnitureCardDataList
                ) {
                    Log.d("MainScreen", "${furnitureDataState.furnitureCardDataList[it].title} was clicked!")
                }
            }
            else -> {
                /**
                 * do something with other states
                 */
            }
        }
    }
}


@Composable
fun MainScreenFurnitureList(
    furnitureCardDataList: List<FurnitureCardData>,
    onCompare: (Int) -> Unit
) {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        itemsIndexed(furnitureCardDataList) { index, furnitureCardData ->
            FurnitureCard(
                data = furnitureCardData,
                setCompare = { onCompare(index) },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


private val DefaultMainScreenContent by lazy {
    MainScreenContent(
        isLoading = false,
        filterItemList = emptyList(),
        furnitureDataState = FurnitureDataState.Success(
            furnitureCardDataList = emptyList()
        )
    )
}