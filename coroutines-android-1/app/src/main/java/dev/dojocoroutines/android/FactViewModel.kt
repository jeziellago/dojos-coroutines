package dev.dojocoroutines.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.dojocoroutines.android.domain.Fact
import dev.dojocoroutines.android.domain.UseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class FactViewModel(private val getFactUseCase: UseCase<Fact>) : ViewModel() {

    private val _state = MutableLiveData(FactState())

    val state: LiveData<FactState>
        get() = _state

    private val disposable = CompositeDisposable()

    init {
        loadFact()
    }

    private fun loadFact() {
        getFactUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleSuccess, ::handleError)
            .apply { disposable.add(this) }
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

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}