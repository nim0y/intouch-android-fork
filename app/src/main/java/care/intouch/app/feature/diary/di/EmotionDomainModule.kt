package care.intouch.app.feature.diary.di

import care.intouch.app.feature.diary.domain.useCase.SaveEmotionDescUC
import care.intouch.app.feature.diary.domain.useCase.SaveEmotionUC
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface EmotionDomainModule {
    @Binds
    fun bindSaveEmotionDescUC(
        impl: SaveEmotionDescUC.Base,
    ): SaveEmotionDescUC

    @Binds
    fun bindSaveEmotionUC(
        impl: SaveEmotionUC.Base,
    ): SaveEmotionUC
}