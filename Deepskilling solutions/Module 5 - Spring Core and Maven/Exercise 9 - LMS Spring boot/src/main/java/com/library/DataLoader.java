package com.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.library.model.Book;
import com.library.repository.BookRepository;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(BookRepository bookRepository) {
        return args -> {
            if (bookRepository.count() == 0) {
                bookRepository.save(new Book("The Hobbit", "J.R.R. Tolkien"));
                bookRepository.save(new Book("1984", "George Orwell"));
                bookRepository.save(new Book("To Kill a Mockingbird", "Harper Lee"));
            }
        };
    }
}
