package dev.kichan.composecomunity.model.data

import dev.kichan.composecomunity.randomHash
import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

data class Board(
    val id : String = randomHash(),
    val title: String = "",
    val content : String = "",
    val writer : String = "",
    val password : String = "",
    val created : Date = Date()
) : Serializable {
    companion object {
        val default = Board(
        "asjasjkajsjkf",
        "대충 제목",
        "대충 내용 대충 내용 대충 내용 대충 내용 대충 내용 대충 내용 대충 내용 ",
        "박키찬희찬",
        "0213",
    )
    }
//    val createdFormat : String
//        get() = created. .format(DateTimeFormatter.ofPattern("yy.MM.DD"))
}