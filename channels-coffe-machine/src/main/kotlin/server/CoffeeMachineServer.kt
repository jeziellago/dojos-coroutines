package server

import coffeemachine.CoffeeMachineMessage
import log
import java.math.BigDecimal

class CoffeeMachineServer {

    fun newCoffeeAppConnection() {
        TODO()
    }

    private fun handleMessage(message: CoffeeMachineMessage.Send) {
        log("SERVER-received", message.toString())
        when(message) {
            is CoffeeMachineMessage.Send.MachineId -> TODO()
            is CoffeeMachineMessage.Send.InProgress -> TODO()
            is CoffeeMachineMessage.Send.Canceled -> TODO()
            is CoffeeMachineMessage.Send.Finished -> TODO()
        }
    }

    private fun calculatePaymentFromCoffee(quantity: Int): BigDecimal {
        log("SERVER", "Calculate payment from $quantity")
        return (0.25 * quantity).toBigDecimal()
    }

}