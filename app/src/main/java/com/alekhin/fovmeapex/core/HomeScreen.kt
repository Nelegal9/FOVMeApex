package com.alekhin.fovmeapex.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alekhin.fovmeapex.R
import com.alekhin.fovmeapex.authentication.AuthViewModel
import com.alekhin.fovmeapex.ui.theme.Typography

// region SCREEN

@Composable
fun HomeScreen(authViewModel: AuthViewModel) {
    Scaffold(topBar = {
        HomeTopBar()
    }) {
        HomeContent(paddingValues = it, authViewModel)
    }
}

// endregion

// region COMPONENTS

// region TOP BAR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar() {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.home))
    })
}

// endregion

// region CONTENT

@Composable
private fun HomeContent(paddingValues: PaddingValues, authViewModel: AuthViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(paddingValues = paddingValues), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier.padding(bottom = 32.dp))
        Text(text = "Welcome Back ${authViewModel.currentUser?.displayName.toString()}", modifier = Modifier.padding(bottom = 8.dp), fontSize = Typography.headlineMedium.fontSize)
        Text(text = "Email address is: ${authViewModel.currentUser?.email.toString()}", modifier = Modifier.padding(horizontal = 16.dp), fontSize = Typography.bodyLarge.fontSize, textAlign = TextAlign.Center)
    }
}

// endregion