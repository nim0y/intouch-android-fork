package care.intouch.app.feature.authorization.data.di

import care.intouch.app.feature.authorization.data.api.AuthenticationApiService
import care.intouch.app.feature.common.di.RetrofitWithAuth
import care.intouch.app.feature.common.di.RetrofitWithoutAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthApiServiceWithoutAuth

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthenticationApiServiceWithAuth

@Module
@InstallIn(SingletonComponent::class)
class AuthenticationNetworkModule {
    @Provides
    @AuthApiServiceWithoutAuth
    @Singleton
    fun provideAuthenticationApiService(
        @RetrofitWithoutAuth retrofit: Retrofit
    ): AuthenticationApiService {
        return retrofit.create(AuthenticationApiService::class.java)
    }

    @Provides
    @AuthenticationApiServiceWithAuth
    @Singleton
    fun provideAuthenticationApiServiceWithToken(
        @RetrofitWithAuth retrofit: Retrofit
    ): AuthenticationApiService {
        return retrofit.create(AuthenticationApiService::class.java)
    }

}