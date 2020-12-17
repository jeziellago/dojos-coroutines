import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import java.io.File

suspend fun readOrders(): List<Order> {
    return withContext(Dispatchers.Default) {
        val first = async { readFile("PostalService.json").filter { it.isBadChild.not() } }
        val second = async { readFile("NatalWeb.json").filter { it.isBadChild.not() } }

        val resultFirst = first.await()
        val secondFirst = second.await()

        resultFirst.toMutableList().addAll(secondFirst)

        resultFirst
    }

}

suspend fun readFile(path: String): List<Order> {
    return withContext(Dispatchers.IO) {
        val asString: String = File(path).readText()
        Json.parseToJsonElement(asString).jsonArray.map { jsonElement ->
            Json.decodeFromString(Order.serializer(), jsonElement.toString())
        }
    }
}

suspend fun createFile(map: Map<String, List<Order>>) {
    withContext(Dispatchers.IO) {
        map.forEach { (key, value) ->
            val deliveryData = DeliveryList(
                continent = key,
                orderList = value.map {
                    OrderForDelivery(
                        name = it.name,
                        gender = it.gender,
                        address = it.address,
                        email = it.email,
                        gift = it.wishGift
                    )
                }
            )
            File("$key.json").writeText(Json.encodeToString(DeliveryList.serializer(), deliveryData))
        }
    }
}

fun main() {
    // Generate report by continent
    println("Go!")

    runBlocking {
//        println(Thread.currentThread().name)
        val list: Map<String, List<Order>> = readOrders().groupBy { it.continent }
        createFile(list)
    }

    println()
}
