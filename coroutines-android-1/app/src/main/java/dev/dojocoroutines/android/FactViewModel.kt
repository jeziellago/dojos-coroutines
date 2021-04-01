package dev.dojocoroutines.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.dojocoroutines.android.domain.Fact
import dev.dojocoroutines.android.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FactViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val getFactUseCase: UseCase<Fact>
) : ViewModel() {

    private val _state = MutableLiveData(FactState())

    val state: LiveData<FactState>
        get() = _state

    init {
        loadFact()
    }

    private fun loadFact() {
        viewModelScope.launchSafe(::handleError) {
            val fact: Fact = withContext(dispatcher) {
                getFactUseCase.execute()
            }
            handleSuccess(fact)
        }
    }

    private fun handleSuccess(fact: Fact) {
        updateState {
            copy(description = fact.description)
        }
    }

    private fun handleError(error: Throwable) {
        updateState {
            copy(
                description = "",
                error = error.message
            )
        }
    }

    private fun updateState(onChange: FactState.() -> FactState) {
        _state.value = onChange(checkNotNull(_state.value))
    }

}