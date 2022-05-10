package com.example.testapp.persistence.messages

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testapp.model.Mensaje

@Dao
interface MensajeDAO {

    @Query("SELECT * FROM ttest_mensajes")
    fun getAll(): List<Mensaje>

    @Insert
    fun insertAll(vararg messages: Mensaje)

    @Query("DELETE FROM ttest_mensajes")
    fun delete()

}