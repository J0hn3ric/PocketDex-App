package com.example.pocketdex.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserCard::class],
    version = 1,
    exportSchema = false
)
abstract class UserCardDatabase: RoomDatabase() {

    abstract fun userCardDao(): UserCardDao

    companion object {
        @Volatile
        private var Instance: UserCardDatabase? = null


        fun getDataBase(context: Context): UserCardDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, UserCardDatabase::class.java, "user_card_database")
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also { Instance = it }
            }
        }
    }

}