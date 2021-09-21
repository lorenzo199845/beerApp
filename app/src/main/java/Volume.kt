import java.io.Serializable

data class Volume(
    val unit: String,
    val value: Int
) : Serializable