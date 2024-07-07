package app.stocks.domain

import app.stocks.data.dto.localDto.*
import app.stocks.data.dto.mappers.*
import app.stocks.data.local.dao.CompanyOverviewDao
import app.stocks.data.local.dao.IntraDayDao
import app.stocks.data.local.dao.TopPerformersDao
import app.stocks.data.remote.RemoteStocksRepository
import app.stocks.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class AppRepositoryImpl(
    private val remoteStocksRepository: RemoteStocksRepository,
    private val topPerformersDao: TopPerformersDao,
    private val companyOverviewDao: CompanyOverviewDao,
    private val intraDayDao: IntraDayDao
) : AppRepository {
    override fun getTopPerformers(): Flow<Resource<TopPerformersWithRelations>> {
        return flow {
            emit(Resource.Loading())
            val topPerformersResponse = remoteStocksRepository.getTopPerformers()
            if (topPerformersResponse is Resource.Success) {
                val topPerformersEntity = topPerformersResponse.data?.toEntity()
                val mostActivelyTradedEntities = topPerformersResponse.data?.mostActivelyTraded?.toMostActivelyTradedEntityList(topPerformersEntity?.id ?: 0)
                val topGainersEntities = topPerformersResponse.data?.topGainers?.toTopGainersEntityList(topPerformersEntity?.id ?: 0)
                val topLosersEntities = topPerformersResponse.data?.topLosers?.toTopLosersEntityList(topPerformersEntity?.id ?: 0)

                topPerformersEntity?.let { topPerformersDao.insertTopPerformers(it) }
                mostActivelyTradedEntities?.let { topPerformersDao.insertMostActivelyTraded(it) }
                topGainersEntities?.let { topPerformersDao.insertTopGainers(it) }
                topLosersEntities?.let { topPerformersDao.insertTopLosers(it) }

                val topPerformersWithRelations = topPerformersDao.getTopPerformersWithRelations(topPerformersEntity?.id ?: 0)
                emit(Resource.Success(topPerformersWithRelations!!))
            } else {
                emit(Resource.Error("An error occurred"))
            }
        }.catch { e ->
            emit(Resource.Error("An error occurred ${e.message}"))
        }
    }

    override fun getCompanyOverview(symbol: String): Flow<Resource<List<CompanyOverviewEntity>>> {
        return channelFlow {
            send(Resource.Loading())

            try {
                // Fetch data from remote repository
                val companyOverviewResponse = remoteStocksRepository.getCompanyInfo(symbol)

                if (companyOverviewResponse is Resource.Success) {
                    // Map response data to local entities
                    val companyOverviewEntities = companyOverviewResponse.data?.toEntity()

                    // Insert entities into Room database
                    companyOverviewEntities?.let { companyOverviewDao.insertCompanyOverview(it) }

                    // Retrieve entities from Room database
                    companyOverviewDao.getCompanyOverview().collectLatest { storedCompanyOverviews ->
                        // Emit success with stored entities
                        send(Resource.Success(storedCompanyOverviews))
                    }
                } else {
                    // Emit error if fetching or mapping fails
                    send(Resource.Error("Failed to fetch company overview data"))
                }
            } catch (e: Exception) {
                // Catch any exceptions and emit error
                send(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }




    override fun getIntraDay(symbol: String): Flow<Resource<IntraDayEntity>> {
        return channelFlow {
            send(Resource.Loading())

            try {
                // Fetch data from remote repository
                val intraDayResponse = remoteStocksRepository.getMostActivelyTraded(symbol)

                if (intraDayResponse is Resource.Success) {
                    // Map response data to local entities
                    val intraDayEntity = intraDayResponse.data?.toEntity()
                    val metaDataEntity = intraDayResponse.data?.metaData?.toEntity()
                    val timeSeries60minEntities = intraDayResponse.data?.timeSeries60min?.toEntity()

                    // Insert entities into Room database
                    intraDayEntity?.let { intraDayDao.insertIntraDayWithRelations(it) }
                    metaDataEntity?.let { intraDayDao.insertMetaData(it) }
                    timeSeries60minEntities?.let { intraDayDao.insertTimeSeries60min(it) }

                    // Retrieve entities from Room database
                    val intraDayWithRelations = intraDayDao.getIntraDayWithRelations(intraDayEntity?.id?.toInt() ?: 0)
                    intraDayWithRelations?.let {
                        send(Resource.Success(it))
                    } ?: run {
                        send(Resource.Error("No data found"))
                    }
                } else {
                    // Emit error if fetching or mapping fails
                    send(Resource.Error("Failed to fetch intraday data"))
                }
            } catch (e: Exception) {
                // Catch any exceptions and emit error
                send(Resource.Error("An error occurred: ${e.message}"))
            }
        }
    }



}