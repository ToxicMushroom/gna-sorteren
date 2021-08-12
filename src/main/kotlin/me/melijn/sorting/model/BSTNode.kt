package me.melijn.sorting.model

data class BSTNode<T>(
    val left: BSTNode<T>?,
    val value: T,
    val right: BSTNode<T>?
)