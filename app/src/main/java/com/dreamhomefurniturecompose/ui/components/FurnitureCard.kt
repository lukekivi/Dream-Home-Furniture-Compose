package com.dreamhomefurniturecompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.dreamhomefurniturecompose.ui.theme.DreamHomeTheme
import com.dreamhomefurniturecompose.ui.theme.Green
import com.dreamhomefurniturecompose.ui.theme.Montserrat
import com.example.dreamhomefurniturecompose.R

@Composable
fun FurnitureCard(
    data: FurnitureCardData,
    setCompare: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .height(210.dp)
            .border(
                width = .5.dp,
                color = DreamHomeTheme.colors.onBackgroundAccent,
                shape = RoundedCornerShape(8.dp)
            )
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
                    style = DreamHomeTheme.typography.cardHeader,
                    modifier = Modifier.padding(bottom = 5.dp)
                )

                Text(
                    text = data.vendorTitle,
                    style = DreamHomeTheme.typography.cardSubHeader1,
                )
                
                Text(
                    text = data.collection,
                    style = DreamHomeTheme.typography.cardSubHeader2,
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
                    color = DreamHomeTheme.colors.onBackground
                )

                Text(
                    text = data.price,
                    fontSize = 14.sp,
                    fontFamily = Montserrat,
                    color = Green
                )
            }

            Button(
                onClick = { setCompare() },
                colors = ButtonDefaults.buttonColors(containerColor = DreamHomeTheme.colors.buttonBackground)
            ) {
                Text(
                    text = stringResource(R.string.furniture_card_compare_button),
                    style = DreamHomeTheme.typography.button
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
        setCompare = {},
        onClick = {}
    )
}