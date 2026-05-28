
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import tools.jackson.databind.JsonNode
import tools.jackson.databind.ObjectMapper

@Converter
class JsonNodeConverter : AttributeConverter<JsonNode, String> {
    private val mapper = ObjectMapper()

    override fun convertToDatabaseColumn(attribute: JsonNode?): String =
        if (attribute == null) "[]" else mapper.writeValueAsString(attribute)

    override fun convertToEntityAttribute(dbData: String?): JsonNode =
        if (dbData.isNullOrBlank()) mapper.createArrayNode()
        else mapper.readTree(dbData)
}