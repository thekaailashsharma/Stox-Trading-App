package app.stocks.data.remote.dto.intraday


import com.google.gson.annotations.SerializedName

data class IntradayResponse(
    @SerializedName("Meta Data")
    val metaData: MetaData? = MetaData(),
    @SerializedName("Time Series (5min)")
    val timeSeries5min: TimeSeries5min? = TimeSeries5min()
)