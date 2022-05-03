package com.dreamhomefurniturecompose.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dreamhomefurniturecompose.Destinations
import com.dreamhomefurniturecompose.data.FurnitureRepo
import com.dreamhomefurniturecompose.data.FurnitureResponse
import com.dreamhomefurniturecompose.network.FurnitureData
import com.dreamhomefurniturecompose.ui.components.FurnitureCardData
import com.dreamhomefurniturecompose.ui.screens.DetailScreenContent
import com.dreamhomefurniturecompose.ui.screens.DetailScreenData
import com.dreamhomefurniturecompose.ui.screens.DetailScreenState
import com.dreamhomefurniturecompose.ui.screens.FurnitureDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

interface DetailScreenViewModel {
    val detailScreenContentFlow: Flow<DetailScreenContent>
}

@HiltViewModel
class DetailScreenViewModelImpl @Inject constructor(
    private val furnitureRepo: FurnitureRepo,
    savedStateHandle: SavedStateHandle
): ViewModel(), DetailScreenViewModel {
    private val furnitureResponseFlow: Flow<FurnitureResponse> = furnitureRepo.furnitureResponseFlow.onEach {
        /**
         * Whenever an update comes in loading is complete.
         */
        isLoadingFlow.emit(false)
    }

    private val isLoadingFlow = MutableStateFlow(false)

    override val detailScreenContentFlow: Flow<DetailScreenContent> = combine(
        furnitureResponseFlow, isLoadingFlow
    ) { furnitureResponse, isLoading ->
        val detailScreenState: DetailScreenState = when(furnitureResponse) {
            is FurnitureResponse.Uninitialized -> {
                furnitureRepo.refreshFurnitureData()
                DetailScreenState.Uninitialized
            }
            is FurnitureResponse.Success -> {
                savedStateHandle.get<String>(Destinations.DetailScreen.ArgId)?.let { articleTitle ->
                    furnitureResponse.simpleFurnitureDataList.firstOrNull { it.title == articleTitle }
                        ?.let { DetailScreenState.Success(it.toDetailScreenData()) }
                        ?: DetailScreenState.NoMatch
                }
                    ?: DetailScreenState.Error("Failed to retreive saved State")
            }
            is FurnitureResponse.Error -> {
                DetailScreenState.Error(furnitureResponse.message)
            }
        }

        DetailScreenContent(
            isLoading = isLoading,
            detailScreenState = detailScreenState
        )
    }


    private fun FurnitureData.toDetailScreenData() = DetailScreenData(
        title = this.title,
        price = "$${this.price}",
        vendorTitle = this.vendorName,
        features = this.features,
        collection = this.collection,
        imageUrl = this.media.firstOrNull()?.url,
        overview = this.overview,
        specs = this.productSpecifications
    )
}