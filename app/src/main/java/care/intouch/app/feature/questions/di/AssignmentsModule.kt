package care.intouch.app.feature.questions.di

import care.intouch.app.feature.questions.domain.useCase.GetAssignmentsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface AssignmentsModule {

    @Binds
    fun bindGetAssignmentsUseCase(impl: GetAssignmentsUseCase.Base): GetAssignmentsUseCase
}
