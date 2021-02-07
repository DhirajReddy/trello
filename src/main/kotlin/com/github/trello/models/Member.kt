package com.github.trello.models

data class Member(override val id: String,
                  override val name: String): IMember {
    override fun copy(): IMember {
        return Member(id, name)
    }
}
