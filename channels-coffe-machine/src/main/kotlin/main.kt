import app.CoffeeMachineApp
import coffeemachine.CoffeeMachine
import coffeemachine.CoffeeMachineMessage
import server.CoffeeMachineServer

fun log(tag: String, operation: String) {
    println("[$tag]: $operation")
}

fun main() {

    val app = CoffeeMachineApp()
    val server = CoffeeMachineServer()
    val coffeeMachine = CoffeeMachine()

    app.connectCoffeeMachineServer(server)
    app.connectCoffeeMachineBluetooth(coffeeMachine)

    app.orderCoffee(CoffeeMachineMessage.Receive.Order(200))
}