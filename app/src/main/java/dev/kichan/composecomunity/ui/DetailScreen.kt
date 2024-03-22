package dev.kichan.composecomunity.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.kichan.composecomunity.model.data.Board
import dev.kichan.composecomunity.ui.theme.ComposeComunityTheme

@Composable
fun DetailScreen(navController: NavController, board: Board, onDelete: (board: Board) -> Unit) {
    val isShowDialog = rememberSaveable { mutableStateOf(false) }

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

        if (isShowDialog.value) CheckDialog(
            {
                onDelete(board)
                isShowDialog.value = false
            },
            {
                isShowDialog.value = false
            }
        )

        Text(
            text = board.content,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1F),
        )

        Row {
            Button(
                onClick = { }, modifier = Modifier.weight(1F)
            ) {
                Text(text = "수정")
            }
            Button(
                onClick = {
                    isShowDialog.value = true
//                    onDelete(board)
                },
                modifier = Modifier.weight(1F),
            ) {
                Text(text = "삭제")
            }
        }
    }
}

@Composable
fun CheckDialog(
    onYesClick: () -> Unit,
    onNoClick: () -> Unit,
) {
    Dialog(
        onDismissRequest = {  },
        DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        )
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column {
                Text(
                    text = "Hello World",
                    modifier = Modifier
                        .padding(32.dp)
                        .align(Alignment.CenterHorizontally),
                )
                Row {
                    Button(
                        onClick = onYesClick,
                        modifier = Modifier.weight(1F)
                    ) {
                        Text(text = "예")
                    }
                    Button(
                        onClick = onNoClick,
                        modifier = Modifier.weight(1F),
                    ) {
                        Text(text = "아니요")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckDialogPreview() {
    ComposeComunityTheme {
        CheckDialog({}, {})
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    ComposeComunityTheme {
        DetailScreen(rememberNavController(), Board.default, {})
    }
}