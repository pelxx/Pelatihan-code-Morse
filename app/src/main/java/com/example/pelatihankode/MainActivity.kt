package com.example.pelatihankode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.*
import com.example.pelatihankode.ui.screen.BelajarScreen
import com.example.pelatihankode.ui.screen.LoginScreen
import com.example.pelatihankode.ui.screen.MenuScreen
import com.example.pelatihankode.ui.theme.PelatihanKODETheme
import com.example.pelatihankode.ui.screen.DetailHurufScreen
import com.example.pelatihankode.ui.screen.hurufList

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            PelatihanKODETheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {

                    composable("login") {
                        LoginScreen(navController)
                    }

                    composable("menu") {
                        MenuScreen(navController)
                    }

                    composable("belajar") {
                        BelajarScreen(navController)
                    }
                    composable("detail/{huruf}") { backStackEntry ->

                        val huruf = backStackEntry.arguments
                            ?.getString("huruf") ?: ""

                        val selectedHuruf = hurufList.find {

                            it.huruf == huruf

                        }

                        selectedHuruf?.let {

                            DetailHurufScreen(it)

                        }
                    }
                }

            }
        }
    }
}