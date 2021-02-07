package com.github.trello.services

import com.github.trello.models.*
import kotlin.random.Random

class TrelloService(private val readTrelloService: ReadTrelloService,
private val writeTrelloService: WriteTrelloService) {
    private val idCharacters = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private val idLength = 10

    private fun randomIdGenerator(): String {
        return (0 until 10).map { Random.nextInt(0, idCharacters.size) }
                .map { idCharacters[it] }
                .joinToString("")
    }

    fun addBoard(name: String): Boolean {
        val board = Board(mutableListOf(), randomIdGenerator(), name)
        return writeTrelloService.addBoard(board)
    }

    fun addProject(name: String, board: IBoard): Boolean {
        val boardToAdd = readTrelloService.getBoard(board.id)
        val projectToAdd = Project(mutableListOf(), randomIdGenerator(), name)
        return boardToAdd?.let {
            writeTrelloService.addProject(it, projectToAdd)
        } ?: false
    }

    fun addCard(name: String, project: IProject, board: IBoard): Boolean {
        val boardToAdd = readTrelloService.getBoard(board.id)
        if (boardToAdd != null) {
            val projectToAdd = readTrelloService.getProject(boardToAdd.id, project.id)
            if (projectToAdd != null) {
                val cardToAdd = Card(null, randomIdGenerator(), name)
                return writeTrelloService.addCard(boardToAdd, projectToAdd, cardToAdd)
            }
        }
        return false
    }

    fun addCard(card: ICard, project: IProject, board: IBoard): Boolean {
        val boardToAdd = readTrelloService.getBoard(board.id)
        if (boardToAdd != null) {
            val projectToAdd = readTrelloService.getProject(boardToAdd.id, project.id)
            if (projectToAdd != null) {
                val cardToAdd = card.copy() as ICard
                return writeTrelloService.addCard(boardToAdd, projectToAdd, cardToAdd)
            }
        }
        return false
    }

    fun assignCard(card: ICard, project: IProject, board: IBoard, member: IMember): Boolean {
        return writeTrelloService.assignCard(board, project, card, member)
    }

    fun unAssignCard(card: ICard, project: IProject, board: IBoard, member: IMember): Boolean {
        return writeTrelloService.unAssignCard(board, project, card)
    }

    fun deleteBoard(board: IBoard): Boolean {
        return writeTrelloService.removeBoard(board)
    }

    fun deleteProject(project: IProject, board: IBoard): Boolean {
        return writeTrelloService.removeProject(board, project)
    }

    fun deleteCard(card: ICard, project: IProject, board: IBoard): Boolean {
        return writeTrelloService.removeCard(board, project, card)
    }

    fun moveCard(card: ICard, fromProject: IProject, toProject: IProject, board: IBoard): Boolean {
        return deleteCard(card, fromProject, board) && addCard(card, toProject, board)
    }
}
