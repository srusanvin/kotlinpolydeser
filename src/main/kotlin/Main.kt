import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Files.readAllBytes
import java.nio.file.Files.write
import java.nio.file.StandardOpenOption

fun main(args: Array<String>) {
    val mapper = JsonMapper.builder().addModule(
        KotlinModule.Builder()
            .configure(KotlinFeature.NullIsSameAsDefault, enabled = true)
            .build()
    )
        .activateDefaultTyping(
            BasicPolymorphicTypeValidator.builder().allowIfSubType(Movie::class.java).build(),
            ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE, JsonTypeInfo.As.PROPERTY
        )
        .build()

    val movie: Programme = Movie(true)
    val serialized = mapper.writeValueAsBytes(WrapperProgramme(movie))
    val path = Files.createTempDirectory("disk-file").apply { toFile() }
    val filePath = path.resolve("my_movie.json")
    write(filePath, serialized, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    val fileContent = readAllBytes(filePath)
    val readValue = mapper.readValue(fileContent, WrapperProgramme::class.java).programme
    println(readValue)
}