
package io.github.user21spl.booksnotfound404.service;


import io.github.user21spl.booksnotfound404.model.*;
import io.github.user21spl.booksnotfound404.dao.*;

import java.util.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ExternalBookService {
    
    // for fetched books only - static inner class
    public static class ExternalBook{
        private String title;
        private String author;
        private String genre;
        private double price;
        
        
         public ExternalBook(String title, String author, String genre, double price) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getGenre() {
            return genre;
        }

        public double getPrice() {
            return price;
        }
        
        
    }
    
    
    public List<ExternalBook> fetchBestSellingBooks(){
        List<ExternalBook> books = new ArrayList<>();
        
        books.add(new ExternalBook("Don Quixote", "Miguel de Cervantes", "Classic Fiction", 399.0));
        books.add(new ExternalBook("A Tale of Two Cities", "Charles Dickens", "Historical Fiction", 299.0));
        books.add(new ExternalBook("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 699.0));
        books.add(new ExternalBook("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "Fantasy", 499.0));
        books.add(new ExternalBook("The Little Prince", "Antoine de Saint-Exupéry", "Fable", 299.0));
        books.add(new ExternalBook("The Hobbit", "J.R.R. Tolkien", "Fantasy", 449.0));
        books.add(new ExternalBook("And Then There Were None", "Agatha Christie", "Mystery", 349.0));
        books.add(new ExternalBook("Dream of the Red Chamber", "Cao Xueqin", "Classic Chinese Literature", 399.0));
        books.add(new ExternalBook("The Da Vinci Code", "Dan Brown", "Thriller", 399.0));
        books.add(new ExternalBook("Pather Panchali", "Bibhutibhushan Bandyopadhyay", "Bengali Fiction", 299.0));
        books.add(new ExternalBook("Chokher Bali", "Rabindranath Tagore", "Bengali Fiction", 249.0));
        books.add(new ExternalBook("Gitanjali", "Rabindranath Tagore", "Poetry", 199.0));
        books.add(new ExternalBook("Feluda Samagra", "Satyajit Ray", "Detective", 499.0));
        books.add(new ExternalBook("Byomkesh Bakshi", "Sharadindu Bandyopadhyay", "Detective", 399.0));
        books.add(new ExternalBook("Aranyak", "Bibhutibhushan Bandyopadhyay", "Bengali Fiction", 249.0));
        books.add(new ExternalBook("Shesher Kobita", "Rabindranath Tagore", "Romance", 199.0));
        books.add(new ExternalBook("Devdas", "Sarat Chandra Chattopadhyay", "Classic Fiction", 199.0));
        books.add(new ExternalBook("One Hundred Years of Solitude", "Gabriel García Márquez", "Magical Realism", 399.0)); // Spanish original
        books.add(new ExternalBook("Norwegian Wood", "Haruki Murakami", "Japanese Fiction", 349.0)); // Japanese
        books.add(new ExternalBook("Kafka on the Shore", "Haruki Murakami", "Japanese Fiction", 399.0));
        books.add(new ExternalBook("The Name of the Rose", "Umberto Eco", "Historical Mystery", 399.0)); // Italian
        books.add(new ExternalBook("The Girl with the Dragon Tattoo", "Stieg Larsson", "Crime Thriller", 449.0)); // Swedish
        books.add(new ExternalBook("The Kite Runner", "Khaled Hosseini", "Afghan-American Fiction", 349.0));
        books.add(new ExternalBook("The Shadow of the Wind", "Carlos Ruiz Zafón", "Spanish Fiction", 349.0));
        books.add(new ExternalBook("Man's Search for Meaning", "Viktor E. Frankl", "Psychology", 299.0)); // German original
        books.add(new ExternalBook("The Prophet", "Kahlil Gibran", "Philosophical", 199.0)); // Arabic-English

        return books;
    }
}
