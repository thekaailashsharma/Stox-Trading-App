package app.stocks.di

import app.stocks.data.remote.RemoteStocksRepository
import app.stocks.data.remote.RemoteStocksRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideApiService(client: HttpClient): RemoteStocksRepository {
        return RemoteStocksRepositoryImpl(client = client)
    }
}