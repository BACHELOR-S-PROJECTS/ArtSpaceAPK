package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

data class ArtworkInfo(
    val artwork: Int,
    val titleResId: Int,
    val yearResId: Int,
    val descriptionId: Int
)


@OptIn(ExperimentalStdlibApi::class)
@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {

    val firstArtwork = R.drawable.denji_face
    val secondArtwork = R.drawable.zero_two_face
    val thirdArtwork = R.drawable.sanji_face
    val fourthArtwork = R.drawable.naruto_face

    val context = LocalContext.current
    val previousButtonText = context.getString(R.string.previous)
    val nextButtonText = context.getString(R.string.next)

    val artworkInfoList = listOf(
        ArtworkInfo(firstArtwork, R.string.denji, R.string.denji_year, R.string.denji_description),
        ArtworkInfo(secondArtwork, R.string.zero_two, R.string.zero_two_year,R.string.zero_two_description),
        ArtworkInfo(thirdArtwork, R.string.sanji, R.string.sanji_year,R.string.sanji_description),
        ArtworkInfo(fourthArtwork, R.string.naruto, R.string.naruto_year,R.string.naruto_description)
    )

    var position by remember {
        mutableStateOf( 0 )
    }
    var title by remember {
        mutableStateOf(artworkInfoList[position].titleResId)
    }
    var year by remember {
        mutableStateOf(artworkInfoList[position].yearResId)
    }
    var description by remember {
        mutableStateOf(artworkInfoList[position].descriptionId)
    }

    var currentArtwork by remember {
        mutableStateOf(artworkInfoList[position].artwork)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        ArtworkImage(
            currentArtwork = currentArtwork
        )
        Spacer(
            modifier = modifier.size(32.dp)
        )
        ArtworkTitle(
            title = title,
            year = year,
            description = description
        )
        Spacer(
            modifier = modifier.size(25.dp)
        )
        Row(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(50.dp)
        ) {
            Button(
                onClick = {
                    when (position) {
                        in 1..<artworkInfoList.size -> {
                            position -= 1
                            currentArtwork = artworkInfoList[position].artwork
                            title = artworkInfoList[position].titleResId
                            year = artworkInfoList[position].yearResId
                            description = artworkInfoList[position].descriptionId
                        }

                        else -> {
                            position = artworkInfoList.size - 1
                            currentArtwork = artworkInfoList[position].artwork
                            title = artworkInfoList[position].titleResId
                            year = artworkInfoList[position].yearResId
                            description = artworkInfoList[position].descriptionId
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.red)
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp,
                )
            ) {
                Text(
                    text = previousButtonText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white)
                )
            }
            Button(
                onClick = {
                    position = 0
                    currentArtwork = artworkInfoList[0].artwork
                    title = artworkInfoList[0].titleResId
                    year = artworkInfoList[0].yearResId
                    description = artworkInfoList[0].descriptionId
                },
                modifier = Modifier
                    .padding(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.red)
                )
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.restart),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )

            }
            Button(
                onClick = {
                    when (position) {
                        in 0..artworkInfoList.size -2 -> {
                            position += 1
                            currentArtwork = artworkInfoList[position].artwork
                            title = artworkInfoList[position].titleResId
                            year = artworkInfoList[position].yearResId
                            description = artworkInfoList[position].descriptionId
                        }

                        else -> {
                            position = 0
                            currentArtwork = artworkInfoList[position].artwork
                            title = artworkInfoList[position].titleResId
                            year = artworkInfoList[position].yearResId
                            description = artworkInfoList[position].descriptionId
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.red)
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                ),
            ) {
                Text(
                    text = nextButtonText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}

@Composable
fun ArtworkImage(
    modifier: Modifier = Modifier,
    @DrawableRes currentArtwork: Int
) {
    Box(
        modifier = modifier
            .size(350.dp) // Adjust the size as needed
            .graphicsLayer(
                clip = true,
                shape = RoundedCornerShape(32f)
            )
    ) {
        Image(
            painter = painterResource(id = currentArtwork),
            contentDescription = null,
            modifier = modifier
                .size(500.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun ArtworkTitle(
    @StringRes title: Int,
    @StringRes year: Int,
    @StringRes description: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.marron),
            fontSize = 32.sp,
        )
        Text(
            text = stringResource(id = year),
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.gray_300),
            fontSize = 16.sp,
        )
        Text(
            text = stringResource(id = description),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.verde_oscuro),
            fontSize = 20.sp,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}

