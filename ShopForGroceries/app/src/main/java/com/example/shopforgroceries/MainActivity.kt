package com.example.shopforgroceries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.shopforgroceries.auth.GroceriesSwitch
import com.example.shopforgroceries.ui.theme.ShopForGroceriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopForGroceriesTheme {
               GroceriesSwitch()
            }
        }
    }
}

