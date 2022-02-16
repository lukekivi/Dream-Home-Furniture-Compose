package com.example.dreamhomefurniturecompose.viewmodels

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.example.dreamhomefurniturecompose.R
import com.dreamhomefurniturecompose.data.FurnitureRepo
import com.dreamhomefurniturecompose.data.FurnitureResponse
import com.dreamhomefurniturecompose.network.FurnitureData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

interface MainScreenViewModel {
    val mainScreenContentFlow: Flow<MainScreenContent>
    fun userRefresh()
    fun userUpdateFilter(filterOption: FilterOptions)
}

@HiltViewModel
class MainScreenViewModelImpl @Inject constructor(
    private val furnitureRepo: FurnitureRepo
): ViewModel(), MainScreenViewModel {

    private val filterItemListFlow = MutableStateFlow(FilterOptions.values().map { FilterItem(it) }.toList())

    private val furnitureResponseFlow: Flow<FurnitureResponse> = furnitureRepo.furnitureResponseFlow.onEach {
        /**
         * Whenever an update comes in loading is complete.
         */
        isLoadingFlow.emit(false)
    }

    private val isLoadingFlow = MutableStateFlow(false)

    override val mainScreenContentFlow: Flow<MainScreenContent> = combine(
        furnitureResponseFlow, isLoadingFlow, filterItemListFlow
    ) { furnitureResponse, isLoading, filterItemList ->
        val furnitureDataState = when(furnitureResponse) {
            is FurnitureResponse.Uninitialized -> {
                furnitureRepo.refreshFurnitureData()
                FurnitureDataState.Uninitialized
            }
            is FurnitureResponse.Success -> {
                /**
                 * Filter the data if a filter is selected
                 */
                val filteredData = filterItemList
                    .firstOrNull { it.isSelected }
                    ?.let { filterItem ->
                        furnitureResponse.simpleFurnitureDataList
                            .filter { it.collection == filterItem.filter.apiFilterName } // todo not the correct mapping
                    }
                    ?: furnitureResponse.simpleFurnitureDataList

                if (filteredData.isEmpty()) {
                    FurnitureDataState.Empty
                } else {
                    FurnitureDataState.Success(
                        simpleFurnitureDataList = filteredData.map { it.toSimpleFurnitureData() }
                    )
                }
            }
            is FurnitureResponse.Error -> {
                FurnitureDataState.Error(furnitureResponse.message)
            }
        }

        MainScreenContent(
            isLoading = isLoading,
            filterItemList = filterItemList,
            furnitureDataResponse = furnitureDataState
        )
    }

    override fun userRefresh() {
        isLoadingFlow.value = true
        furnitureRepo.refreshFurnitureData()
    }

    override fun userUpdateFilter(filterOption: FilterOptions) {
        val newFilterList = FilterOptions.values().map { FilterItem(it) }.toMutableList()

        val prevFilterOption = filterItemListFlow.value.firstOrNull { it.isSelected }

        if (prevFilterOption?.filter != filterOption) {
            newFilterList[filterOption.ordinal] = FilterItem(
                filter = filterOption,
                isSelected = true
            )
        }

        filterItemListFlow.value = newFilterList.toList()
    }


    private fun FurnitureData.toSimpleFurnitureData() = SimpleFurnitureData(
        title = this.title,
        price = this.price,
        vendorName = this.vendorName,
        collection = this.collection,
        imageUrl = this.media.firstOrNull()?.url
    )
}

enum class FilterOptions(
    @StringRes val label: Int,
    /**
     * Actual name of section as it appears in API results. Used for actual filtering.
     */
    val apiFilterName: String
) {
    Beds(R.string.filter_item_beds, "Beds"),
    BeddingAccessories(R.string.filter_item_bedding_accessories, "Bedding Accessories"),
    Benches(R.string.filter_item_benches, "Benches"),
    Chests(R.string.filter_item_chests, "Chests"),
    Desks(R.string.filter_item_desks, "Desks"),
    Dressers(R.string.filter_item_dressers,"Dressers"),
    Nightstands(R.string.filter_item_nightstands, "Nightstands"),
    VanityTables(R.string.filter_item_vanity_tables, "Vanity Tables")
}

data class FilterItem(
    val filter: FilterOptions,
    val isSelected: Boolean = false
)

data class MainScreenContent(
    val isLoading: Boolean,
    val filterItemList: List<FilterItem>,
    val furnitureDataResponse: FurnitureDataState
)

sealed class FurnitureDataState {
    object Uninitialized: FurnitureDataState()
    object Empty: FurnitureDataState()
    class Success(val simpleFurnitureDataList: List<SimpleFurnitureData>): FurnitureDataState()
    class Error(val message: String): FurnitureDataState()
}

data class SimpleFurnitureData(
    val title: String,
    val price: Double,
    val vendorName: String,
    val collection: String,
    val imageUrl: String?
)