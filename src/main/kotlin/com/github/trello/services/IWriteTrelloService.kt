package com.github.trello.services

import com.github.trello.models.IBoard
import com.github.trello.models.ICard
import com.github.trello.models.IMember
import com.github.trello.models.IProject

interface IWriteTrelloService {
    fun addBoard(board: IBoard): Boolean
    fun addProject(board: IBoard, project: IProject): Boolean
    fun addCard(board: IBoard, project: IProject, card: ICard): Boolean
    fun assignCard(board: IBoard, project: IProject, card: ICard, member: IMember): Boolean

    fun removeBoard(board: IBoard): Boolean
    fun removeProject(board: IBoard, project: IProject): Boolean
    fun removeCard(board: IBoard, project: IProject, card: ICard): Boolean
    fun unAssignCard(board: IBoard, project: IProject, card: ICard): Boolean
}
