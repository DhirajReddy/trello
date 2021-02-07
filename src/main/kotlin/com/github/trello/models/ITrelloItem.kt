package com.github.trello.models

interface ITrelloItem {
    val id: String
    val name: String
    fun copy(): ITrelloItem
}


