package com.github.trello.models

data class Card(override var assignee: String?,
                override val id: String,
                override val name: String): ICard {
    override fun copy(): ICard {
        return Card(assignee, id, name)
    }
}
