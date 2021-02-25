package coffeemachine

import kotlinx.coroutines.delay
import log

class CoffeeMachine {

    fun openBluetooth() {
        TODO()
    }

    suspend fun accept(message: CoffeeMachineMessage.Receive) {
        log("MACHINE-received", message.toString())
        when(message) {
            is CoffeeMachineMessage.Receive.Order -> TODO()
            is CoffeeMachineMessage.Receive.Start -> TODO()
            is CoffeeMachineMessage.Receive.Status -> TODO()
        }
    }

    fun closeBluetooth() {
        TODO()
    }

    private fun finishDelivery(deliveredQuantity: Int) {
        TODO()
    }

    private suspend fun startCoffeeDelivery(targetQuantity: Int) {
        // dummy logic to simulate delivery
        repeat(targetQuantity) { delay(100L) }
        // finish delivery
    }
}