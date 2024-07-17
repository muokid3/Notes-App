package com.dm.berxley.notesapp.views

import androidx.compose.runtime.Composable
import com.dm.berxley.notesapp.presentation.NotesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(
    viewModel: NotesViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.HomeScreen.route){
            HomeView(viewModel = viewModel, navController = navController)
        }

    }

}