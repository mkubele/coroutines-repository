package com.example.demo

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PublishingService(
    private val authorRepository: AuthorRepository,
    private val bookRepository: BookRepository
) {
    @Transactional
    suspend fun addAuthorWithBooks(author: Author, books: List<Book>) {
        val savedAuthor = authorRepository.save(author)

        books.forEach { book ->
            book.copy(authorId = savedAuthor.id!!).also {
                bookRepository.save(it)
            }
        }
    }
}
