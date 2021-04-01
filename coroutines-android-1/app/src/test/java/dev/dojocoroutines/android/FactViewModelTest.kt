package dev.dojocoroutines.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.dojocoroutines.android.domain.Fact
import dev.dojocoroutines.android.domain.UseCase
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


class FactViewModelTest {

    @get:Rule
    val itRule = InstantTaskExecutorRule()

    @get:Rule
    val rxRule = RxTestRule()

    @Test
    fun `when initialize should load fact state with description`() {
        val mockedUseCase = mock<UseCase<Fact>> {
            on { execute() } doReturn Single.just(Fact("OK"))
        }
        val expectedState = FactState(description = "OK", error = null)

        val viewModel = FactViewModel(mockedUseCase)

        assertEquals(expectedState, viewModel.state.value)
    }

    @Test
    fun `when initialize should load fact state with error`() {
        val mockedUseCase = mock<UseCase<Fact>> {
            on { execute() } doReturn Single.error(Throwable("Deu ruim mano :("))
        }
        val expectedState = FactState(description = "", error = "Deu ruim mano :(")

        val viewModel = FactViewModel(mockedUseCase)

        assertEquals(expectedState, viewModel.state.value)
    }
}