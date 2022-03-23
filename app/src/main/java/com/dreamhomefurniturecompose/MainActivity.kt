package com.dreamhomefurniturecompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamhomefurniturecompose.ui.components.FurnitureCard
import com.dreamhomefurniturecompose.viewmodels.FurnitureDataState
import com.dreamhomefurniturecompose.viewmodels.MainScreenContent
import com.dreamhomefurniturecompose.viewmodels.MainScreenViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * set the root composable
         */
        setContent {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(
    vm: MainScreenViewModelImpl = hiltViewModel()   // allow Hilt to provide MainScreen with a viewModel
) {
    val mainScreenContent: MainScreenContent by vm.mainScreenContentFlow.collectAsState(initial = DefaultMainScreenContent)

    /**
     * Handle each data state differently
     */
    when (val furnitureDataState = mainScreenContent.furnitureDataState) {

            is FurnitureDataState.Success -> {
                /**
                 * If there is data in simpleFurnitureDataList display a furniture card
                 */
                furnitureDataState.simpleFurnitureDataList.firstOrNull()?.let { furnitureCardData ->
                    FurnitureCard(
                        data = furnitureCardData,
                        setCompare = { Log.d("CompareButton", "I was clicked") }
                    )
                }
            }
            else -> {
                // do something with other states
            }
        }
}

private val DefaultMainScreenContent by lazy {
    MainScreenContent(
        isLoading = false,
        filterItemList = emptyList(),
        furnitureDataState = FurnitureDataState.Success(
            simpleFurnitureDataList = emptyList()
        )
    )
}