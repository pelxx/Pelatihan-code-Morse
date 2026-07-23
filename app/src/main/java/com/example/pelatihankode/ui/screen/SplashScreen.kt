package com.example.pelatihankode.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.pelatihankode.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.animation.core.tween
@Composable
fun SplashScreen(
    navController: NavController
) {
    var visible by remember {

        mutableStateOf(false)

    }
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.7f,
        label = "logoScale"
    )

    LaunchedEffect(Unit) {
        visible = true
        delay(2000)

        navController.navigate("sop") {

            popUpTo("splash") {
                inclusive = true
            }

        }
    }
    AnimatedVisibility(

        visible = visible,

        enter = fadeIn(
            animationSpec = tween(800)
        )
    ){ Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Image(
            painter = painterResource(id = R.mipmap.ic_launcher_foreground),
            contentDescription = "Logo",
            modifier = Modifier
                .size(170.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
        )


        Text(
            text = "MORSE GO",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Media Pembelajaran Kode Morse",
            fontSize = 18.sp,
            fontFamily = DynapufMenu
        )

        Text(
            text = "Version 1.0",
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            fontStyle = Italic
        )
        Spacer(
            Modifier.height(40.dp)
        )

        CircularProgressIndicator()

        Spacer(
            Modifier.height(12.dp)
        )

        Text(
            "Loading..."
        )
    }
    }

}