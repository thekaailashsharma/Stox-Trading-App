package app.stocks.data.dto.remoteDto.overview


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompanyOverviewResponse(
    @SerialName("Address")
    val address: String? = null,
    @SerialName("AnalystRatingBuy")
    val analystRatingBuy: String? = null,
    @SerialName("AnalystRatingHold")
    val analystRatingHold: String? = null,
    @SerialName("AnalystRatingSell")
    val analystRatingSell: String? = null,
    @SerialName("AnalystRatingStrongBuy")
    val analystRatingStrongBuy: String? = null,
    @SerialName("AnalystRatingStrongSell")
    val analystRatingStrongSell: String? = null,
    @SerialName("AnalystTargetPrice")
    val analystTargetPrice: String? = null,
    @SerialName("AssetType")
    val assetType: String? = null,
    @SerialName("Beta")
    val beta: String? = null,
    @SerialName("BookValue")
    val bookValue: String? = null,
    @SerialName("CIK")
    val cIK: String? = null,
    @SerialName("Country")
    val country: String? = null,
    @SerialName("Currency")
    val currency: String? = null,
    @SerialName("50DayMovingAverage")
    val day50MovingAverage: String? = null,
    @SerialName("200DayMovingAverage")
    val day200MovingAverage: String? = null,
    @SerialName("Description")
    val description: String? = null,
    @SerialName("DilutedEPSTTM")
    val dilutedEPSTTM: String? = null,
    @SerialName("DividendDate")
    val dividendDate: String? = null,
    @SerialName("DividendPerShare")
    val dividendPerShare: String? = null,
    @SerialName("DividendYield")
    val dividendYield: String? = null,
    @SerialName("EBITDA")
    val eBITDA: String? = null,
    @SerialName("EPS")
    val ePS: String? = null,
    @SerialName("EVToEBITDA")
    val eVToEBITDA: String? = null,
    @SerialName("EVToRevenue")
    val eVToRevenue: String? = null,
    @SerialName("ExDividendDate")
    val exDividendDate: String? = null,
    @SerialName("Exchange")
    val exchange: String? = null,
    @SerialName("FiscalYearEnd")
    val fiscalYearEnd: String? = null,
    @SerialName("ForwardPE")
    val forwardPE: String? = null,
    @SerialName("GrossProfitTTM")
    val grossProfitTTM: String? = null,
    @SerialName("Industry")
    val industry: String? = null,
    @SerialName("LatestQuarter")
    val latestQuarter: String? = null,
    @SerialName("MarketCapitalization")
    val marketCapitalization: String? = null,
    @SerialName("Name")
    val name: String? = null,
    @SerialName("OperatingMarginTTM")
    val operatingMarginTTM: String? = null,
    @SerialName("PEGRatio")
    val pEGRatio: String? = null,
    @SerialName("PERatio")
    val pERatio: String? = null,
    @SerialName("PriceToBookRatio")
    val priceToBookRatio: String? = null,
    @SerialName("PriceToSalesRatioTTM")
    val priceToSalesRatioTTM: String? = null,
    @SerialName("ProfitMargin")
    val profitMargin: String? = null,
    @SerialName("QuarterlyEarningsGrowthYOY")
    val quarterlyEarningsGrowthYOY: String? = null,
    @SerialName("QuarterlyRevenueGrowthYOY")
    val quarterlyRevenueGrowthYOY: String? = null,
    @SerialName("ReturnOnAssetsTTM")
    val returnOnAssetsTTM: String? = null,
    @SerialName("ReturnOnEquityTTM")
    val returnOnEquityTTM: String? = null,
    @SerialName("RevenuePerShareTTM")
    val revenuePerShareTTM: String? = null,
    @SerialName("RevenueTTM")
    val revenueTTM: String? = null,
    @SerialName("Sector")
    val sector: String? = null,
    @SerialName("SharesOutstanding")
    val sharesOutstanding: String? = null,
    @SerialName("Symbol")
    val symbol: String? = null,
    @SerialName("TrailingPE")
    val trailingPE: String? = null,
    @SerialName("52WeekHigh")
    val weekHigh: String? = null,
    @SerialName("52WeekLow")
    val weekLow: String? = null
)