
package io.github.user21spl.booksnotfound404.service;

import io.github.user21spl.booksnotfound404.model.Book;
import io.github.user21spl.booksnotfound404.dao.BookDao;

import java.util.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BookServiceImpl implements BookService{
    
    @Autowired
    private BookDao bookDao;
    
    
    
    @Override
    public void addBook(Book book)
    {
        bookDao.saveBook(book);
    }
    
    @Override
    public Book addBookIfNotExists(Book book)
    {
        Book existing = bookDao.findByTitleAndAuthor(book.getTitle(), book.getAuthor());
        if(existing!=null)
        {
            return existing;
        }
        bookDao.saveBook(book);
        return null;
    }
    
    @Override
    public void updateBook(Book book)
    {
        bookDao.updateBook(book);
    }
    
    @Override
    public void deleteBook(int bookId)
    {
        bookDao.deleteBook(bookId);
    }
    
    @Override
    public Book getBook(int bookId)
    {
        return bookDao.findBook(bookId);
    }
    
    @Override
    public List<Book> getAllBooks(){
       return bookDao.findAllBooks(); 
    }
    
    @Override
    public List<Book> searchBooks(String keyword)
    {
        Set<Book> results = new LinkedHashSet();
        
        List<Book> byTitle = bookDao.searchBooksByTitle(keyword);
        if(byTitle !=null) results.addAll(byTitle);
        
        List<Book> byAuthor = bookDao.searchBooksByAuthor(keyword);
        if(byAuthor !=null) results.addAll(byAuthor);
        
        List<Book> byGenre = bookDao.searchBooksByGenre(keyword);
        if (byGenre != null) results.addAll(byGenre);
        
        return new ArrayList<>(results);
    }
    
    @Override
    public boolean isBookInStock(int bookId)
    {
        Book book = bookDao.findBook(bookId);
        return book!=null && book.getCopies()>0;
    }
    
    @Override
    public List<String> getAllGenres() {
        return bookDao.getDistinctGenres();  // or getDistinctGenres()
    }
    
    @Override
    public List<Book> searchBooksByGenre(String genre){
        return bookDao.searchBooksByGenre(genre);
    }
    
    
}
