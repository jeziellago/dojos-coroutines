import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

/**
 * Kotlin Channels
 * - Similar BlockingQueue (Java)
 * - Hot Streams
 * - Tipos de channels (capacity)
 * - ReceiveChannel e SendChannel
 * - Estratégias de BufferOverflow
 * - Fechamento do stream
 * */


fun main() = runBlocking<Unit> {
    val palestrante = Palestrante()
    val espectadores = listOf(
        Espectador("Henrique"),
        Espectador("Roger"),
        Espectador("Joao"),
        Espectador("Ciclano"),
        Espectador("Beltrano"),
        Espectador("Hanna"),
        Espectador("Nani"),
        Espectador("Judite da Tim"),
    )

    val palestra = Palestra(palestrante).apply {
        espectadores.forEach {
            entrarPalestra(it)
        }
    }

    palestra.iniciarPalestra()
}

class Palestrante {
    private val channel: Channel<String> = Channel()
    val receiveChannel: ReceiveChannel<String>
        get() = channel

    suspend fun palestrar() {
        var msgCounter = 1
        repeat(40) {
            if (!channel.isClosedForSend) {
                channel.send("[${msgCounter++}] Fala galerinha!")
                delay(1000L)
            }
        }
        channel.close()
    }
}

class Espectador(
    private val nome: String,
) {
    fun ouvir(msg: String) {
        println("$nome: $msg")
    }

}

class Palestra(
    private val palestrante: Palestrante,
) {
    private val espectadores: ArrayList<Espectador> = arrayListOf()

    fun entrarPalestra(espectador: Espectador): Boolean = espectadores.add(espectador)

    suspend fun iniciarPalestra() {
        coroutineScope {
            launch {
                palestrante.palestrar()
            }
            launch {
                palestrante.receiveChannel.consumeEach { msg ->
                    espectadores.forEach { spec ->
                        spec.ouvir(msg)
                    }
                }
            }
        }
    }
}
