package app.stocks.data.dto.remoteDto.topGainers


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopLoser(
    @SerialName("change_amount")
    val changeAmount: String? = null,
    @SerialName("change_percentage")
    val changePercentage: String? = null,
    @SerialName("price")
    val price: String? = null,
    @SerialName("ticker")
    val ticker: String? = null,
    @SerialName("volume")
    val volume: String? = null
)