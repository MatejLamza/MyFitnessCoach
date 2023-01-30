package matej.lamza.mycoach.common.state

import matej.lamza.mycoach.common.ErrorMessage

interface UiState {
    val isLoading: Boolean
    val errorMessages: List<ErrorMessage>
}
