package app

import coffeemachine.CoffeeMachine
import coffeemachine.CoffeeMachineMessage
import log
import server.CoffeeMachineServer
import java.math.BigDecimal

class CoffeeMachineApp {

    fun connectCoffeeMachineBluetooth(machine: CoffeeMachine) {
        // machine.openBluetooth()
    }

    fun connectCoffeeMachineServer(server: CoffeeMachineServer) {
        // server.newCoffeeAppConnection()
    }

    fun orderCoffee(order: CoffeeMachineMessage.Receive.Order) {
        log("APP", order.toString())
        TODO()
    }

    private fun onCompleteCoffeeOrder(deliveredCoffee: Int, valueToPay: BigDecimal) {
        TODO()
    }

}