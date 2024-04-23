package care.intouch.app.feature.authorization.data.di

import care.intouch.app.feature.authorization.data.api.UserApiService
import care.intouch.app.feature.common.di.RetrofitWithToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserNetworkModule {
    @Provides
    @Singleton
    fun provideUserApiService(
        @RetrofitWithToken retrofit: Retrofit
    ): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }
}