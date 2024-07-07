package app.stocks.data.remote.dto.intraday


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaData(
    @SerialName("1. Information")
    val information: String? = null,
    @SerialName("4. Interval")
    val interval: String? = null,
    @SerialName("3. Last Refreshed")
    val lastRefreshed: String? = null,
    @SerialName("5. Output Size")
    val outputSize: String? = null,
    @SerialName("2. Symbol")
    val symbol: String? = null,
    @SerialName("6. Time Zone")
    val timeZone: String? = null
)