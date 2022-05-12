package com.dreamhomefurniturecompose.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dreamhomefurniturecompose.Destinations
import com.dreamhomefurniturecompose.data.FurnitureRepo
import com.dreamhomefurniturecompose.data.FurnitureResponse
import com.dreamhomefurniturecompose.network.FurnitureData
import com.dreamhomefurniturecompose.ui.screens.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

interface CompareScreenViewModel {
    val compareScreenContentFlow: Flow<CompareScreenContent>
}

@HiltViewModel
class CompareScreenViewModelImpl @Inject constructor(
    private val furnitureRepo: FurnitureRepo,
    savedStateHandle: SavedStateHandle
) : CompareScreenViewModel, ViewModel() {
    private val furnitureResponseFlow: Flow<FurnitureResponse> = furnitureRepo.furnitureResponseFlow.onEach {
        /**
         * Whenever an update comes in loading is complete.
         */
        isLoadingFlow.emit(false)
    }

    private val isLoadingFlow = MutableStateFlow(false)

    override val compareScreenContentFlow: Flow<CompareScreenContent> = combine(
        furnitureResponseFlow, isLoadingFlow
    ) { furnitureResponse, isLoading ->
        val compareScreenState: CompareScreenState = when(furnitureResponse) {
            is FurnitureResponse.Uninitialized -> {
                furnitureRepo.refreshFurnitureData()
                CompareScreenState.Uninitialized
            }
            is FurnitureResponse.Success -> {
                val compareScreenDataOne: CompareScreenData? = savedStateHandle.get<String>(Destinations.CompareScreen.ArgIdOne)?.let { articleTitle ->
                    furnitureResponse.simpleFurnitureDataList.firstOrNull { it.title == articleTitle }
                        ?.toCompareScreenData()
                } ?: return@combine CompareScreenContent(
                    isLoading = isLoading,
                    compareScreenState = CompareScreenState.Error("Failed to retrieve ArgIdOne from savedState.")
                )

                val compareScreenDataTwo: CompareScreenData? = savedStateHandle.get<String>(Destinations.CompareScreen.ArgIdTwo)?.let { articleTitle ->
                    furnitureResponse.simpleFurnitureDataList.firstOrNull { it.title == articleTitle }
                        ?.toCompareScreenData()
                } ?: return@combine CompareScreenContent(
                    isLoading = isLoading,
                    compareScreenState = CompareScreenState.Error("Failed to retrieve ArgIdTwo from savedState.")
                )

                // todo migrate to furnitureRepo providing specific values

                if (compareScreenDataOne == null || compareScreenDataTwo == null) {
                    CompareScreenState.NoMatch
                } else {
                    CompareScreenState.Success(compareScreenDataOne, compareScreenDataTwo)
                }
            }
            is FurnitureResponse.Error -> {
                CompareScreenState.Error(furnitureResponse.message)
            }
        }

        CompareScreenContent(
            isLoading = isLoading,
            compareScreenState = compareScreenState
        )
    }

    private fun FurnitureData.toCompareScreenData() = CompareScreenData(
        title = this.title,
        price = "$${this.price}",
        brand = this.productSpecifications.brandName,
        collection = this.collection,
        overview = this.overview,
        imageUrl = this.media.firstOrNull()?.url
    )
}