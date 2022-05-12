package com.dreamhomefurniturecompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    onCardNavClick: (String) -> Unit,
    onCompareNavClick: (String, String) -> Unit
) {
    val mainScreenContent: MainScreenContent by vm.mainScreenContentFlow.collectAsState(initial = DefaultMainScreenContent)

    val onCompare: (String) -> Unit = mainScreenContent.compareState.let { compareState ->
        when (compareState) {
            is CompareState.Inactive -> { title -> vm.userSetCompare(title) }
            is CompareState.Active -> { title -> onCompareNavClick(compareState.title, title) }
        }
    }

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
                        onCompare = onCompare,
                        onClick = onCardNavClick
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
    onCompare: (String) -> Unit,
    onClick: (String) -> Unit
) {
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        items(furnitureCardDataList) { furnitureCardData ->
            FurnitureCard(
                data = furnitureCardData,
                setCompare = { onCompare(furnitureCardData.title) },
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
    val furnitureDataState: FurnitureDataState,
    val compareState: CompareState
)

sealed class FurnitureDataState {
    object Uninitialized : FurnitureDataState()
    object Empty : FurnitureDataState()
    class Success(val furnitureCardDataList: List<FurnitureCardData>) : FurnitureDataState()
    class Error(val message: String) : FurnitureDataState()
}

sealed class CompareState {
    object Inactive: CompareState()
    class Active(val title: String): CompareState()
}

private val DefaultMainScreenContent = MainScreenContent(
    isLoading = false,
    filterItemList = emptyList(),
    furnitureDataState = FurnitureDataState.Success(furnitureCardDataList = emptyList()),
    compareState = CompareState.Inactive
)