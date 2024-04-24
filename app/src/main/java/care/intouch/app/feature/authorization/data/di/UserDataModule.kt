package care.intouch.app.feature.authorization.data.di

import care.intouch.app.feature.authorization.data.api.UserRemoteDataSource
import care.intouch.app.feature.authorization.data.impl.UserRemoteDataSourceImpl
import care.intouch.app.feature.authorization.data.impl.UserRepositoryImpl
import care.intouch.app.feature.authorization.domain.api.UserRepository
import care.intouch.app.feature.authorization.domain.models.mappers.UserExceptionToErrorMapper
import care.intouch.app.feature.common.domain.models.BaseExceptionToErrorMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserExceptionMapper

@Module
@InstallIn(SingletonComponent::class)
interface UserDataModule {
    @Singleton
    @Binds
    fun bindUserRemoteDataSource(
        impl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource

    @Singleton
    @Binds
    fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    @UserExceptionMapper
    @Binds
    fun bindUserExceptionToErrorMapper(
        impl: UserExceptionToErrorMapper
    ): BaseExceptionToErrorMapper
}