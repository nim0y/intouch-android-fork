package care.intouch.app.feature.common.di

import android.content.Context
import care.intouch.app.feature.common.data.ErrorInterceptor
import care.intouch.app.feature.common.data.TokenInterceptor
import care.intouch.app.feature.common.data.api.NetworkConnectionProvider
import care.intouch.app.feature.common.data.impl.NetworkConnectionProviderImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpClientWithoutToken

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkhttpClientWithToken

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitWithoutToken

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitWithToken


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkhttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Singleton
    @OkhttpClientWithoutToken
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        errorInterceptor: ErrorInterceptor,
        okkHttpClientBuilder: OkHttpClient.Builder
    ): OkHttpClient {
        return okkHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
            .addInterceptor(errorInterceptor).connectTimeout(TIMEOUT_180, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_180, TimeUnit.SECONDS).readTimeout(TIMEOUT_180, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @OkhttpClientWithToken
    @Provides
    fun provideOkHttpClientWithToken(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor,
        errorInterceptor: ErrorInterceptor,
        okkHttpClientBuilder: OkHttpClient.Builder
    ): OkHttpClient {
        return okkHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
            .addInterceptor(tokenInterceptor).addInterceptor(errorInterceptor)
            .connectTimeout(TIMEOUT_180, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_180, TimeUnit.SECONDS).readTimeout(TIMEOUT_180, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @RetrofitWithoutToken
    fun provideRetrofitWithoutToken(
        @OkhttpClientWithoutToken okHttpClient: OkHttpClient, json: Json
    ): Retrofit {
        return Retrofit.Builder().baseUrl(MOVIE_DB_ENDPOINT_URL).client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType())).build()
    }

    @Provides
    @RetrofitWithToken
    fun provideMainRetrofitWithToken(
        @OkhttpClientWithToken okHttpClient: OkHttpClient, json: Json
    ): Retrofit {
        return Retrofit.Builder().baseUrl(MOVIE_DB_ENDPOINT_URL).client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType())).build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectionProvider(@ApplicationContext context: Context): NetworkConnectionProvider {
        return NetworkConnectionProviderImpl(context)
    }

    companion object {
        const val MOVIE_DB_ENDPOINT_URL = "https://app.intouch.care/"
        const val TIMEOUT_180 = 180L
    }
}