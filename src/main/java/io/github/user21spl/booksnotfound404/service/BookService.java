
package io.github.user21spl.booksnotfound404.service;

import io.github.user21spl.booksnotfound404.model.Book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




public interface BookService {   
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int bookId);
    Book addBookIfNotExists(Book book);
    
    Book getBook(int BookId);
    List<Book> getAllBooks();
    List<Book> searchBooks(String keyword); // keyword based search (title/category/author)
    
    boolean isBookInStock(int bookId);
    public List<String> getAllGenres();
    public List<Book> searchBooksByGenre(String genre);
 
}
