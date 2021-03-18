import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.math.BigDecimal

sealed class WalletOperation {
    data class Withdraw(val value: BigDecimal) : WalletOperation()
    data class Deposit(val value: BigDecimal) : WalletOperation()
}

class Wallet(
    private var balance: BigDecimal = BigDecimal.ZERO,
    private val channel: Channel<WalletChange> = Channel(10),
) {

    val receiveChannel: ReceiveChannel<WalletChange> = channel

    suspend fun applyOperation(operation: WalletOperation) {
        val walletChange = WalletChange(operation, balance, 0.toBigDecimal())

        when (operation) {
            is WalletOperation.Withdraw -> {
                balance -= operation.value
            }
            is WalletOperation.Deposit -> {
                balance += operation.value
            }
        }

        channel.send(walletChange.copy(newBalance = balance))
    }

    fun currentBalance(): BigDecimal = balance
}

data class WalletChange(
    val operation: WalletOperation,
    val oldBalance: BigDecimal,
    val newBalance: BigDecimal,
)

class WalletObserver {
    fun onWalletChanged(walletChange: WalletChange) {
        walletChange.run {
            println("(${this@WalletObserver.hashCode()})\n\t[$operation]\n\tOld Balance: [R$ $oldBalance] | New Balance: [R$ $newBalance]")
        }
    }
}

data class WalletPublisher(
    private val coroutineScope: CoroutineScope,
    private val channel: ReceiveChannel<WalletChange>
) {
    private val observers = mutableListOf<WalletObserver>()

    init {
        coroutineScope.launch {
            channel.consumeEach { change ->
                observers.forEach { observer ->
                    observer.onWalletChanged(change)
                }
            }
        }
    }

    fun subscribe(observer: WalletObserver) {
        observers.add(observer)
    }

}

fun main() = runBlocking<Unit> {

    val wallet = Wallet()

    val publisher = WalletPublisher(this, wallet.receiveChannel)

    val ob1 = WalletObserver()
    val ob2 = WalletObserver()

    publisher.subscribe(ob1)
    publisher.subscribe(ob2)

    launch {
        wallet.applyOperation(WalletOperation.Deposit(5000.toBigDecimal()))
        wallet.applyOperation(WalletOperation.Withdraw(1000F.toBigDecimal()))
        wallet.applyOperation(WalletOperation.Deposit(2000.toBigDecimal()))
        wallet.applyOperation(WalletOperation.Withdraw(1000.toBigDecimal()))
    }
}