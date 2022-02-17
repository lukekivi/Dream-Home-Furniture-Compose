package com.dreamhomefurniturecompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.dreamhomefurniturecompose.ui.theme.Montserrat
import com.example.dreamhomefurniturecompose.R

@Composable
fun FurnitureCard(
    data: FurnitureCardData,
    setCompare: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(3f)
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = rememberImagePainter(data = data.imageUrl),
                contentDescription = null
            )
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(4f)
                .padding(8.dp)
                .fillMaxSize()
        ) {

            Column {
                Text(
                    text = data.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Montserrat,
                    color = colorResource(id = R.color.dream_home_blue),
                    modifier = Modifier.padding(bottom = 5.dp)
                )

                Text(
                    text = data.vendorTitle,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Montserrat,
                    color = colorResource(id = R.color.dream_home_gray)
                )
                
                Text(
                    text = data.collection,
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    color = colorResource(id = R.color.dream_home_gray)
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(.8f)
            ) {
                Text(
                    text = stringResource(R.string.furniture_card_price_title),
                    fontSize = 14.sp,
                    fontFamily = Montserrat,
                    color = colorResource(id = R.color.black)
                )

                Text(
                    text = data.price,
                    fontSize = 14.sp,
                    fontFamily = Montserrat,
                    color = colorResource(id = R.color.green)
                )
            }

            Button(
                onClick = { setCompare() },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.dream_home_blue))
            ) {
                Text(
                    text = stringResource(R.string.furniture_card_compare_button),
                    fontSize = 14.sp,
                    fontFamily = Montserrat,
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}

data class FurnitureCardData(
    val title: String,
    val price: String,
    val vendorTitle: String,
    val collection: String,
    val imageUrl: String?
)

@Preview(showBackground = true)
@Composable
private fun FurnitureCardPreview() {
    FurnitureCard(
        data = FurnitureCardData(
            title = "Mccade 3-piece Reclining Sectional",
            price = "$2,241.99",
            vendorTitle = "Signature Design by Ashley",
            collection = "Mccade Cobblestone Collection",
            imageUrl = "https://cdn.theclassyhome.com/600x600/ASH-10104-88-77-94-SW.jpg"
        ),
        setCompare = {}
    )
}