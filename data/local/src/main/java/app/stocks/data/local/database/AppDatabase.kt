package app.stocks.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.stocks.data.dto.localDto.CompanyOverviewEntity
import app.stocks.data.dto.localDto.IntraDayEntity
import app.stocks.data.dto.localDto.MetaDataEntity
import app.stocks.data.dto.localDto.MostActivelyTradedEntity
import app.stocks.data.dto.localDto.TimeSeries60minEntity
import app.stocks.data.dto.localDto.TopGainersEntity
import app.stocks.data.dto.localDto.TopLosersEntity
import app.stocks.data.dto.localDto.TopPerformersEntity
import app.stocks.data.dto.localDto.X20240626130000Entity
import app.stocks.data.local.dao.CompanyOverviewDao
import app.stocks.data.local.dao.IntraDayDao
import app.stocks.data.local.dao.TopPerformersDao

@Database(
    entities = [
        TopPerformersEntity::class,
        MostActivelyTradedEntity::class,
        TopGainersEntity::class,
        TopLosersEntity::class,
        MetaDataEntity::class,
        TimeSeries60minEntity::class,
        X20240626130000Entity::class,
        CompanyOverviewEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun topPerformersDao(): TopPerformersDao
    abstract fun companyOverviewDao(): CompanyOverviewDao
    abstract fun intraDayDao(): IntraDayDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "AppDatabase"
            ).build()
        }
    }
}
