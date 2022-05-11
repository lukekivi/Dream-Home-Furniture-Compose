package com.dreamhomefurniturecompose.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamhomefurniturecompose.ui.components.DreamHomeTopBar
import com.dreamhomefurniturecompose.ui.components.FurnitureCard
import com.dreamhomefurniturecompose.ui.components.FurnitureCardData
import com.dreamhomefurniturecompose.viewmodels.FilterOptions
import com.dreamhomefurniturecompose.viewmodels.MainScreenViewModelImpl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    vm: MainScreenViewModelImpl = hiltViewModel(),  // allow Hilt to provide MainScreen with a viewModel
    onNavClick: (String) -> Unit
) {
    val mainScreenContent: MainScreenContent by vm.mainScreenContentFlow.collectAsState(initial = DefaultMainScreenContent)

    Scaffold(
        topBar = { DreamHomeTopBar() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (val furnitureDataState = mainScreenContent.furnitureDataState) {
                is FurnitureDataState.Success -> {
                    MainScreenFurnitureList(
                        furnitureCardDataList = furnitureDataState.furnitureCardDataList,
                        onCompare = {
                            Log.d(
                                "MainScreen",
                                "${furnitureDataState.furnitureCardDataList[it].title} was clicked!"
                            )
                        },
                        onClick = onNavClick
                    )
                }
                else -> {
                    /**
                     * do something with other states
                     */
                }
            }
        }
    }
}


@Composable
fun MainScreenFurnitureList(
    furnitureCardDataList: List<FurnitureCardData>,
    onCompare: (Int) -> Unit,
    onClick: (String) -> Unit
) {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        itemsIndexed(furnitureCardDataList) { index, furnitureCardData ->
            FurnitureCard(
                data = furnitureCardData,
                setCompare = { onCompare(index) },
                onClick = { onClick(furnitureCardData.title) },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


data class FilterItem(
    val filter: FilterOptions,
    val isSelected: Boolean = false
)


data class MainScreenContent(
    val isLoading: Boolean,
    val filterItemList: List<FilterItem>,
    val furnitureDataState: FurnitureDataState
)


sealed class FurnitureDataState {
    object Uninitialized : FurnitureDataState()
    object Empty : FurnitureDataState()
    class Success(val furnitureCardDataList: List<FurnitureCardData>) : FurnitureDataState()
    class Error(val message: String) : FurnitureDataState()
}


private val DefaultMainScreenContent = MainScreenContent(
    isLoading = false,
    filterItemList = emptyList(),
    furnitureDataState = FurnitureDataState.Success(
        furnitureCardDataList = emptyList()
    )
)