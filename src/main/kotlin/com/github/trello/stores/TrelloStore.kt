package com.github.trello.stores

import com.github.trello.models.IBoard
import com.github.trello.models.IMember
import javax.inject.Singleton

@Singleton
class TrelloStore {
    val boards = mutableListOf<IBoard>()
    val members = mutableListOf<IMember>()

    fun serialise() {

    }

    fun deSerialise() {

    }
}
