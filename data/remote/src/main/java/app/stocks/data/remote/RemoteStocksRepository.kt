package app.stocks.data.remote

import app.stocks.data.remote.dto.topGainers.TopPerformersResponse

interface RemoteStocksRepository {
    suspend fun getTopPerformers(): TopPerformersResponse
    suspend fun getTopLosers(): List<app.stocks.data.remote.dto.topGainers.TopLoser>
    suspend fun getMostActivelyTraded(): List<app.stocks.data.remote.dto.topGainers.MostActivelyTraded>
}