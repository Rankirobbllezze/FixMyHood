package com.alenga.fixmyhood.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DrawerContent(
    onNavigateToHome: () -> Unit,
    onLogout: () -> Unit,
//    onNavigateToApi: () -> Unit
){
    Surface(
        modifier = Modifier.width(280.dp).fillMaxHeight()
            .padding(top = 64.dp, bottom = 16.dp)
            .clip(RoundedCornerShape(topEnd = 16.dp,
                bottomEnd = 16.dp))
            .shadow(4.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(modifier  = Modifier.padding(top = 16.dp,
            start = 24.dp, end = 16.dp )
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
        ) {
            Text(
                text  = "Menu",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Home",
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.clickable { onNavigateToHome() }
                    .padding(vertical = 12.dp)
            )

            Text(
                text = "Logout",
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.clickable { onLogout() }
                    .padding(vertical = 12.dp)
            )

        }
    }
}

@Composable
fun DrawerItem(label: String, onClick: () -> Unit) {
    Text(
        text = label,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        style = MaterialTheme.typography.bodyLarge
    )
}
