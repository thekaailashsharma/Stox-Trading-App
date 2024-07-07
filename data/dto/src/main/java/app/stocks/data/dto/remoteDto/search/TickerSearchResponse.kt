package app.stocks.data.dto.remoteDto.search


import app.stocks.data.dto.remoteDto.search.BestMatche
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TickerSearchResponse(
    @SerialName("bestMatches")
    val bestMatches: List<BestMatche?>? = null
)