package app.stocks.data.remote

import app.stocks.data.dto.remoteDto.intraday.IntraDayInfo
import app.stocks.data.dto.remoteDto.overview.CompanyOverviewResponse
import app.stocks.data.dto.remoteDto.search.TickerSearchResponse
import app.stocks.data.dto.remoteDto.topGainers.TopPerformersResponse
import app.stocks.utils.Resource

interface RemoteStocksRepository {
    suspend fun getTopPerformers(): Resource<TopPerformersResponse>
    suspend fun getIntraDayInfo(
        symbol: String,
        interval: String = "60min"
    ): Resource<List<IntraDayInfo>>
    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyOverviewResponse>

    suspend fun tickerSearch(
        query: String
    ): Resource<TickerSearchResponse>
}