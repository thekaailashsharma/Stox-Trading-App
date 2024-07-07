package app.stocks.data.remote.dto.search


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BestMatche(
    @SerialName("8. currency")
    val currency: String? = null,
    @SerialName("6. marketClose")
    val marketClose: String? = null,
    @SerialName("5. marketOpen")
    val marketOpen: String? = null,
    @SerialName("9. matchScore")
    val matchScore: String? = null,
    @SerialName("2. name")
    val name: String? = null,
    @SerialName("4. region")
    val region: String? = null,
    @SerialName("1. symbol")
    val symbol: String? = null,
    @SerialName("7. timezone")
    val timezone: String? = null,
    @SerialName("3. type")
    val type: String? = null
)