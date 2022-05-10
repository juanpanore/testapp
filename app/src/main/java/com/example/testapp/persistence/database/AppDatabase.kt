package com.example.testapp.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testapp.model.Mensaje
import com.example.testapp.persistence.messages.MensajeDAO


@Database( entities = [Mensaje::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase()  {

    companion object {
        private const val DATABASE_NAME = "email.db"

        private lateinit var instance: AppDatabase

        fun getInstance(context: Context?): AppDatabase {
            if (::instance.isInitialized) {
                println("Encontré una instancia para conectarte $instance")
                return instance
            } else {
                instance = Room.databaseBuilder(
                    context!!.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .build()
                return instance
            }

        }


    }

    abstract fun mensajeDAO(): MensajeDAO
}