package app.stocks.data.local.dao

import androidx.room.*
import app.stocks.data.dto.localDto.IntraDayEntity
import app.stocks.data.dto.localDto.MetaDataEntity
import app.stocks.data.dto.localDto.TimeSeries60minEntity
import app.stocks.data.dto.localDto.X20240626130000Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface IntraDayDao {

    // Insert methods for each entity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMetaData(metaDataEntity: MetaDataEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimeSeries60min(timeSeries60minEntities: TimeSeries60minEntity)

    // Method to insert IntraDayEntity with relations
    @Transaction
    suspend fun insertIntraDayWithRelations(intraDayEntity: IntraDayEntity) {
        intraDayEntity.metaData?.let { insertMetaData(it) }
        intraDayEntity.timeSeries60min?.let { insertTimeSeries60min(it) }
    }

    // Method to get IntraDayEntity with relations
    @Transaction
    @Query("SELECT * FROM metadata WHERE response_id = :id")
    suspend fun getIntraDayWithRelations(id: Int): IntraDayEntity?

    @Query("SELECT * FROM metadata WHERE id = :id")
    suspend fun getMetaDataWithRelations(id: Int): MetaDataEntity?

    @Query("SELECT * FROM timeseries_60min WHERE id = :id")
    suspend fun getTimeSeries60WithRelations(id: Int): TimeSeries60minEntity?

    @Query("SELECT * FROM x20240626130000 WHERE id = :id")
    suspend fun getX20240626130000WithRelations(id: Int): X20240626130000Entity?

    // Method to observe IntraDayEntity changes
    @Query("SELECT * FROM metadata WHERE response_id = :id")
    fun observeIntraDay(id: Int): Flow<IntraDayEntity?>

    // Method to clear all data
    @Query("DELETE FROM metadata")
    suspend fun clearAllIntraDay()


}
