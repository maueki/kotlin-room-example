package demo.roomexample

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by maueki on 17/09/01.
 */

@Database(entities = arrayOf(Item::class), version = 1)
abstract class ItemsDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}
