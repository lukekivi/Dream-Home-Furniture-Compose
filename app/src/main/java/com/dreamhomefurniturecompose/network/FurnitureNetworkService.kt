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
                MccadeCouch,
                MabtonCouch,
                ArcolaSofa,
                CharentonSofa,
                BoxbergSofa,
                WorkhorseSofa
            )
        )
    }
}

private val MccadeCouch = FurnitureData(
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
            url = "https://cdn.shopify.com/s/files/1/0487/9319/0566/products/10104-88-77-94-T327_1200x864.jpg?v=1626800880",
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


private val MabtonCouch = FurnitureData(
    title = "Mabton 3-piece Power Reclining Sofa",
    price = 2301.99,
    vendorName = "Ashley",
    collection = "Mabton Black/Gray Collection",
    features = listOf(
        "Includes 3 pieces: armless chair, left-arm facing zero wall power recliner and right-arm facing zero-wall recliner",
        "Left-arm and \"right-arm\" describe the position of the arm when you face the piece",
        "One-touch power control with adjustable positions, Easy View™ adjustable headrest and USB plug-in",
        "Corner-blocked frame with metal reinforced seats"
    ),
    type = "Sofas",
    media = listOf(
        MediaData(
            url = "https://cdn.shopify.com/s/files/1/0538/1069/2285/products/studio_616b6ac16e25b2495621634433233_9929042d-cb49-4c5a-9399-7367da087f3e_1024x1024@2x.jpg?v=1635851683",
            width = 175,
            length = 200
        )
    ),
    overview = "The challenge: design a cutting-edge power reclining sectional you'd never guess was a recliner. Super sleek and streamlined, this reclining sectional in a taupe gray padded velvet might just be our best-kept secret. Rest assured, you'd be hard-pressed to find a modern sectional that looks so high end yet is so comfortably priced. Integrated elements include an Easy View™ adjustable headrest for low-profile style when it's down and a high level of comfort when you're kicking back and reclining. Factor in one-touch power controls (each with a USB port) placed on the inside track arm, and you have the makings for one beautifully deceptive reclining sectional.",
    productSpecifications = ProductSpecifications(
        brandName = "Signature Design by Ashley",
        color = "Black/Gray",
        consumerAssembly = "No Assembly Required",
        consumerDescription = "3-Piece Power Reclining Sofa",
        friendlyDescription = "3-Piece Power Reclining Sofa",
        itemCode = "Sectional",
        itemColorKey = 1690,
        itemName = "Mabton 3-Piece Power Reclining Sofa",
        itemType = "Mabton",
        itemWeightLbs = 307f,
        manufacturerWarrantyDays = 365,
        marxentPidNumber = "",
        retailType = "Motion Upholstery",
        seriesId = 77005,
        sku = "77005S7",
        unitDepthInches = 40,
        unitHeightInches = 31,
        unitWidthInches = 118
    )
)

private val ArcolaSofa = FurnitureData(
    title = "Arcola RTA Sofa",
    price = 749.99,
    vendorName = "Ashley",
    collection = "Arcola Java Collection",
    features = listOf(
        "Corner-blocked frame",
        "High-resiliency foam cushions wrapped in thick poly fiber over sinuous spring system",
        "Loose seat and reversible back cushions",
        "Polyester velvet upholstery"
    ),
    type = "Sofas",
    media = listOf(
        MediaData(
            url = "https://cdn.shopify.com/s/files/1/0603/8362/5429/products/82604-38AB-SET_1048x700.jpg?v=1647870388",
            width = 175,
            length = 200
        )
    ),
    overview = "The challenge: design a cutting-edge power reclining sectional you'd never guess was a recliner. Super sleek and streamlined, this reclining sectional in a taupe gray padded velvet might just be our best-kept secret. Rest assured, you'd be hard-pressed to find a modern sectional that looks so high end yet is so comfortably priced. Integrated elements include an Easy View™ adjustable headrest for low-profile style when it's down and a high level of comfort when you're kicking back and reclining. Factor in one-touch power controls (each with a USB port) placed on the inside track arm, and you have the makings for one beautifully deceptive reclining sectional.",
    productSpecifications = ProductSpecifications(
        brandName = "Signature Design by Ashley",
        color = "Java",
        consumerAssembly = "Assembly Required",
        consumerDescription = "RTA Sofa",
        friendlyDescription = "RTA Sofa",
        itemCode = "Sofa",
        itemColorKey = 2044,
        itemName = "Arcola RTA Sofa",
        itemType = "Arcola",
        itemWeightLbs = 103f,
        manufacturerWarrantyDays = 365,
        marxentPidNumber = "ASH8260438",
        retailType = "Stationary Upholstery",
        seriesId = 82604,
        sku = "82604S1",
        unitDepthInches = 34,
        unitHeightInches = 36,
        unitWidthInches = 79
    )
)


private val CharentonSofa = FurnitureData(
    title = "Charenton Sofa",
    price = 792.99,
    vendorName = "Benchcraft",
    collection = "Charenton Charcoal Collection",
    features = listOf(
        "Corner-blocked frame",
        "Attached back and loose seat cushions",
        "High-resiliency foam cushions wrapped in thick poly fiber",
        "2 toss pillows included"
    ),
    type = "Sofas",
    media = listOf(
        MediaData(
            url = "https://static.homelivingfurniture.com/data/vendors/8/items/256845/big/1410138.jpg",
            width = 175,
            length = 200
        )
    ),
    overview = "Wrapped in a decadently plush charcoal fabric, this sumptuous sofa-with roll back arms for distinctive flair-is so easy to fall for. Adding to the feel-good experience: four toss pillows that bring tone, texture and comfort to the scene.",
    productSpecifications = ProductSpecifications(
        brandName = "Benchcraft",
        color = "Charcoal",
        consumerAssembly = "No Assembly Required",
        consumerDescription = "Sofa",
        friendlyDescription = "Sofa",
        itemCode = "Sofa",
        itemColorKey = 1837,
        itemName = "Charenton Sofa",
        itemType = "Charenton",
        itemWeightLbs = 133f,
        manufacturerWarrantyDays = 365,
        marxentPidNumber = "ASH1410138",
        retailType = "Stationary Upholstery",
        seriesId = 14101,
        sku = "1410138",
        unitDepthInches = 41,
        unitHeightInches = 39,
        unitWidthInches = 93
    )
)


private val BoxbergSofa = FurnitureData(
    title = "Boxberg Power Reclining Sofa",
    price = 1228.99,
    vendorName = "Ashley",
    collection = "Boxberg Bark Collection",
    features = listOf(
        "Dual-sided recliner; middle seat remains stationary",
        "One-touch power control with adjustable positions",
        "Corner-blocked frame with metal reinforced seat",
        "Attached cushions"
    ),
    type = "Sofas",
    media = listOf(
        MediaData(
            url = "https://smhttp-ssl-77687.nexcesscdn.net/media/catalog/product/cache/1/image/650x650/9df78eab33525d08d6e5fb8d27136e95/3/3/3380287-sofa-1.jpg",
            width = 175,
            length = 200
        )
    ),
    overview = "Rock the cool look of leather minus the hefty price tag with this power reclining sofa. Rich with tonal variation, the indulgently soft upholstery sure looks like the real thing. And talk about indulgent comfort. Double stuffed armrests and sumptuous cushions cradle and support in all the right places. Distinctive scooped stitching enhances this reclining sofa's contemporary flair.",
    productSpecifications = ProductSpecifications(
        brandName = "Signature Design by Ashley",
        color = "Bark",
        consumerAssembly = "Assembly Required",
        consumerDescription = "Power Reclining Sofa",
        friendlyDescription = "Reclining Power Sofa",
        itemCode = "Sofa",
        itemColorKey = 1653,
        itemName = "Boxberg Power Reclining Sofa",
        itemType = "Boxberg",
        itemWeightLbs = 214f,
        manufacturerWarrantyDays = 365,
        marxentPidNumber = "ASH3380287",
        retailType = "Motion Upholstery",
        seriesId = 33802,
        sku = "3380287",
        unitDepthInches = 41,
        unitHeightInches = 41,
        unitWidthInches = 93
    )
)


private val WorkhorseSofa = FurnitureData(
    title = "Workhorse Reclining Sofa",
    price = 909.99,
    vendorName = "Ashley",
    collection = "Workhorse Cocoa Collection",
    features = listOf(
        "Dual-sided recliner; middle seat remains stationary",
        "Pull tab reclining motion",
        "Corner-blocked frame with metal reinforced seat",
        "Attached back and seat cushions"
    ),
    type = "Sofas",
    media = listOf(
        MediaData(
            url = "https://ashleyfurniture.scene7.com/is/image/AshleyFurniture/5840188-ALT-10x8-CROP?\$AFHS-PDP-Main\$",
            width = 175,
            length = 200
        )
    ),
    overview = "Saddle up for affordably priced luxury with this reclining sofa. Stylish elements of this contemporary reclining sofa include biscuit back cushioning and incredibly thick and sumptuous pillow top armrests. Ultra neutral and wonderfully plush, the warm taupe upholstery is sure to complement an endless array of color schemes. Discreetly placed (inside arm) reclining tab maintains streamlined style.",
    productSpecifications = ProductSpecifications(
        brandName = "Signature Design by Ashley",
        color = "Cocoa",
        consumerAssembly = "No Assembly Required",
        consumerDescription = "Reclining Sofa",
        friendlyDescription = "Reclining Sofa",
        itemCode = "Sofa",
        itemColorKey = 1860,
        itemName = "Workhorse Reclining Sofa",
        itemType = "Workhorse",
        itemWeightLbs = 216f,
        manufacturerWarrantyDays = 365,
        marxentPidNumber = "ASH5840188",
        retailType = "Motion Upholstery",
        seriesId = 58401,
        sku = "5840188",
        unitDepthInches = 41,
        unitHeightInches = 42,
        unitWidthInches = 87
    )
)
