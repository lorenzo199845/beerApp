import java.io.Serializable

data class Malt(
    val amount: Amount,
    val name: String
) : Serializable