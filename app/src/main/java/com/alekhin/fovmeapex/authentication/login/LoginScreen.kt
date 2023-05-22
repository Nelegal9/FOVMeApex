package com.alekhin.fovmeapex.authentication.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {
    LoginContent(navController = navController, authViewModel = authViewModel)
}

// endregion

// region COMPONENTS

// region CONTENT

@Composable
private fun LoginContent(navController: NavController, authViewModel: AuthViewModel) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        LoginHeader()
        LoginBody(navController = navController, authViewModel = authViewModel)
    }
}

// endregion

// region HEAD

@Composable
private fun LoginHeader() {
    Image(painter = painterResource(id = R.drawable.logo), contentDescription = null, modifier = Modifier.padding(bottom = 32.dp))
    Text(text = stringResource(id = R.string.app_name), modifier = Modifier.padding(bottom = 32.dp), fontSize = Typography.headlineLarge.fontSize)
    Text(text = stringResource(id = R.string.sign_in), fontSize = Typography.headlineMedium.fontSize, modifier = Modifier.padding(bottom = 8.dp))
}

// endregion

// region BODY

@Composable
private fun LoginBody(navController: NavController, authViewModel: AuthViewModel) {
    val email = remember { mutableStateOf(value = "") }
    val password = remember { mutableStateOf(value = "") }
    val context = LocalContext.current

    val loginFlow = authViewModel.loginFlow.collectAsState()

    EmailCard(email = email)
    PasswordCard(password = password)
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 56.dp), horizontalArrangement = Arrangement.SpaceAround) {
        RememberMeCheckBox()
        ForgotPasswordTextButton()
    }

    SignInButton {
        authViewModel.login(email.value, password.value)
    }

    SignUpTextButton {
        navController.navigate(route = Screen.REGISTRATION)
    }

    loginFlow.value.let {
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

// region EMAIL TEXT FIELD

@Composable
private fun EmailCard(email: MutableState<String>) {
    AuthCard(id = R.string.email, input = email, leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) }, visualTransformation = VisualTransformation.None, keyboardType = KeyboardType.Email)
}

// endregion

// region PASSWORD TEXT FIELD

@Composable
private fun PasswordCard(password: MutableState<String>) {
    AuthCard(id = R.string.password, input = password, leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) }, visualTransformation = PasswordVisualTransformation(), keyboardType = KeyboardType.Password)
}

// endregion

// region REMEMBER ME CHECKBOX

@Composable
private fun RememberMeCheckBox() {
    var checked by remember { mutableStateOf(false) }
    Row {
        Checkbox(checked = checked, onCheckedChange = { checked = !checked })
        Text(text = stringResource(id = R.string.remember_me), modifier = Modifier.align(Alignment.CenterVertically))
    }
}

// endregion

// region FORGOT PASSWORD TEXT BUTTON

@Composable
private fun ForgotPasswordTextButton() {
    TextButton(onClick = { /*TODO*/ }, contentPadding = PaddingValues(0.dp)) {
        Text(text = stringResource(id = R.string.forgot_password))
    }
}

// endregion

// region SIGN IN BUTTON

@Composable
private fun SignInButton(onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier.padding(bottom = 8.dp)) {
        Text(text = stringResource(id = R.string.sign_in))
    }
}

// endregion

// region SIGN UP TEXT BUTTON

@Composable
private fun SignUpTextButton(onClick: () -> Unit) {
    Row {
        Text(text = "Don't have an account? ", modifier = Modifier.align(Alignment.CenterVertically))
        TextButton(onClick = onClick, contentPadding = PaddingValues(0.dp)) {
            Text(text = stringResource(id = R.string.sign_up))
        }
    }
}

// endregion

// endregion