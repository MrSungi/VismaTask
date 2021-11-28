package com.Visma.demo.rest;

import com.Visma.demo.Service.BookService;
import com.Visma.demo.Service.LibraryRegistryService;
import com.Visma.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class LibraryController {

    @Autowired
    BookService bookService;

    @Autowired
    LibraryRegistryService libraryRegistryService;



    @PostMapping("/add-book")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HttpStatus> addBook(
            @RequestBody Book theBook
    ) throws IOException {

        boolean isAdded = bookService.addBook(theBook);

        if(!isAdded){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //return all books
    @GetMapping("/get-books")
    public List<Book> getBooks(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Boolean isTaken,
            @RequestParam(required = false) String isbn
    ) throws IOException {
        return bookService.getBooks(name, author, category, language, isTaken, isbn);
    }

    //takes book if it is not taken and registers all necessary information in Library registry
    @GetMapping("/take-book")
    public String takeABook(
            @RequestParam() String client,
            @RequestParam() int days,
            @RequestParam() String guid) throws IOException {
        Book theBook = bookService.takeBook(guid);


        String x = checkConditions(days, guid, theBook);
        if (x != null) return x;

        libraryRegistryService.registerTakenBook(client, days, theBook);


        return client + " successfully took a book: " + theBook.getName() + " for " + days + " days.";
    }


    @GetMapping("/get-book")
    public Book getBook(
            @RequestParam() String guid
    ) {
        Book theBook = bookService.takeBook(guid);
        return theBook;
    }

    @DeleteMapping(value = "/delete-book/{guid}")
    public ResponseEntity<String> deleteBook(
            @PathVariable String guid
    ) throws IOException {

        boolean isRemoved = bookService.deleteBook(guid);

        if (!isRemoved){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private String checkConditions(int days, String guid, Book theBook) {
        if(theBook == null){
            return "This book is not exists in this library";
        }
        if(theBook.isTaken()){
            return "This book is taken. You should wait for " + libraryRegistryService.getInformation(guid).getDays() + " days.";
        }
        if(days > 60){
            return "A book can't be taken for more than 2 months";
        }
        if(libraryRegistryService.totalBooksTaken() > 3){
            return "You can not take any more books from this library.";
        }
        return null;
    }

}
