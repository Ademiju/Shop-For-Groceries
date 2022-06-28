package com.example.shopforgroceries.auth

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.shopforgroceries.auth.viewmodels.AuthViewModel
import com.example.shopforgroceries.navigation.navhost.AuthenticationNavigationHost

@Composable
fun AuthenticationWrapper(viewModel: AuthViewModel){
    val navHostController = rememberNavController()  //The rememberNavController function helps us create a navHostController and returns it to us
    AuthenticationNavigationHost(navHostController = navHostController, authViewModel = viewModel) //handing the navHostController to the AuthenticationNavigationHost
}