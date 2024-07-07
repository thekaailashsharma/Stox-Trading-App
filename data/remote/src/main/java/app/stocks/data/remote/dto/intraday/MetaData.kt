package app.stocks.data.remote.dto.intraday


import com.google.gson.annotations.SerializedName

data class MetaData(
    @SerializedName("1. Information")
    val information: String? = null,
    @SerializedName("4. Interval")
    val interval: String? = null,
    @SerializedName("3. Last Refreshed")
    val lastRefreshed: String? = null,
    @SerializedName("5. Output Size")
    val outputSize: String? = null,
    @SerializedName("2. Symbol")
    val symbol: String? = null,
    @SerializedName("6. Time Zone")
    val timeZone: String? = null
)