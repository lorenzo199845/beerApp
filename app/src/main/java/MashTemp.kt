import java.io.Serializable

data class MashTemp(
    val duration: Int,
    val temp: TempX
) : Serializable