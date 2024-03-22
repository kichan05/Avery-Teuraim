package dev.kichan.composecomunity.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.kichan.composecomunity.Screen
import dev.kichan.composecomunity.model.data.Board
import dev.kichan.composecomunity.ui.theme.ComposeComunityTheme


@Composable
fun MainScreen(navController: NavController, boardList: List<Board>, loadBoard : () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = { loadBoard() }) {
            Text(text = "새로 고침")
        }
        LazyColumn(
            modifier = Modifier
                .weight(1F)
                .padding(12.dp)
        ) {
            items(boardList) {
                BoardItem(board = it, navController = navController)
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Screen.Write.name)
            }
        ) {
            Text(text = "글쓰기")
        }
    }
}

@Composable
fun BoardItem(board: Board, navController: NavController) {
    Column(
        Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                navController.apply {
                    currentBackStackEntry?.savedStateHandle?.set("board", board)
                    navigate(Screen.Detail.name)
                }
            }
    ) {
        Text(
            text = board.title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = if (board.content.length > 10) board.content.slice(0..10) else board.content,
            style = MaterialTheme.typography.bodyMedium,
        )
        Row {
            val createDateFormat = board.created

            Text(
                text = "${board.writer} • $createDateFormat",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoardItemPrev() {
    val board = Board(
        "대충 제목",
        "대충 내용 대충 내용 대충 내용 대충 내용 대충 내용 대충 내용 대충 내용 ",
        "박희찬사마",
        "0213",
    )

    ComposeComunityTheme {
        BoardItem(board = board, rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val boardList = MutableList(100) {
        Board(
            "대충 제목",
            "대충 내용 대충 내용 대충 내용 대충 내용 대충 내용 대충 내용 대충 내용 ",
            "박희찬사마",
            "0213",
        )
    }

    ComposeComunityTheme {
        MainScreen(rememberNavController(), boardList, {})
    }
}