package app.stocks.data.remote.dto.intraday


import com.google.gson.annotations.SerializedName

data class X20240705100500(
    @SerializedName("4. close")
    val close: String? = null,
    @SerializedName("2. high")
    val high: String? = null,
    @SerializedName("3. low")
    val low: String? = null,
    @SerializedName("1. open")
    val `open`: String? = null,
    @SerializedName("5. volume")
    val volume: String? = null
)