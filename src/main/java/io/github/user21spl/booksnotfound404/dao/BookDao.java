package io.github.user21spl.booksnotfound404.dao;

// basic imports
import java.util.*;

// import models
import io.github.user21spl.booksnotfound404.model.Book;



public interface BookDao {
    void saveBook(Book book); //by admin
    void updateBook(Book book); //by admin
    void deleteBook(int bookId); //by admin
    Book findBook(int bookId); 
    Book findByTitleAndAuthor(String title, String author);
    List<Book> findAllBooks();
    List<Book> searchBooksByTitle(String title);
    List<Book> searchBooksByAuthor(String author);
    List<Book> searchBooksByGenre(String genre); 
    public List<String> getDistinctGenres();
}
