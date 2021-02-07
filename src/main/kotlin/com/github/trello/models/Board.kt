package com.github.trello.models

data class Board(override val projects: MutableList<IProject>,
                 override val id: String,
                 override val name: String): IBoard {
    override fun copy(): IBoard {
        val copiedProjects = projects.map { it.copy() as IProject }
                .toMutableList()
        return Board(copiedProjects, id, name)
    }
}
