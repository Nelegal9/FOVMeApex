package com.alekhin.fovmeapex.authentication.registration

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alekhin.fovmeapex.R
import com.alekhin.fovmeapex.authentication.AuthCard
import com.alekhin.fovmeapex.authentication.AuthViewModel
import com.alekhin.fovmeapex.authentication.Resource
import com.alekhin.fovmeapex.navigation.graph.Graph
import com.alekhin.fovmeapex.navigation.screen.Screen
import com.alekhin.fovmeapex.ui.theme.Typography

// region SCREEN

@Composable
fun RegistrationScreen(navController: NavController, authViewModel: AuthViewModel) {
    RegistrationContent(navController = navController, authViewModel = authViewModel)
}

// endregion

// region COMPONENTS

// region CONTENT

@Composable
private fun RegistrationContent(navController: NavController, authViewModel: AuthViewModel) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        RegistrationHeader()
        RegistrationBody(navController = navController, authViewModel = authViewModel)
    }
}

// endregion

// region HEAD

@Composable
private fun RegistrationHeader() {
    Image(painter = painterResource(id = R.drawable.logo), contentDescription = null, modifier = Modifier.padding(bottom = 32.dp))
    Text(text = stringResource(id = R.string.app_name), modifier = Modifier.padding(bottom = 32.dp), fontSize = Typography.headlineLarge.fontSize)
    Text(text = stringResource(id = R.string.sign_up), fontSize = Typography.headlineMedium.fontSize, modifier = Modifier.padding(bottom = 8.dp))
}

// endregion

// region BODY

@Composable
private fun RegistrationBody(navController: NavController, authViewModel: AuthViewModel) {
    val username = remember { mutableStateOf(value = "") }
    val email = remember { mutableStateOf(value = "") }
    val password = remember { mutableStateOf(value = "") }
    val context = LocalContext.current

    val registerFlow = authViewModel.registerFlow.collectAsState()
    
    UsernameCard(username = username)
    EmailCard(email = email)
    PasswordCard(password = password)
    AgreementCheckBox(navController = navController)
    SignUpButton {
        authViewModel.register(username.value, email.value, password.value)
    }

    SignInTextButton {
        navController.navigateUp()
    }

    registerFlow.value.let {
        when (it) {
            is Resource.Error -> {
                Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
            }
            Resource.Loading -> {}
            is Resource.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(route = Graph.MAIN) {
                        popUpTo(route = Graph.MAIN) {
                            inclusive = true
                        }
                    }
                }
            }

            null -> {}
        }
    }
}

// endregion

// region USERNAME TEXT FIELD

@Composable
private fun UsernameCard(username: MutableState<String>) {
    AuthCard(id = R.string.user_name, input = username, leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) }, VisualTransformation.None, keyboardType = KeyboardType.Email)
}

// endregion

// region EMAIL TEXT FIELD

@Composable
private fun EmailCard(email: MutableState<String>) {
    AuthCard(id = R.string.email, input = email, leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) }, VisualTransformation.None, keyboardType = KeyboardType.Email)
}

// endregion

// region PASSWORD TEXT FIELD

@Composable
private fun PasswordCard(password: MutableState<String>) {
    AuthCard(id = R.string.password, input = password, leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) }, visualTransformation = PasswordVisualTransformation(), keyboardType = KeyboardType.Password)
}

// endregion

// region AGREEMENT CHECK BOX

@Composable
private fun AgreementCheckBox(navController: NavController) {
    var checked by remember { mutableStateOf(false) }
    Row {
        Checkbox(checked = checked, onCheckedChange = { checked = !checked })
        Text(text = "By signing up you accept the ", modifier = Modifier.align(Alignment.CenterVertically))
        TermsAndConditionsTextButton() {
            navController.navigate(route = Screen.TERMS_AND_CONDITIONS)
        }
    }
}

// endregion

// region TERMS & CONDITIONS TEXT BUTTON

@Composable
private fun TermsAndConditionsTextButton(onClick: () -> Unit) {
    TextButton(onClick = onClick, contentPadding = PaddingValues(0.dp)) {
        Text(text = stringResource(id = R.string.terms_and_conditions))
    }
}

// endregion

// region SIGN UP BUTTON

@Composable
private fun SignUpButton(onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.padding(bottom = 8.dp)) {
        Text(text = stringResource(id = R.string.sign_up))
    }
}

// endregion

// region SIGN IN TEXT BUTTON

@Composable
private fun SignInTextButton(onClick: () -> Unit) {
    Row {
        Text(text = "Already have an account? ", modifier = Modifier.align(Alignment.CenterVertically))
        TextButton(onClick = onClick, contentPadding = PaddingValues(0.dp)) {
            Text(text = stringResource(id = R.string.sign_in))
        }
    }
}

// endregion

// endregion