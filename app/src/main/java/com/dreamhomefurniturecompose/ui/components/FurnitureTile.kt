package com.dreamhomefurniturecompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.dreamhomefurniturecompose.ui.theme.DreamHomeBlue
import com.dreamhomefurniturecompose.ui.theme.DreamHomeTheme
import com.dreamhomefurniturecompose.ui.theme.Green
import com.dreamhomefurniturecompose.ui.theme.Montserrat
import com.example.dreamhomefurniturecompose.R

@Composable
fun FurnitureTile(
    imageUrl: String?,
    title: String,
    price: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = DreamHomeTheme.colors.background)
    ) {
        Image(
            painter = rememberImagePainter(data = imageUrl),
            contentDescription = null,
            modifier = Modifier.weight(5f)
        )

        Text(
            text = title,
            fontFamily = Montserrat,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = DreamHomeTheme.colors.onBackground,
            modifier = Modifier
                .background(color = DreamHomeTheme.colors.backgroundSecondary)
                .fillMaxWidth()
                .padding(4.dp)
                .weight(1f)
        )

        Row(
            modifier = Modifier
                .background(color = DreamHomeTheme.colors.backgroundSecondary)
                .padding(bottom = 8.dp)
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = stringResource(R.string.furniture_card_price_title),
                fontSize = 14.sp,
                fontFamily = Montserrat,
                textAlign = TextAlign.Center,
                color = DreamHomeTheme.colors.onBackground,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = price,
                fontSize = 14.sp,
                fontFamily = Montserrat,
                textAlign = TextAlign.Center,
                color = Green,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FurnitureTilePreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = DreamHomeBlue)
    ) {
        FurnitureTile(
            imageUrl = "https://cdn.shopify.com/s/files/1/0603/8362/5429/products/82604-38AB-SET_1048x700.jpg?v=1647870388",
            title = "Arcola RTA Sofa",
            price = "$749.99",
            modifier = Modifier
                .width(380.dp)
                .height(216.dp)
        )
    }
}