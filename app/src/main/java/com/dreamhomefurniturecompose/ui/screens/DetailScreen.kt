package com.dreamhomefurniturecompose.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.dreamhomefurniturecompose.ui.components.BackButtonIcon
import com.dreamhomefurniturecompose.ui.components.DreamHomeTopBar
import com.dreamhomefurniturecompose.ui.theme.DreamHomeTheme
import com.dreamhomefurniturecompose.ui.theme.Montserrat
import com.dreamhomefurniturecompose.viewmodels.DetailScreenViewModelImpl
import com.example.dreamhomefurniturecompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    vm: DetailScreenViewModelImpl = hiltViewModel(),
    onNavClick: () -> Unit
) {
    val detailScreenContent by vm.detailScreenContentFlow.collectAsState(initial = DefaultDetailScreenContent)

    Scaffold(
        topBar = {
            DreamHomeTopBar { BackButtonIcon { onNavClick() } }
        }
    ) {
        if (detailScreenContent.isLoading) {
            /**
             * Handle Loading
             */
        } else {
            detailScreenContent.detailScreenState.let { detailScreenState ->
                when (detailScreenState) {
                    is DetailScreenState.Success -> {
                        FurnitureDetails(
                            detailScreenData = detailScreenState.detailScreenData,
                            padding = it
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
fun FurnitureDetails(
    detailScreenData: DetailScreenData,
    padding: PaddingValues
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
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
                        color = colorResource(id = R.color.dream_home_blue),
                        shape = RoundedCornerShape(bottomStart = 5.dp, bottomEnd = 5.dp)
                    )
            ) {
                Text(
                    text = detailScreenData.title,
                    style = DreamHomeTheme.typography.screenHeader,
                    modifier = Modifier.padding(
                        start = 5.dp,
                        top = 10.dp,
                        end = 5.dp,
                        bottom = 10.dp
                    )
                )

                Divider(
                    color = colorResource(id = R.color.white),
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth(.9f)
                )

                Text(
                    text = detailScreenData.vendorTitle,
                    style = DreamHomeTheme.typography.screenSubHeader1,
                    modifier = Modifier.padding(top = 5.dp)
                )

                Text(
                    text = detailScreenData.collection,
                    style = DreamHomeTheme.typography.screenSubHeader2,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
            }
        }

        /**
         * Image
         */
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.detail_image_container_height))
                .padding(bottom = 8.dp, top = 8.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = detailScreenData.imageUrl),
                contentDescription = null,
            )
        }

        /**
         * Price and add to cart
         */
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize()
                .background(color = colorResource(id = R.color.light_gray))
                .border(
                    width = 0.25.dp,
                    color = colorResource(id = R.color.half_black),
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.furniture_card_price_title),
                        fontSize = 18.sp,
                        fontFamily = Montserrat,
                        color = colorResource(id = R.color.black)
                    )

                    Text(
                        text = detailScreenData.price,
                        fontSize = 18.sp,
                        fontFamily = Montserrat,
                        color = colorResource(id = R.color.green)
                    )
                }

                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.dream_home_blue))
                ) {
                    Text(
                        text = stringResource(R.string.add_to_cart_button),
                        style = DreamHomeTheme.typography.button
                    )
                }
            }
        }

        /**
         * Overview
         */
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentSize()
        ) {
            Column(
                modifier = Modifier.wrapContentSize()
            ) {
                Text(
                    text = stringResource(R.string.overview_title),
                    style = DreamHomeTheme.typography.bodyHeader1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, top = 16.dp)
                )

                Text(
                    text = detailScreenData.overview,
                    style = DreamHomeTheme.typography.body1,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        /**
         * Features
         */
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentSize()
        ) {
            Column(
                modifier = Modifier.wrapContentSize()
            ) {
                if (detailScreenData.features.isNotEmpty()) {
                    Text(
                        text = stringResource(R.string.features_title),
                        style = DreamHomeTheme.typography.bodyHeader1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp, top = 16.dp)
                    )

                    detailScreenData.features.forEach { FeatureRow(it) }
                }
            }
        }

        /**
         * Specs
         */
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentSize()
        ) {
            Column(
                modifier = Modifier.wrapContentSize()
            ) {
                if (detailScreenData.specs.isNotEmpty()) {
                    Text(
                        text = stringResource(R.string.specs_title),
                        style = DreamHomeTheme.typography.bodyHeader1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp, top = 16.dp)
                    )

                    detailScreenData.specs.forEachIndexed { index, spec ->
                        SpecificationRow(
                            specs = spec,
                            modifier = Modifier
                                .background(color = colorResource(id = if (index % 2 == 0) R.color.white else R.color.light_gray))
                                .padding(bottom = 4.dp, top = 4.dp, start = 8.dp, end = 8.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Composable
fun FeatureRow(
    feature: String
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.wrapContentSize()
    ) {
        /**
         * Bullet and double tab. Is separate from feature text because if the feature takes multiple lines
         * then line 2+ are also indented.
         */
        Text(
            text = "\u2022\t\t",
            style = DreamHomeTheme.typography.body1,
        )

        Text(
            text = feature,
            style = DreamHomeTheme.typography.body1,
        )
    }
}


@Composable
fun SpecificationRow(
    specs: DetailScreenSpecs,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(specs.categoryResId),
            style = DreamHomeTheme.typography.bodyHeader2,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = specs.specValue,
            style = DreamHomeTheme.typography.body2,
            modifier = Modifier.weight(1f)
        )
    }
}


data class DetailScreenContent(
    val isLoading: Boolean,
    val detailScreenState: DetailScreenState
)

sealed class DetailScreenState {
    object NoMatch : DetailScreenState()
    object Uninitialized : DetailScreenState()
    class Success(val detailScreenData: DetailScreenData) : DetailScreenState()
    class Error(val message: String) : DetailScreenState()
}


data class DetailScreenData(
    val title: String,
    val price: String,
    val vendorTitle: String,
    val collection: String,
    val features: List<String>,
    val imageUrl: String?,
    val overview: String,
    val specs: List<DetailScreenSpecs>
)


data class DetailScreenSpecs(
    val categoryResId: Int,
    val specValue: String
)


val DesignSpecIds = listOf(
    R.string.brand_name,
    R.string.color,
    R.string.consumer_assembly,
    R.string.consumer_description,
    R.string.friendly_description,
    R.string.item_code,
    R.string.item_type,
    R.string.item_weight_lbs,
    R.string.manufacturer_warranty_days,
    R.string.marxent_pid_number,
    R.string.retail_type,
    R.string.series_id,
    R.string.sku,
    R.string.unit_depth_in,
    R.string.unit_height_in,
    R.string.unit_width_in
)


val DefaultDetailScreenContent = DetailScreenContent(
    isLoading = false,
    detailScreenState = DetailScreenState.Success(
        detailScreenData = DetailScreenData(
            title = "",
            price = "",
            vendorTitle = "",
            collection = "",
            features = emptyList(),
            imageUrl = "",
            overview = "",
            specs = DesignSpecIds.map {
                DetailScreenSpecs(it, "")
            }
        )
    )
)
