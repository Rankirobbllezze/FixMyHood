package com.alenga.fixmyhood.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("👤 Profile", style = MaterialTheme.typography.headlineSmall)

        // Placeholder user info
        Text("Name: FixMyHood Community")
        Text("Email: eco@fixmyhood.co.ke")
        Text("Phone Number: +23412348730")
        Text("Instagram: Fix_My_Hood")
        Text("Facebook: Fix_myHood")


        Divider()

        // Preferences Section
        Text("⚙️ Preferences", style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Dark Mode")
            Spacer(modifier = Modifier.weight(1f))
            Switch(checked = false, onCheckedChange = { /* handle toggle */ })
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = onLogout,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Logout", color = Color.White)
        }
    }
}
