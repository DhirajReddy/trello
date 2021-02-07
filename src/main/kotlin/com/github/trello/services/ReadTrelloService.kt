package com.github.trello.services

import com.github.trello.models.*
import com.github.trello.stores.TrelloStore
import javax.inject.Singleton

@Singleton
class ReadTrelloService(private val trelloStore: TrelloStore): IReadTrelloService<ITrelloItem> {
    override fun getAllBoards(): List<IBoard> {
        return trelloStore.boards.map { it.copy() as IBoard }
    }

    override fun getAllProjects(boardId: String): List<IProject>? {
        return trelloStore.boards.firstOrNull { it.id == boardId }?.projects?.map { it.copy() as IProject }
    }

    override fun getAllCards(boardId: String, projectId: String): List<ICard>? {
        return trelloStore.boards.firstOrNull { it.id == boardId }
                ?.projects?.firstOrNull { it.id == projectId}
                ?.cards?.map { it.copy() as ICard }
    }

    override fun getBoard(boardId: String): IBoard? {
        return trelloStore.boards.firstOrNull { it.id == boardId }
    }

    override fun getProject(boardId: String, projectId: String): IProject? {
        return trelloStore.boards.firstOrNull { it.id == boardId }
                ?.projects?.firstOrNull { it.id == projectId }
    }

    override fun getCard(boardId: String, projectId: String, cardId: String): ICard? {
        return trelloStore.boards.firstOrNull { it.id == boardId }
                ?.projects?.firstOrNull { it.id == projectId }
                ?.cards?.firstOrNull { it.id == cardId }
    }
}
