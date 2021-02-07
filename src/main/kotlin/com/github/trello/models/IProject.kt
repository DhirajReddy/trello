package com.github.trello.models

interface IProject: ITrelloItem {
    val cards: MutableList<ICard>
}
