package com.dreamhomefurniturecompose.data

import com.dreamhomefurniturecompose.network.FurnitureData

sealed class FurnitureResponse {
    object Uninitialized : FurnitureResponse()
    class Success(val simpleFurnitureDataList: List<FurnitureData>) : FurnitureResponse()
    class Error(val message: String) : FurnitureResponse()
}
