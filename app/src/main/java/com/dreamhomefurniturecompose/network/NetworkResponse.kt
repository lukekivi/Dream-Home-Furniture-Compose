package com.dreamhomefurniturecompose.network

data class NetworkResponse(
    val furnitureDataList: List<FurnitureData>
)

data class FurnitureData(
    val title: String,
    val price: Double,
    val vendorName: String,
    val collection: String,
    val features: List<String>,
    val type: String,
    val media: List<MediaData>,
    val overview: String,
    val productSpecifications: ProductSpecifications
)

data class MediaData(
    val url: String,
    val width: Int,
    val length: Int
)

data class ProductSpecifications(
    val brandName: String,
    val color: String,
    val consumerAssembly: String,
    val consumerDescription: String,
    val friendlyDescription: String,
    val itemCode: String,
    val itemColorKey: Int,
    val itemName: String,
    val itemType: String,
    val itemWeightLbs: Float,
    val manufacturerWarrantyDays: Int,
    val marxentPidNumber: String,
    val retailType: String,
    val seriesId: Int,
    val sku: String,
    val unitDepthInches: Int,
    val unitHeightInches: Int,
    val unitWidthInches: Int,
)