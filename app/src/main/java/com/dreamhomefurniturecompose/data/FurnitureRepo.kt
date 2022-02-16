package com.dreamhomefurniturecompose.data

import android.util.Log
import com.dreamhomefurniturecompose.network.FurnitureNetworkService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "FurnitureRepo"
private const val DEFAULT_ERROR_MESSAGE = "Unknown error"

interface FurnitureRepo {
    val furnitureResponseFlow: Flow<FurnitureResponse>
    fun refreshFurnitureData()
}

@Singleton
class FurnitureRepoImpl @Inject constructor(
    private val furnitureNetworkService: FurnitureNetworkService,
    private val coroutineScope: CoroutineScope
    ) : FurnitureRepo {

    private val _furnitureResponseFlow: MutableStateFlow<FurnitureResponse> =
        MutableStateFlow(FurnitureResponse.Uninitialized)
    override val furnitureResponseFlow: Flow<FurnitureResponse> = _furnitureResponseFlow

    override fun refreshFurnitureData() {
        Log.d(TAG, "refreshFurnitureData()")

        coroutineScope.launch {
            val response = try {
                FurnitureResponse.Success(
                    furnitureNetworkService.getFurnitureData().furnitureDataList
                )
            } catch (e: Exception) {
                FurnitureResponse.Error(e.message ?: DEFAULT_ERROR_MESSAGE)
            }

            _furnitureResponseFlow.emit(response)
        }
    }
}