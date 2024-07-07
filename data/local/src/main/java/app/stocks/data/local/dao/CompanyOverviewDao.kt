package app.stocks.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.stocks.data.dto.localDto.CompanyOverviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyOverviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyOverview(companyOverviewEntity: CompanyOverviewEntity)

    @Query("SELECT * FROM CompanyOverview")
    fun getCompanyOverview(): Flow<List<CompanyOverviewEntity>>

    @Query("DELETE FROM CompanyOverview")
    suspend fun clearAll()
}
