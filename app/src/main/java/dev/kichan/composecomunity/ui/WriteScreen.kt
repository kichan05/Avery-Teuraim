package dev.kichan.composecomunity.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.kichan.composecomunity.Screen
import dev.kichan.composecomunity.model.data.Board
import dev.kichan.composecomunity.ui.theme.ComposeComunityTheme

@Composable
fun WriteScreen(
    navController: NavController,
    onSubmit: (Board, () -> Unit, () -> Unit) -> Unit = { a, b, c -> }
) {
    val context = LocalContext.current
    var title by rememberSaveable { mutableStateOf("") }
    var content by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        TextField(
            value = title, onValueChange = { title = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "제목") },
            singleLine = true
        )
        TextField(
            value = content, onValueChange = { content = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "내용") },
            minLines = 10
        )
        TextField(
            value = password, onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "비밀번호") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Box(modifier = Modifier.weight(1F))
        Button(
            onClick = {
                if(title.isEmpty() || content.isEmpty()) {
                    return@Button
                }

                val data = Board(
                    title = title,
                    content = content,
                    writer = "박희찬",
                    password = password,
                )
                onSubmit(
                    data,
                    {
                        title = ""
                        content = ""
                        Toast.makeText(context, "작성완료", Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.Main.name)
                    },
                    {
                        Toast.makeText(context, "작성실패", Toast.LENGTH_SHORT).show()
                    },
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "입력")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WriteScreenPreview() {
    ComposeComunityTheme {
        WriteScreen(rememberNavController())
    }
}