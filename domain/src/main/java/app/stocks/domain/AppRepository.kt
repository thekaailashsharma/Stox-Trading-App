package app.stocks.domain


import app.stocks.data.dto.localDto.*
import app.stocks.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getTopPerformers(): Flow<Resource<TopPerformersWithRelations>>
    fun getCompanyOverview(symbol: String): Flow<Resource<List<CompanyOverviewEntity>>>
    fun getIntraDay(symbol: String): Flow<Resource<IntraDayEntity>>
}
