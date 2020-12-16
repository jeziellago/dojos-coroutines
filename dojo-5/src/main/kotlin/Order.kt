import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Order(
    @SerialName("name")
    val name: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("address")
    val address: String,
    @SerialName("continent")
    val continent: String,
    @SerialName("email")
    val email: String,
    @SerialName("wish_gift")
    val wishGift: String,
    @SerialName("bad_child")
    val isBadChild: Boolean
)
