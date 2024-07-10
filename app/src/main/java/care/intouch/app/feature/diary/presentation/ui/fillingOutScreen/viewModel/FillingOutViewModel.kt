package care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.viewModel

import androidx.lifecycle.ViewModel
import care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.models.FillingOutDataEvent
import care.intouch.app.feature.diary.presentation.ui.fillingOutScreen.models.FillingOutScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FillingOutViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(
        FillingOutScreenState(
            detailsText = "",
            analysisText = "",
            typeText = "",
            sensationsText = "",
        )
    )
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: FillingOutDataEvent) {
        when (event) {
            is FillingOutDataEvent.OnAnalysisTextChanged -> updateAnalysisState(event.text)
            is FillingOutDataEvent.OnDetailsTextChanged -> updateDetailsState(event.text)
            is FillingOutDataEvent.OnSensationsTextChanged -> updateSensationsState(event.text)
            is FillingOutDataEvent.OnTypeTextChanged -> updateTypeState(event.text)
        }
    }

    private fun updateDetailsState(text: String) {
        _uiState.update {
            it.copy(detailsText = text)
        }
    }

    private fun updateAnalysisState(text: String) {
        _uiState.update {
            it.copy(analysisText = text)
        }
    }

    private fun updateSensationsState(text: String) {
        _uiState.update {
            it.copy(sensationsText = text)
        }
    }

    private fun updateTypeState(text: String) {
        _uiState.update {
            it.copy(typeText = text)
        }
    }
}