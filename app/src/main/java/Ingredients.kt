import java.io.Serializable


data class Ingredients(
    val hops: List<Any>,
    val malt: List<Malt>,
    val yeast: String
) : Serializable