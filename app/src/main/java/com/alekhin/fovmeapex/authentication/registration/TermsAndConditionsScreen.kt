package com.alekhin.fovmeapex.authentication.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alekhin.fovmeapex.R

// region SCREEN

@Composable
fun TermsAndConditionsScreen(navController: NavController) {
    Scaffold(topBar = {
        TermsAndConditionsTopBar {
            navController.navigateUp()
        }
    }) {
        TermsAndConditionsContent(paddingValues = it)
    }
}

// endregion

// region COMPONENTS


// region TOP BAR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TermsAndConditionsTopBar(onClick: () -> Unit) {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.terms_and_conditions))
    }, navigationIcon = {
        IconButton(onClick = onClick) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    })
}

// endregion

// region CONTENT

@Composable
private fun TermsAndConditionsContent(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
    ) {
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

// endregion