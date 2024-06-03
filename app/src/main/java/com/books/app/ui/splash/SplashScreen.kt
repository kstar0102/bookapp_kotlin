package com.books.app.ui.splash

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.books.app.ui.main.MainActivity
import com.books.app.R
import com.books.app.theme.Typography
import com.books.app.util.VerticalSpacer
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
    var startMainScreen by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        delay(2000)
        startMainScreen = true
    }
    if (startMainScreen) {
        val context = LocalContext.current
        context.startActivity(Intent(context, MainActivity::class.java))
    } else {
        SplashScreenContent()
    }
}

@Composable
fun SplashScreenContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.bookapp),
                style = Typography.h1
                )
            VerticalSpacer(8)
            Text(
                text = stringResource(id = R.string.welcometobookapp),
                style = Typography.h5
            )
            VerticalSpacer(32)
            LinearProgressIndicator(
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(4.dp)
            )
        }
    }
}