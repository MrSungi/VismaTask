package com.Visma.demo.Service;

import com.Visma.demo.dao.BookDAO;
import com.Visma.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDao;

    @Override
    public boolean addBook(Book book) throws IOException {
        UUID uuid = UUID.randomUUID();
        book.setGUID(uuid.toString());

        return bookDao.addBook(book);
    }

    @Override
    public Book takeBook(String GUID) {
        return bookDao.takeBook(GUID);
    }

    @Override
    public List<Book> getBooks(String name, String author, String category, String language, Boolean isTaken, String isbn) throws IOException {
        return bookDao.getBooks(name, author, category, language, isTaken, isbn);
    }


    @Override
    public boolean deleteBook(String GUID) throws IOException {
        return bookDao.deleteBook(GUID);
    }
}
