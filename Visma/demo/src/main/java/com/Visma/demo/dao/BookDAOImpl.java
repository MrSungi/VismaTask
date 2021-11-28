package com.Visma.demo.dao;

import com.Visma.demo.entity.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    ObjectMapper objectMapper = new ObjectMapper();
    private  File JSONfile = new File(System.getProperty("user.dir") +"\\demo\\data\\library.json");

    //read JSON file and map/convert to Java POJO: data/library.json
    List<Book> theBooks =
            objectMapper.readValue(JSONfile, new TypeReference<List<Book>>(){});

    public BookDAOImpl() throws IOException {
    }


    @Override
    public boolean addBook(Book book) throws IOException {
        int size = theBooks.size();
        theBooks.add(book);
        objectMapper.writeValue(JSONfile, theBooks);
        if(size == theBooks.size()-1){
            return true;
        }
        return false;
    }

    @Override
    public Book takeBook(String GUID) {
        for(Book theBook: theBooks){
            if(theBook.getGUID().equals(GUID)){
                return theBook;
            }
        }
        return null;
    }

    @Override
    public List<Book> getBooks(String name, String author, String category, String language, Boolean isTaken, String isbn) throws IOException {
        List<Book> filteredBooks = new ArrayList<>();

        if(author != null){
            for(Book book: theBooks){
                if(book.getAuthor().equals(author)){
                    filteredBooks.add(book);
                }
            }
            return filteredBooks;
        }

        if(category != null){
            for(Book book: theBooks){
                if(book.getCategory().equals(category)){
                    filteredBooks.add(book);
                }
            }
            return filteredBooks;
        }

        if(language != null){
            for(Book book: theBooks){
                if(book.getLanguage().equals(language)){
                    filteredBooks.add(book);
                }
            }
            return filteredBooks;
        }

        if(isbn != null){
            for(Book book: theBooks){
                if(book.getISBN().equals(isbn)){
                    filteredBooks.add(book);
                }
            }
            return filteredBooks;
        }

        if(isTaken != null){
            for(Book book: theBooks){
                if(book.isTaken() == isTaken){
                    filteredBooks.add(book);
                }
            }
            return filteredBooks;
        }

        if(name != null){
            for(Book book: theBooks){
                if(book.getName().equals(name)){
                    filteredBooks.add(book);
                }
            }
            return filteredBooks;
        }


        return theBooks;
    }


    @Override
    public boolean deleteBook(String GUID) throws IOException {
        for(Book theBook: theBooks){
            if(theBook.getGUID().equals(GUID)){
                theBooks.remove(theBook);
                objectMapper.writeValue(JSONfile, theBooks);
                return true;
            }
        }
        return false;
    }
}
