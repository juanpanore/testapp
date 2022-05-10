package com.example.testapp.model



import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "ttest_mensajes")
data class Mensaje (
    @PrimaryKey var idMessage: Int,
    @ColumnInfo(name = "title")   var title: String,
    @ColumnInfo(name = "body") var body: String,
    @ColumnInfo(name = "emailFrom") var emailFrom: String,
    @ColumnInfo(name = "time") var time: Date?,
    @ColumnInfo(name = "imageContactUrl") var imageContactUrl: String
)