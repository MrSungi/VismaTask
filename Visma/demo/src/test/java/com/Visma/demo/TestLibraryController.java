package com.Visma.demo;

import com.Visma.demo.Service.BookService;
import com.Visma.demo.Service.LibraryRegistryService;
import com.Visma.demo.Service.LibraryRegistryServiceImpl;
import com.Visma.demo.entity.Book;
import com.Visma.demo.entity.LibraryRegistry;
import com.Visma.demo.rest.LibraryController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestLibraryController {


    @Mock
    LibraryRegistryService mockLibraryRegistryService;
    @Mock
    BookService mockBookService;


    @InjectMocks
    LibraryController libraryController = new LibraryController();


    @Test
    public void testAddBook() throws IOException {

        Book book = new Book();

        libraryController.addBook(book);

        verify (mockBookService, times(1)).addBook(book);
    }

    @Test
    public void testGetBook(){
        Book book = new Book();

        libraryController.getBook(book.getGUID());

        verify(mockBookService, times(1)).takeBook(book.getGUID());
    }

    @Test
    public void testDeleteBook() throws IOException {
        Book book = new Book();

        libraryController.deleteBook(book.getGUID());

        verify(mockBookService, times(1)).deleteBook(book.getGUID());

    }

    @Test
    public void testGetBooks() throws IOException {

        libraryController.getBooks(null,null,null,null,null,null);

        System.out.println(System.getProperty("user.dir"));

        verify(mockBookService, times(1)).getBooks(null,null,null,null,null,null);

    }


    // Need to work more on that.
//    @Test
//    public void testTakeBook() throws IOException {
//        Book book = new Book();
//        LibraryRegistry libraryRegistry = new LibraryRegistry("Ignas", 25);
//
//        libraryController.takeABook(libraryRegistry.getClient(), libraryRegistry.getDays(), book.getGUID());
//
//        book.setTaken(false);
//        verify(mockLibraryRegistryService).registerTakenBook(libraryRegistry.getClient(), libraryRegistry.getDays(), book);
//
//    }



}
