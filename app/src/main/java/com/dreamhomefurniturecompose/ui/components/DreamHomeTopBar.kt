package com.dreamhomefurniturecompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dreamhomefurniturecompose.R

@Composable
fun DreamHomeTopBar(
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit) = {}
) {
    TopAppBar(
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.white))
                .padding(top = 4.dp, bottom = 4.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                navigationIcon()
            }
            
            Image(
                painter = painterResource(id = R.drawable.dreamhomeicon),
                contentDescription = null,
                modifier = modifier
                    .weight(6f)
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


@Composable
fun BackButtonIcon(
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null
        )
    }
}