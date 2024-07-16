package care.intouch.app.feature.questions.presentations.ui.models

sealed class QuestionEvent {
    class OnCloseButton(): QuestionEvent()
}