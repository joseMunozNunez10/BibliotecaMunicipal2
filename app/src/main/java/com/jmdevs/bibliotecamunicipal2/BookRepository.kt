package com.jmdevs.bibliotecamunicipal2

import kotlinx.coroutines.delay

class BookRepository {

    private val books = listOf(
        Book("1", "Cien años de soledad", "Gabriel García Márquez", true),
        Book("2", "El Señor de los Anillos", "J.R.R. Tolkien", false),
        Book("3", "1984", "George Orwell", true),
        Book("4", "Un mundo feliz", "Aldous Huxley", true),
        Book("5", "Don Quijote de la Mancha", "Miguel de Cervantes", false),
        Book("6", "El Principito", "Antoine de Saint-Exupéry", true),
        Book("7", "La Sombra del Viento", "Carlos Ruiz Zafón", true),
        Book("8", "Rayuela", "Julio Cortázar", false),
        Book("9", "Ficciones", "Jorge Luis Borges", true),
        Book("10", "El túnel", "Ernesto Sabato", true)
    )

    suspend fun search(query: String): List<Book> {
        delay(2000)
        return books.filter {
            it.title.contains(query, ignoreCase = true) || it.author.contains(query, ignoreCase = true)
        }
    }
}
