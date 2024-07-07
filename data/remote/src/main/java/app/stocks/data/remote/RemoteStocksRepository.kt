package app.stocks.data.remote

import app.stocks.data.remote.dto.intraday.IntradayResponse
import app.stocks.data.remote.dto.overview.CompanyOverviewResponse
import app.stocks.data.remote.dto.search.TickerSearchResponse
import app.stocks.data.remote.dto.topGainers.TopPerformersResponse
import app.stocks.utils.Resource

interface RemoteStocksRepository {
    suspend fun getTopPerformers(): Resource<TopPerformersResponse>
    suspend fun getMostActivelyTraded(
        symbol: String,
        interval: String = "60min"
    ): Resource<IntradayResponse>
    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyOverviewResponse>

    suspend fun tickerSearch(
        query: String
    ): Resource<TickerSearchResponse>
}