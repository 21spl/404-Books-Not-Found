
package io.github.user21spl.booksnotfound404.dao;

// import the Book Model
import io.github.user21spl.booksnotfound404.model.Book;

// hibernate imports
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


// spring annotation imports
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


//basic imports
import java.util.*;

@Repository
@Transactional
public class BookDaoImpl implements BookDao{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    public void saveBook(Book book)
    {
        getSession().save(book);
    }
    
    @Override
    public void updateBook(Book book)
    {
        getSession().update(book);
    }
    
    @Override
    public void deleteBook(int bookId)
    {
        Book book = getSession().get(Book.class, bookId);
        if(book != null)
            getSession().delete(book);
    }
    
    @Override
    public Book findBook(int bookId)
    {
        return getSession().get(Book.class, bookId);
    }
    
    @Override
    public Book findByTitleAndAuthor(String title, String author){
        String hql = "FROM Book WHERE title = :title AND author = :author";
        return getSession()
                .createQuery(hql, Book.class)
                .setParameter("title", title)
                .setParameter("author", author)
                .uniqueResult();
    }
    
    @Override
    public List<Book> findAllBooks()
    {
        String hql = "FROM Book";
        Query<Book> query = getSession().createQuery(hql, Book.class);
        //Query<Book> will return results of type Book
        return query.getResultList();
    }
    
    @Override
    public List<Book> searchBooksByTitle(String title)
    {
        String hql = "FROM book WHERE lower(title) LIKE :title";
        Query<Book> query = getSession().createQuery(hql, Book.class);
        query.setParameter("title", "%" + title.toLowerCase() + "%");
        return query.getResultList();
    }
    
    @Override
    public List<Book> searchBooksByAuthor(String author)
    {
        String hql = "FROM Book WHERE lower(author) LIKE :author";
        Query<Book> query = getSession().createQuery(hql, Book.class);
        query.setParameter("author", "%" + author.toLowerCase() + "%");
        return query.getResultList();
    }
    
    @Override
    public List<Book> searchBooksByGenre(String genre)
    {
        String hql = "FROM Book WHERE lower(genre) LIKE :genre";
        Query<Book> query = getSession().createQuery(hql, Book.class);
        query.setParameter("genre", "%" + genre.toLowerCase() + "%");
        return query.getResultList();
    }
    
    // fetch all genres
    @Override
    public List<String> getDistinctGenres() {
        String hql = "SELECT DISTINCT b.genre FROM Book b WHERE b.genre IS NOT NULL";
        Query<String> query = getSession().createQuery(hql, String.class);
        return query.getResultList();
        
    }
}