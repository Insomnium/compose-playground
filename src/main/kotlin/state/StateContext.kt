package state

import control.TextAreaContent
import java.util.UUID

data class StateContext(private val textAreas: MutableList<TextAreaContent> = mutableListOf()) {

    private val textAreasById: MutableMap<UUID, TextAreaContent> = mutableMapOf()

    operator fun plusAssign(textAreaContent: TextAreaContent) {
        textAreasById[textAreaContent.id] = textAreaContent
    }

    operator fun get(id: UUID): TextAreaContent? = textAreasById[id]

    fun getTextAreas(): List<TextAreaContent> = textAreas.toList()

    val size get() = textAreas.size

    val values: List<TextAreaContent> get() = textAreasById.values.toList()
}
