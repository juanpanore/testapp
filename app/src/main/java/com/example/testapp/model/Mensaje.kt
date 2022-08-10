package com.example.testapp.model



import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "ttest_mensajes")
data class Mensaje (
    @ColumnInfo(name = "emailTo") var emailTo: String? = null,
    @ColumnInfo(name = "title")   var title: String? = null,
    @ColumnInfo(name = "body") var body: String? = null,
    @ColumnInfo(name = "emailFrom") var emailFrom: String? = null,
    @ColumnInfo(name = "time") var time: Date? = null,
    @ColumnInfo(name = "imageContactUrl") var imageContactUrl: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var mensajeId: Int = 0
}