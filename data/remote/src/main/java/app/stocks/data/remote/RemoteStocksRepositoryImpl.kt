package app.stocks.data.remote

import android.util.Log
import app.stocks.data.dto.remoteDto.intraday.IntraDayInfo
import app.stocks.data.dto.remoteDto.intraday.IntradayResponse
import app.stocks.data.dto.remoteDto.overview.CompanyOverviewResponse
import app.stocks.data.dto.remoteDto.search.TickerSearchResponse
import app.stocks.data.dto.remoteDto.topGainers.TopPerformersResponse
import app.stocks.utils.Constants
import app.stocks.utils.Resource
import app.stocks.utils.parser.CSVParser
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.util.InternalAPI
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.jvm.javaio.toInputStream
import java.io.InputStream

class RemoteStocksRepositoryImpl(
    private val client: HttpClient,
    private val intradayInfoParser: CSVParser<IntraDayInfo>,
) : RemoteStocksRepository {
    override suspend fun getTopPerformers(): Resource<TopPerformersResponse> {
        try {
            val response = client.get {
                url(
                    "${Constants.baseUrl}?function=TOP_GAINERS_LOSERS" +
                            "&apikey=S2BHEWIDXTXEPTEH"
                )
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            println("Response: ${response.body<TopPerformersResponse>()}")
            println("Response: ${response}")
            return if (response.status == HttpStatusCode.OK) {
                Resource.Success(response.body<TopPerformersResponse>())
            } else {
                Resource.Error("An error occurred ${response.status.description}")
            }
        } catch (e: Exception) {
            Log.i("ApiException", e.message.toString())
            return Resource.Error("An error occurred ${e.message}")
        }
    }

    @OptIn(InternalAPI::class)
    override suspend fun getIntraDayInfo(
        symbol: String,
        interval: String
    ): Resource<List<IntraDayInfo>> {
        try {
            val response = client.get {
                url(
                    "${Constants.baseUrl}?function=TIME_SERIES_INTRADAY&symbol=$symbol" +
                            "&interval=$interval&apikey=6IK8VWJ61XCZ3XNO&datatype=csv"
                )
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            return if (response.status == HttpStatusCode.OK) {
                val inputStream = response.content.toMyInputStream()
                val results = intradayInfoParser.parse(inputStream)
                println("Results: $results")
                println("Results: $inputStream")
                println("Response: ${response.content}")
                return Resource.Success(results)
            } else {
                Resource.Error("An error occurred ${response.status.description}")
            }
        } catch (e: Exception) {
            Log.i("ApiException", e.message.toString())
            return Resource.Error("An error occurred ${e.message}")
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyOverviewResponse> {
        try {
            val response = client.get {
                url(
                    "${Constants.baseUrl}?function=OVERVIEW&symbol=$symbol" +
                            "&apikey=S2BHEWIDXTXEPTEH"
                )
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
             println("Response: ${response}")
             return if (response.status == HttpStatusCode.OK){
                 Resource.Success(response.body<CompanyOverviewResponse>())
             } else {
                 Resource.Error("An error occurred ${response.status.description}")
             }
        } catch (e: Exception) {
            Log.i("ApiException", e.message.toString())
            return Resource.Error("An error occurred ${e.message}")
        }
    }

    override suspend fun tickerSearch(query: String): Resource<TickerSearchResponse> {
        try {
            val response = client.get {
                url(
                    "${Constants.baseUrl}?function=SYMBOL_SEARCH&keywords=$query" +
                            "&apikey=S2BHEWIDXTXEPTEH"
                )
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            println("Response: ${response}")
            return if (response.status == HttpStatusCode.OK) {
                Resource.Success(response.body<TickerSearchResponse>())
            } else {
                Resource.Error("An error occurred ${response.status.description}")
            }
        } catch (e: Exception) {
            Log.i("ApiException", e.message.toString())
            return Resource.Error("An error occurred ${e.message}")
        }
    }

}

suspend fun ByteReadChannel.toMyInputStream(): InputStream {
    return this.readRemaining().readBytes().inputStream()
}
