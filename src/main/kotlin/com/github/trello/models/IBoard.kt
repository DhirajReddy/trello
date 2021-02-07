package com.github.trello.models

interface IBoard: ITrelloItem {
    val projects: MutableList<IProject>
}
