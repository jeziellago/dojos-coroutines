package dev.dojocoroutines.android

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class CoroutineTestRule : TestRule {

    val testDispatcher = TestCoroutineDispatcher()

    override fun apply(
        base: Statement,
        description: Description?
    ): Statement = createCoroutineStatement(base)

    private fun createCoroutineStatement(base: Statement) = object : Statement() {
        override fun evaluate() {
            Dispatchers.setMain(testDispatcher)

            base.evaluate()

            Dispatchers.resetMain()
            testDispatcher.cleanupTestCoroutines()
        }

    }

}