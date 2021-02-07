package com.github.trello.services

interface IReadTrelloService<out T> {
    fun getAllBoards(): List<T>?
    fun getAllProjects(boardId: String): List<T>?
    fun getAllCards(boardId: String, projectId: String): List<T>?
    fun getBoard(boardId: String): T?
    fun getProject(boardId: String, projectId: String): T?
    fun getCard(boardId: String, projectId: String, cardId: String): T?
}
