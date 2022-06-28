package com.example.shopforgroceries.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shopforgroceries.auth.screens.Login
import com.example.shopforgroceries.auth.screens.Register
import com.example.shopforgroceries.auth.viewmodels.AuthViewModel
import com.example.shopforgroceries.navigation.destinations.Destination

@Composable
fun AuthenticationNavigationHost(navHostController: NavHostController,authViewModel: AuthViewModel){ //NavHostController

    NavHost(
        navController = navHostController,
        startDestination = Destination.LoginDestination.route){
        composable(route = Destination.LoginDestination.route){

            Login(navController = navHostController, authViewModel = authViewModel)
        }
        composable(route = Destination.SignUpDestination.route){

            Register(navController = navHostController)
        }
    }
}
