package dev.kichan.composecomunity.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.kichan.composecomunity.model.data.Board
import dev.kichan.composecomunity.ui.theme.ComposeComunityTheme

@Composable
fun DetailScreen(navController: NavController, board : Board) {
    Column {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = board.title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = board.writer,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
            )
            Text(
                text = board.created.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
            )
        }

        Text(text = board.content, style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    ComposeComunityTheme {
        DetailScreen(rememberNavController(), Board.default)
    }
}