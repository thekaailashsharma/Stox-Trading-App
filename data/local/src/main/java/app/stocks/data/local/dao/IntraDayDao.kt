package app.stocks.data.local.dao

import androidx.room.*
import app.stocks.data.dto.localDto.IntraDayEntity
import app.stocks.data.dto.localDto.IntraDayInfoDto
import app.stocks.data.dto.localDto.MetaDataEntity
import app.stocks.data.dto.localDto.TimeSeries60minEntity
import app.stocks.data.dto.localDto.X20240626130000Entity
import kotlinx.coroutines.flow.Flow

@Dao
interface IntraDayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntraday(intraDayInfoDto: IntraDayInfoDto)

    @Query("SELECT * FROM intraday_info")
    fun getIntraDays(): Flow<List<IntraDayInfoDto>>?

    @Query("DELETE FROM intraday_info")
    suspend fun clearAllIntraDay()
}
