package com.dreamhomefurniturecompose.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.dreamhomefurniturecompose.Destinations
import com.dreamhomefurniturecompose.data.FurnitureRepo
import com.dreamhomefurniturecompose.data.FurnitureResponse
import com.dreamhomefurniturecompose.network.FurnitureData
import com.dreamhomefurniturecompose.network.ProductSpecifications
import com.dreamhomefurniturecompose.ui.screens.*
import com.example.dreamhomefurniturecompose.R
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
        vendorTitle = this.productSpecifications.brandName,
        features = this.features,
        collection = this.collection,
        imageUrl = this.media.firstOrNull()?.url,
        overview = this.overview,
        specs = this.productSpecifications.toListOfDetailScreenSpecs()
    )

    private fun ProductSpecifications.toListOfDetailScreenSpecs() = listOf(
        DetailScreenSpecs(
            categoryResId = R.string.brand_name,
            specValue = this.brandName
        ),
        DetailScreenSpecs(
            categoryResId = R.string.color,
            specValue = this.color
        ),
        DetailScreenSpecs(
            categoryResId = R.string.consumer_assembly,
            specValue = this.consumerAssembly
        ),
        DetailScreenSpecs(
            categoryResId = R.string.consumer_description,
            specValue = this.consumerDescription
        ),
        DetailScreenSpecs(
            categoryResId = R.string.friendly_description,
            specValue = this.friendlyDescription
        ),
        DetailScreenSpecs(
            categoryResId = R.string.item_code,
            specValue = this.itemCode
        ),
        DetailScreenSpecs(
            categoryResId = R.string.item_type,
            specValue = this.itemType
        ),
        DetailScreenSpecs(
            categoryResId = R.string.item_weight_lbs,
            specValue = this.itemWeightLbs.toString()
        ),
        DetailScreenSpecs(
            categoryResId = R.string.manufacturer_warranty_days,
            specValue = this.manufacturerWarrantyDays.toString()
        ),
        DetailScreenSpecs(
            categoryResId = R.string.marxent_pid_number,
            specValue = this.marxentPidNumber
        ),
        DetailScreenSpecs(
            categoryResId = R.string.retail_type,
            specValue = this.retailType
        ),
        DetailScreenSpecs(
            categoryResId = R.string.series_id,
            specValue = this.seriesId.toString()
        ),
        DetailScreenSpecs(
            categoryResId = R.string.sku,
            specValue = this.sku
        ),
        DetailScreenSpecs(
            categoryResId = R.string.unit_depth_in,
            specValue = this.unitDepthInches.toString()
        ),
        DetailScreenSpecs(
            categoryResId = R.string.unit_height_in,
            specValue = this.unitHeightInches.toString()
        ),
        DetailScreenSpecs(
            categoryResId = R.string.unit_width_in,
            specValue = this.unitWidthInches.toString()
        )
    )
}