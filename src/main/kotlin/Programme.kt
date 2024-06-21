abstract class Programme {
    val synopsis: List<String>
        get() = resolveData()

    private fun resolveData(): List<String> {
        return arrayListOf("my synopsis1")
    }
}
data class Movie(val lastInSeason: Boolean? = null): Programme() {}
data class WrapperProgramme<T> (val programme: T) {}