package app.stocks.data.dto.localDto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded
import androidx.room.Relation

@Entity(tableName = "top_performers")
data class TopPerformersEntity(
    @PrimaryKey val id: Int,
    val lastUpdated: String?,
    val metadata: String?
)

@Entity(tableName = "most_actively_traded")
data class MostActivelyTradedEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val topPerformersId: Int,
    val changeAmount: String?,
    val changePercentage: String?,
    val price: String?,
    val ticker: String?,
    val volume: String?
)

@Entity(tableName = "top_gainers")
data class TopGainersEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val topPerformersId: Int,
    val changeAmount: String?,
    val changePercentage: String?,
    val price: String?,
    val ticker: String?,
    val volume: String?
)

@Entity(tableName = "top_losers")
data class TopLosersEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val topPerformersId: Int,
    val changeAmount: String?,
    val changePercentage: String?,
    val price: String?,
    val ticker: String?,
    val volume: String?
)


data class TopPerformersWithRelations(
    @Embedded val topPerformers: TopPerformersEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "topPerformersId"
    )
    val mostActivelyTraded: List<MostActivelyTradedEntity>?,
    @Relation(
        parentColumn = "id",
        entityColumn = "topPerformersId"
    )
    val topGainers: List<TopGainersEntity>?,
    @Relation(
        parentColumn = "id",
        entityColumn = "topPerformersId"
    )
    val topLosers: List<TopLosersEntity>?
)

