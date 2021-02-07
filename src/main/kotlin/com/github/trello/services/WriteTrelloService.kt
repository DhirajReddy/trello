package com.github.trello.services

import com.github.trello.models.*
import com.github.trello.stores.TrelloStore

class WriteTrelloService(private val trelloStore: TrelloStore): IWriteTrelloService {
    override fun addBoard(board: IBoard): Boolean {
        trelloStore.boards.add(board.copy() as IBoard)
        return true
    }

    override fun addProject(board: IBoard, project: IProject): Boolean {
        return trelloStore.boards.firstOrNull { board.id == it.id }
                ?.projects?.add(project.copy() as IProject)?.let { true } ?: false
    }

    override fun addCard(board: IBoard, project: IProject, card: ICard): Boolean {
        return trelloStore.boards.firstOrNull { board.id == it.id }
                ?.projects?.firstOrNull { project.id == it.id }
                ?.cards?.add(card.copy() as ICard)?.let { true } ?: false
    }

    override fun assignCard(board: IBoard, project: IProject, card: ICard, member: IMember): Boolean {
        if (trelloStore.members.any { it.id == member.id }) {
            val cardFound = trelloStore.boards.firstOrNull { board.id == it.id }
                    ?.projects?.firstOrNull { project.id == it.id }
                    ?.cards?.firstOrNull { it.id == card.id }
            return cardFound?.let {
                it.assignee = member.id
                true
            } ?: false
        }
        return false
    }

    override fun removeBoard(board: IBoard): Boolean {
        return trelloStore.boards.removeIf {
            it.id == board.id
        }
    }

    override fun removeProject(board: IBoard, project: IProject): Boolean {
        return trelloStore.boards.firstOrNull { board.id == it.id }
                ?.projects?.removeIf { it.id == project.id } ?: false
    }

    override fun removeCard(board: IBoard, project: IProject, card: ICard): Boolean {
        return trelloStore.boards.firstOrNull { board.id == it.id }
                ?.projects?.firstOrNull { project.id == it.id }
                ?.cards?.removeIf { it.id == card.id } ?: false
    }

    override fun unAssignCard(board: IBoard, project: IProject, card: ICard): Boolean {
        val newCard = trelloStore.boards.firstOrNull { board.id == it.id }
                ?.projects?.firstOrNull { project.id == it.id }
                ?.cards?.firstOrNull { it.id == card.id }
        return newCard?.let {
            it.assignee = null
            true
        } ?: false
    }
}
