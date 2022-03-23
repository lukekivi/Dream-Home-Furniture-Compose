package com.dreamhomefurniturecompose.network

import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

interface FurnitureNetworkService {
    suspend fun getFurnitureData(): NetworkResponse
}

@Singleton
class FurnitureNetworkServiceImpl @Inject constructor() : FurnitureNetworkService {
    override suspend fun getFurnitureData(): NetworkResponse {
        delay(1000)

        return NetworkResponse(
            furnitureDataList = listOf(
                FurnitureData(
                    title = "Mccade 3-piece Reclining Sectional",
                    price = 2241.99,
                    vendorName = "Ashley",
                    collection = "Mccade Cobblestone Collection",
                    features = listOf(
                        "Corner-blocked frame with metal reinforced seat",
                        "Attached back and seat cushions",
                        "Pull tab reclining motion",
                        "Reclining loveseat: lift-top storage console and 2 cup holders"
                    ),
                    type = "Sectionals",
                    media = listOf(
                        MediaData(
                            url = "https://ashleyfurniture.scene7.com/is/image/AshleyFurniture/10104-88-77-94-T327?AFHS-PDP-Main",
                            width = 175,
                            length = 200
                        )
                    ),
                    overview = "Comfort is yours for the taking with this 3-piece reclining sectional. Supportive back is plush in all the right places, enhancing your seating experience. Feel-good fabric is elevated with detailed stitching. Rounded sides are fashion-forward and topped with pillowy armrests. Stash away essentials in the center console. With one easy pull, you're on your way to endless relaxation.",
                    productSpecifications = ProductSpecifications(
                        brandName = "Signature Design by Ashley",
                        color = "Cobblestone",
                        consumerAssembly = "Assembly Required",
                        consumerDescription = "3-Piece Reclining Sectional",
                        friendlyDescription = "3-Piece Reclining Sectional",
                        itemCode = "Sectional",
                        itemColorKey = 1859,
                        itemName = "McCade 3-Piece Reclining Sectional",
                        itemType = "McCade",
                        itemWeightLbs = 416f,
                        manufacturerWarrantyDays = 365,
                        marxentPidNumber = "ASH10104S1",
                        retailType = "Motion Sectionals",
                        seriesId = 10104,
                        sku = "10104S1",
                        unitDepthInches = 130,
                        unitHeightInches = 42,
                        unitWidthInches = 132
                    )
                )
            )
        )
    }

}