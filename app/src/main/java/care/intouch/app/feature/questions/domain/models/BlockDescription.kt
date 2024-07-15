package care.intouch.app.feature.questions.domain.models

data class BlockDescription(
    val type: TypeOfTitle,       // Сделать экземпляром энум класса
    val text: String
)
