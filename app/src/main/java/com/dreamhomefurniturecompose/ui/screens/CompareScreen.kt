package com.dreamhomefurniturecompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamhomefurniturecompose.ui.components.BackButtonIcon
import com.dreamhomefurniturecompose.ui.components.DreamHomeTopBar
import com.dreamhomefurniturecompose.ui.components.FurnitureTile
import com.dreamhomefurniturecompose.ui.theme.DreamHomeBlue
import com.dreamhomefurniturecompose.ui.theme.DreamHomeTheme
import com.dreamhomefurniturecompose.viewmodels.CompareScreenViewModelImpl
import com.example.dreamhomefurniturecompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareScreen(
    vm: CompareScreenViewModelImpl = hiltViewModel(),
    onNavClick: (String) -> Unit,
    onBackNavClick: () -> Unit
) {
    val compareScreenContent: CompareScreenContent by vm.compareScreenContentFlow.collectAsState(DefaultCompareScreenContent)

    Scaffold(
        topBar = {
            DreamHomeTopBar { BackButtonIcon { onBackNavClick() } }
        }
    ) {
        if (compareScreenContent.isLoading) {
            /**
             * Handle loading
             */
        } else {
            compareScreenContent.compareScreenState.let { compareScreenState ->
                when (compareScreenState) {
                    is CompareScreenState.Success -> {
                        CompareDetails(
                            compareScreenDataOne = compareScreenState.itemOne,
                            compareScreenDataTwo = compareScreenState.itemTwo,
                            paddingValues = it
                        )
                    }
                    else -> {
                        /**
                         * Handle other states
                         */
                    }
                }
            }
        }
    }
}

@Composable
fun CompareDetails(
    compareScreenDataOne: CompareScreenData,
    compareScreenDataTwo: CompareScreenData,
    paddingValues: PaddingValues
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(start = 8.dp, end = 8.dp)
            .verticalScroll(state = rememberScrollState())
    ) {
        /**
         * Title Section
         */
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(
                        color = DreamHomeTheme.colors.headerBackground,
                        shape = RoundedCornerShape(bottomStart = 5.dp, bottomEnd = 5.dp)
                    )
            ) {
                Text(
                    text = stringResource(R.string.comparing_title),
                    style = DreamHomeTheme.typography.screenHeader,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        /**
         * Product Section
         */
        Text(
            text = stringResource(R.string.product_title),
            style = DreamHomeTheme.typography.bodyHeader1,
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                .fillMaxWidth()
        )

        Divider(
            color = DreamHomeTheme.colors.onBackground,
            thickness = 1.dp,
            modifier = Modifier.padding(8.dp)
        )

        FurnitureTile(
            imageUrl = compareScreenDataOne.imageUrl,
            title = compareScreenDataOne.title,
            price = compareScreenDataOne.price,
            modifier = Modifier
                .width(380.dp)
                .height(216.dp)
                .padding(top = 8.dp, bottom = 8.dp)
                .shadow(8.dp)
        )

        FurnitureTile(
            imageUrl = compareScreenDataTwo.imageUrl,
            title = compareScreenDataTwo.title,
            price = compareScreenDataTwo.price,
            modifier = Modifier
                .width(380.dp)
                .height(216.dp)
                .padding(top = 8.dp, bottom = 8.dp)
                .shadow(8.dp)
        )

        /**
         * Details Section
         */
        Text(
            text = stringResource(R.string.details_title),
            style = DreamHomeTheme.typography.bodyHeader1,
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                .fillMaxWidth()
        )

        Divider(
            color = DreamHomeTheme.colors.onBackground,
            thickness = 1.dp,
            modifier = Modifier.padding(8.dp)
        )

        DetailsRow(
            titleResId = R.string.details_product_name,
            detailOne = compareScreenDataOne.title,
            detailTwo = compareScreenDataTwo.title,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )

        Divider(
            color = DreamHomeTheme.colors.onBackground,
            thickness = 0.5.dp,
            modifier = Modifier.padding(8.dp)
        )

        DetailsRow(
            titleResId = R.string.details_brand_name,
            detailOne = compareScreenDataOne.brand,
            detailTwo = compareScreenDataTwo.brand,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )

        Divider(
            color = DreamHomeTheme.colors.onBackground,
            thickness = 0.5.dp,
            modifier = Modifier.padding(8.dp)
        )

        DetailsRow(
            titleResId = R.string.details_collection,
            detailOne = compareScreenDataOne.collection,
            detailTwo = compareScreenDataTwo.collection,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )

        Divider(
            color = DreamHomeTheme.colors.onBackground,
            thickness = 0.5.dp,
            modifier = Modifier.padding(8.dp)
        )

        DetailsRow(
            titleResId = R.string.details_overview,
            detailOne = compareScreenDataOne.overview,
            detailTwo = compareScreenDataTwo.overview,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )
    }
}

/**
 * todo: this should probably done via a datastructure with a titleResId connected.
 * That way the app is set up for a generic number of details (List of [DetailData]). All assembly logic
 * can then be handled in the VM
 */

@Composable
fun DetailsRow(
    titleResId: Int,
    detailOne: String,
    detailTwo: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = stringResource(id = titleResId),
            style = DreamHomeTheme.typography.bodyHeader2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = detailOne,
                style = DreamHomeTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            )

            Text(
                text = detailTwo,
                style = DreamHomeTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            )
        }
    }
}

data class CompareScreenData(
    val title: String,
    val price: String,
    val brand: String,
    val collection: String,
    val overview: String,
    val imageUrl: String?
)

sealed class CompareScreenState {
    object Uninitialized: CompareScreenState()
    object NoMatch: CompareScreenState()
    class Error(val msg: String): CompareScreenState()
    class Success(val itemOne: CompareScreenData, val itemTwo: CompareScreenData): CompareScreenState()
}

data class CompareScreenContent(
    val compareScreenState: CompareScreenState,
    val isLoading: Boolean
)

val DefaultCompareScreenContent = CompareScreenContent(
    compareScreenState = CompareScreenState.Uninitialized,
    isLoading = false
)