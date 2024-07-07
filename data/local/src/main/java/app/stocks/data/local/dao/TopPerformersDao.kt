package app.stocks.data.local.dao
import androidx.room.*
import app.stocks.data.dto.localDto.MostActivelyTradedEntity
import app.stocks.data.dto.localDto.TopGainersEntity
import app.stocks.data.dto.localDto.TopLosersEntity
import app.stocks.data.dto.localDto.TopPerformersEntity
import app.stocks.data.dto.localDto.TopPerformersWithRelations

@Dao
interface TopPerformersDao {

    // Insert methods for each entity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopPerformers(topPerformers: TopPerformersEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMostActivelyTraded(mostActivelyTraded: List<MostActivelyTradedEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopGainers(topGainers: List<TopGainersEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopLosers(topLosers: List<TopLosersEntity>)

    // Method to insert TopPerformersWithRelations
    @Transaction
    suspend fun insertTopPerformersWithRelations(topPerformersWithRelations: TopPerformersWithRelations) {
        val topPerformersEntity = topPerformersWithRelations.topPerformers
        val mostActivelyTradedEntities = topPerformersWithRelations.mostActivelyTraded
        val topGainersEntities = topPerformersWithRelations.topGainers
        val topLosersEntities = topPerformersWithRelations.topLosers

        insertTopPerformers(topPerformersEntity)
        mostActivelyTradedEntities?.let { insertMostActivelyTraded(it) }
        topGainersEntities?.let { insertTopGainers(it) }
        topLosersEntities?.let { insertTopLosers(it) }
    }

    // Method to get TopPerformersWithRelations
    @Transaction
    @Query("SELECT * FROM top_performers WHERE id = :id")
    suspend fun getTopPerformersWithRelations(id: Int): TopPerformersWithRelations?

    // Method to clear all data
    @Query("DELETE FROM top_performers")
    suspend fun clearAllTopPerformers()
}
