package com.github.trello.models

data class Project(override val cards: MutableList<ICard>,
                   override var id: String,
                   override val name: String): IProject {
    override fun copy(): ITrelloItem {
        val clonedCards = cards.map { it.copy() as ICard }.toMutableList()
        return Project(clonedCards, id, name)
    }
}
