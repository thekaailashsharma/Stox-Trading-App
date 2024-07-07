package app.stocks.di

import android.content.Context
import app.stocks.data.local.dao.CompanyOverviewDao
import app.stocks.data.local.dao.IntraDayDao
import app.stocks.data.local.dao.TopPerformersDao
import app.stocks.data.local.database.AppDatabase
import app.stocks.data.remote.RemoteStocksRepository
import app.stocks.data.remote.RemoteStocksRepositoryImpl
import app.stocks.domain.AppRepository
import app.stocks.domain.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(json = kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
            }
            install(Logging)
            install(HttpTimeout) {
                requestTimeoutMillis = 1000000
            }
        }
    }

    @Provides
    @Singleton
    fun provideRemoteStocksRepository(client: HttpClient): RemoteStocksRepository {
        return RemoteStocksRepositoryImpl(client)
    }

    @Provides
    @Singleton
    fun provideTopPerformersDao(@ApplicationContext context: Context): TopPerformersDao {
        return AppDatabase.getInstance(context).topPerformersDao()
    }

    @Provides
    @Singleton
    fun provideCompanyOverviewDao(@ApplicationContext context: Context): CompanyOverviewDao {
        return AppDatabase.getInstance(context).companyOverviewDao()
    }

    @Provides
    @Singleton
    fun provideIntraDayDao(@ApplicationContext context: Context): IntraDayDao {
        return AppDatabase.getInstance(context).intraDayDao()
    }

    @Provides
    @Singleton
    fun provideStocksRepository(
        remoteStocksRepository: RemoteStocksRepository,
        topPerformersDao: TopPerformersDao,
        companyOverviewDao: CompanyOverviewDao,
        intraDayDao: IntraDayDao
    ): AppRepository {
        return AppRepositoryImpl(remoteStocksRepository, topPerformersDao, companyOverviewDao, intraDayDao)
    }
}