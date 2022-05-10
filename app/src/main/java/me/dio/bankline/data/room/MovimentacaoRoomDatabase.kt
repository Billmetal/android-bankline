package me.dio.bankline.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovimentacaoRoom::class], version = 1, exportSchema = false)
abstract class MovimentacaoRoomDatabase : RoomDatabase() {

    abstract fun movimentacaoDAO(): MovimentacaoDAO

    companion object {

        @Volatile
        private var INSTANCE: MovimentacaoRoomDatabase? = null

        fun getDatabase(context: Context) : MovimentacaoRoomDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovimentacaoRoomDatabase::class.java,
                    "movimentacaoroom_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}