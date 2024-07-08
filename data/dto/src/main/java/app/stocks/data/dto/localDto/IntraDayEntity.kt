package app.stocks.data.dto.localDto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "intraday_info")
data class IntraDayInfoDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timestamp: String,
    val close: Double
)