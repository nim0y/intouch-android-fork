package care.intouch.app.feature.authorization.data.di

import care.intouch.app.feature.authorization.data.api.AuthenticationRemoteDataSource
import care.intouch.app.feature.authorization.data.impl.AuthenticationRemoteDataSourceImpl
import care.intouch.app.feature.authorization.data.impl.AuthenticationRepositoryImpl
import care.intouch.app.feature.authorization.domain.api.AuthenticationRepository
import care.intouch.app.feature.authorization.domain.models.AuthenticationExceptionToErrorMapper
import care.intouch.app.feature.common.domain.models.BaseExceptionToErrorMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthenticationExceptionMapper

@Module
@InstallIn(SingletonComponent::class)
interface AuthenticationDataModule {
    @Singleton
    @Binds
    fun bindAuthenticationRemoteDataSource(
        impl: AuthenticationRemoteDataSourceImpl
    ): AuthenticationRemoteDataSource

    @Singleton
    @Binds
    fun bindAuthenticationRepository(
        impl: AuthenticationRepositoryImpl
    ): AuthenticationRepository

    @AuthenticationExceptionMapper
    @Binds
    fun bindAuthenticationExceptionToErrorMapper(
        impl: AuthenticationExceptionToErrorMapper
    ): BaseExceptionToErrorMapper
}