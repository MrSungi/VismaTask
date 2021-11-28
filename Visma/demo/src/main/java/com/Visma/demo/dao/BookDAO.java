package com.Visma.demo.dao;

import com.Visma.demo.entity.Book;
import java.io.IOException;
import java.util.List;

public interface BookDAO {

    boolean addBook(Book book) throws IOException;
    Book takeBook(String GUID);
    List<Book> getBooks(String name, String author, String category, String language, Boolean isTaken, String isbn) throws IOException;
    boolean deleteBook(String GUID) throws IOException;
}
