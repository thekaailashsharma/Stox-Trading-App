package app.stocks.data.dto.localDto

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

data class IntraDayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @Embedded(prefix = "meta_")
    val metaData: MetaDataEntity? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "response_id"
    )
    val timeSeries60min: TimeSeries60minEntity? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "timeseries_id"
    )
    val x20240626130000Entity: X20240626130000Entity? = null

)

@Entity(tableName = "metadata")
data class MetaDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val information: String? = null,
    val interval: String? = null,
    @ColumnInfo(name = "last_refreshed")
    val lastRefreshed: String? = null,
    @ColumnInfo(name = "output_size")
    val outputSize: String? = null,
    val symbol: String? = null,
    @ColumnInfo(name = "time_zone")
    val timeZone: String? = null,
    @ColumnInfo(name = "response_id")
    val responseId: Long = 0L // Foreign key reference
)


@Entity(tableName = "timeseries_60min")
data class TimeSeries60minEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "timestamp")
    val timestamp: String? = null,
    @Embedded(prefix = "data_")
    val data: X20240626130000Entity? = null,
    @ColumnInfo(name = "response_id")
    val responseId: Long = 0L // Foreign key reference
)

@Entity(tableName = "x20240626130000")
data class X20240626130000Entity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "close")
    val close: String? = null,
    @ColumnInfo(name = "high")
    val high: String? = null,
    @ColumnInfo(name = "low")
    val low: String? = null,
    @ColumnInfo(name = "open")
    val open: String? = null,
    @ColumnInfo(name = "volume")
    val volume: String? = null,
    @ColumnInfo(name = "timeseries_id")
    val timeSeriesId: Long = 0L // Foreign key reference
)


