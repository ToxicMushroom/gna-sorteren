package me.melijn.sorting.model

data class BinaryNode<T>(
    val left: BinaryNode<T>?,
    val value: T,
    val right: BinaryNode<T>?
)