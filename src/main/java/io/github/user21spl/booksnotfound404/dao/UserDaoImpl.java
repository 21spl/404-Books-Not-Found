
package io.github.user21spl.booksnotfound404.dao;



import java.util.*;

// hibernate imports
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


// spring annotation imports
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


// model import
import io.github.user21spl.booksnotfound404.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveUser(User user) {
        getSession().save(user);
    }

    @Override
    public User findById(int id) {
        return getSession().get(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        String hql = "FROM User WHERE email = :email";
        Query<User> query = getSession().createQuery(hql, User.class);
        query.setParameter("email", email);
        return query.uniqueResult();
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        String hql = "FROM User WHERE email = :email AND password = :password";
        Query<User> query = getSession().createQuery(hql, User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.uniqueResult();
    }

    @Override
    public List<User> findAllUsers() {
        String hql = "FROM User";
        return getSession().createQuery(hql, User.class).list();
    }

    @Override
    public void updateUser(User user) {
        if (user != null) {
            getSession().update(user);
        }
    }

    @Override
    public void deleteUser(int id) {
        User user = getSession().get(User.class, id);
        if (user != null) {
            getSession().delete(user);
        }
    }
}
