import java.io.Serializable

data class Amount(
    val unit: String,
    val value: Double
) : Serializable