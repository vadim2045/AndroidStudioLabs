package com.example.Lab9

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [Route::class], version = 1)
abstract class RoutesDB: RoomDatabase() {
    abstract fun routeDao(): RouteDao
}

@Entity(tableName = "routes")
data class Route(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "length") val length: Int?
)

@Dao
interface RouteDao {
    @Query("SELECT * FROM routes")
    fun getAll(): List<Route>

    @Insert
    fun insertAll(vararg routes: Route)

    @Delete
    fun delete (route: Route)

    @Query("DELETE FROM routes WHERE id = :routeId")
    fun deleteById(routeId: Int)
}
