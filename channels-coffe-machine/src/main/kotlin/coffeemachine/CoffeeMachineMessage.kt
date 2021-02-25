package coffeemachine

sealed class CoffeeMachineMessage {

    sealed class Receive {
        object Status : Receive()
        data class Order(val targetCoffeeQuantity: Int) : Receive()
        data class Start(val authorization: String, val quantity: Int) : Receive()
    }

    sealed class Send {
        data class MachineId(val value: String) : Send()
        object InProgress : Send()
        data class Finished(val quantity: Int) : Send()
        data class Canceled(val quantity: Int) : Send()
    }
}