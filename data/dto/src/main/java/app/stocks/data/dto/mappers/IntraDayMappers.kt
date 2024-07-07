package app.stocks.data.dto.mappers

import app.stocks.data.dto.localDto.IntraDayEntity
import app.stocks.data.dto.localDto.MetaDataEntity
import app.stocks.data.dto.localDto.TimeSeries60minEntity
import app.stocks.data.dto.localDto.X20240626130000Entity
import app.stocks.data.dto.remoteDto.intraday.IntradayResponse
import app.stocks.data.dto.remoteDto.intraday.MetaData
import app.stocks.data.dto.remoteDto.intraday.TimeSeries60min
import app.stocks.data.dto.remoteDto.intraday.X20240626130000

fun IntradayResponse.toEntity(): IntraDayEntity {
    return IntraDayEntity(
        metaData = this.metaData?.toEntity(),
        timeSeries60min = this.timeSeries60min?.toEntity()
    )
}

fun MetaData.toEntity(): MetaDataEntity {
    return MetaDataEntity(
        information = this.information,
        interval = this.interval,
        lastRefreshed = this.lastRefreshed,
        outputSize = this.outputSize,
        symbol = this.symbol,
        timeZone = this.timeZone
    )
}

fun TimeSeries60min.toEntity(): TimeSeries60minEntity {
    return TimeSeries60minEntity(
        timestamp = System.currentTimeMillis().toString(),
        data = this.x20240626130000?.toEntity()
    )
}

fun X20240626130000.toEntity(): X20240626130000Entity {
    return X20240626130000Entity(
        close = this.close,
        high = this.high,
        low = this.low,
        open = this.`open`,
        volume = this.volume
    )
}
