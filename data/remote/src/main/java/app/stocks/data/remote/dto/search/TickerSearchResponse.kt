package app.stocks.data.remote.dto.search


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TickerSearchResponse(
    @SerialName("bestMatches")
    val bestMatches: List<BestMatche?>? = null
)