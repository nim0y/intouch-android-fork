package care.intouch.app.feature.authorization.data.di

import care.intouch.app.feature.authorization.data.api.AuthenticationApiService
import care.intouch.app.feature.common.di.RetrofitWithToken
import care.intouch.app.feature.common.di.RetrofitWithoutToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthApiServiceWithoutToken

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthenticationApiServiceWithToken

@Module
@InstallIn(SingletonComponent::class)
class AuthenticationNetworkModule {
    @Provides
    @AuthApiServiceWithoutToken
    @Singleton
    fun provideAuthenticationApiService(
        @RetrofitWithoutToken retrofit: Retrofit
    ): AuthenticationApiService {
        return retrofit.create(AuthenticationApiService::class.java)
    }

    @Provides
    @AuthenticationApiServiceWithToken
    @Singleton
    fun provideAuthenticationApiServiceWithToken(
        @RetrofitWithToken retrofit: Retrofit
    ): AuthenticationApiService {
        return retrofit.create(AuthenticationApiService::class.java)
    }

}