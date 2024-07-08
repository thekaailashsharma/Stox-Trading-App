package app.stocks.domain


import app.stocks.data.dto.localDto.*
import app.stocks.data.dto.remoteDto.intraday.IntraDayInfo
import app.stocks.data.dto.remoteDto.intraday.IntradayResponse
import app.stocks.data.dto.remoteDto.search.TickerSearchResponse
import app.stocks.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getTopPerformers(): Flow<Resource<TopPerformersWithRelations>>
    fun getCompanyOverview(symbol: String): Flow<Resource<List<CompanyOverviewEntity>>>
    suspend fun getIntraDayInfo(
        symbol: String
    ): Resource<List<IntraDayInfo>>
    suspend fun tickerSearch(
        query: String
    ): Resource<TickerSearchResponse>
}
