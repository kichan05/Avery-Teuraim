package dev.kichan.composecomunity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import dev.kichan.composecomunity.model.data.Board
import dev.kichan.composecomunity.ui.MainScreen
import dev.kichan.composecomunity.ui.WriteScreen
import dev.kichan.composecomunity.ui.DetailScreen
import dev.kichan.composecomunity.ui.EditScreen
import dev.kichan.composecomunity.ui.theme.ComposeComunityTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeComunityTheme {
                MyApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppBar() {
    Row(
        modifier = Modifier
            .background(Color(0xFF505050))
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "애브리 터임",
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val boardList = rememberSaveable {
        mutableStateOf(listOf<Board>())
    }

    val onSubmit: (Board, () -> Unit, () -> Unit) -> Unit = { board, onSuccess, onFailure ->
        db.collection("community").document(board.id).set(board)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure()
            }
    }

    val onDelete: (Board) -> Unit = {
        db.collection("community").document(it.id).delete()
            .addOnSuccessListener {
                Toast.makeText(context, "삭제 완료", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Main.name)
            }
            .addOnFailureListener {
                Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()
            }
    }

    val onUpdate : (Board) -> Unit = {
        db.collection("community").document(it.id).set(it)
            .addOnSuccessListener {
                Toast.makeText(context, "수정 완료", Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.Main.name)
            }
            .addOnFailureListener {
                Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()
            }
    }

    val loadBoard : () -> Unit = {
        db.collection("community").get()
            .addOnSuccessListener {
                boardList.value = it.toObjects<Board>()
            }
    }

    loadBoard()

    Surface {
        Scaffold(
            topBar = { AppBar() },
        ) {
            NavHost(
                modifier = Modifier.padding(it),
                navController = navController,
                startDestination = Screen.Main.name
            ) {
                composable(route = Screen.Main.name) {
                    MainScreen(navController, boardList.value, loadBoard)
                }
                composable(route = Screen.Write.name) {
                    WriteScreen(navController, onSubmit)
                }
                composable(route = Screen.Detail.name) {
                    val data = remember {
                        navController.previousBackStackEntry?.savedStateHandle?.get<Board>("board")
                    }!!
                    DetailScreen(navController, data, onDelete)
                }
                composable(route = Screen.Edit.name) {
                    val data = remember {
                        navController.previousBackStackEntry?.savedStateHandle?.get<Board>("board")
                    }!!
                    EditScreen(data, onUpdate)
                }
            }
        }
    }
}

