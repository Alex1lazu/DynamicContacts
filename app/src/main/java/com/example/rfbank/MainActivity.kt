package com.example.rfbank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.rfbank.presentation.ContactsScreen
import com.example.rfbank.ui.theme.RFBankTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RFBankTheme {
                ContactsScreen()
            }
        }
    }
}



