package app.stocks.data.dto.remoteDto.intraday

import java.time.LocalDateTime

data class IntraDayInfo(
    val date: LocalDateTime,
    val close: Double
)