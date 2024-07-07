package app.stocks.data.remote.dto.intraday


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IntradayResponse(
    @SerialName("Meta Data")
    val metaData: MetaData? = MetaData(),
    @SerialName("Time Series (60min)")
    val timeSeries60min: TimeSeries60min? = TimeSeries60min()
)