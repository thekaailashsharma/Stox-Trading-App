package app.stocks.data.dto.remoteDto.intraday


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class X20240626130000(
    @SerialName("4. close")
    val close: String? = null,
    @SerialName("2. high")
    val high: String? = null,
    @SerialName("3. low")
    val low: String? = null,
    @SerialName("1. open")
    val `open`: String? = null,
    @SerialName("5. volume")
    val volume: String? = null
)