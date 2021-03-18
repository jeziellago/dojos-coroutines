import kotlinx.coroutines.runBlocking
import java.math.BigDecimal

sealed class WalletOperation {
    data class Withdraw(val value: BigDecimal) : WalletOperation()
    data class Deposit(val value: BigDecimal) : WalletOperation()
}

class Wallet(private var balance: BigDecimal = BigDecimal.ZERO) {

    fun applyOperation(operation: WalletOperation) {
        when (operation) {
            is WalletOperation.Withdraw -> {
                balance -= operation.value
            }
            is WalletOperation.Deposit -> {
                balance += operation.value
            }
        }
    }

    fun currentBalance(): BigDecimal = balance
}

class WalletObserver {

    fun onWalletChanged(
        operation: WalletOperation,
        oldBalance: BigDecimal,
        newBalance: BigDecimal
    ) {
        println("[$operation]\n\tOld Balance: [R$ $oldBalance] | New Balance: [R$ $newBalance]")
    }
}

fun main() = runBlocking<Unit> {

    val wallet = Wallet()

    wallet.applyOperation(WalletOperation.Deposit(1999F.toBigDecimal()))

    WalletObserver().onWalletChanged(
        WalletOperation.Deposit(1999F.toBigDecimal()),
        0F.toBigDecimal(),
        wallet.currentBalance()
    )

}