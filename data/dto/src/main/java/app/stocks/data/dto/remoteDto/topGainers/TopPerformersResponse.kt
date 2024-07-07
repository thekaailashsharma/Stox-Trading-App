package app.stocks.data.dto.remoteDto.topGainers


import app.stocks.data.dto.remoteDto.topGainers.MostActivelyTraded
import app.stocks.data.dto.remoteDto.topGainers.TopGainer
import app.stocks.data.dto.remoteDto.topGainers.TopLoser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopPerformersResponse(
    @SerialName("last_updated")
    val lastUpdated: String? = null,
    @SerialName("metadata")
    val metadata: String? = null,
    @SerialName("most_actively_traded")
    val mostActivelyTraded: List<MostActivelyTraded?>? = null,
    @SerialName("top_gainers")
    val topGainers: List<TopGainer?>? = null,
    @SerialName("top_losers")
    val topLosers: List<TopLoser?>? = null
)