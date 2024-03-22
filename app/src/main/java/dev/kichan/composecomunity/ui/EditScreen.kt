package dev.kichan.composecomunity.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.kichan.composecomunity.model.data.Board
import dev.kichan.composecomunity.ui.theme.ComposeComunityTheme
import org.w3c.dom.Text

@Composable
fun EditScreen(board: Board, onUpdate: (Board) -> Unit) {
    val title = rememberSaveable {
        mutableStateOf(board.title)
    }
    val content = rememberSaveable {
        mutableStateOf(board.content)
    }

    Column {
        TextField(
            value = title.value,
            onValueChange = { title.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "제목") }
        )
        TextField(
            value = content.value,
            onValueChange = { content.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "내용") }
        )

        Button(onClick = {
            onUpdate(board.copy(title = title.value, content = content.value))
        }) {
            Text(text = "수정")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditScreenPreview() {
    ComposeComunityTheme {
        EditScreen(Board.default, {})
    }
}