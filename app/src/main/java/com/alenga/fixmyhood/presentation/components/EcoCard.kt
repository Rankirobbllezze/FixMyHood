package com.alenga.fixmyhood.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.tooling.preview.Preview

data class EcoChallenge(
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val isCompleted: Boolean
)

@Composable
fun EcoCard(
    challenge: EcoChallenge,
    onCompleteChange: (Boolean) -> Unit,
    onEditClick: (EcoChallenge) -> Unit,
    onDeleteClick: (EcoChallenge) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = challenge.isCompleted,
                onCheckedChange = onCompleteChange,
                modifier = Modifier.padding(end = 16.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = challenge.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = challenge.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Category: ${challenge.category}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Button(onClick = { onEditClick(challenge) }) {
                        Text("Edit")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { onDeleteClick(challenge) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Delete", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun EcoCardPreview() {
    EcoCard(
        challenge = EcoChallenge(
            id = "1",
            title = "Plant a Tree",
            description = "Join a tree planting event to support reforestation.",
            category = "Conservation",
            isCompleted = false
        ),
        onCompleteChange = {},
        onEditClick = {},
        onDeleteClick = {}
    )
}
