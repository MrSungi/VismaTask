package com.Visma.demo.dao;

import com.Visma.demo.entity.Book;
import com.Visma.demo.entity.LibraryRegistry;

import java.io.IOException;

public interface LibraryRegistryDAO {

    void registerTakenBook(String client, int days, Book book) throws IOException;

    LibraryRegistry getInformation(String guid);
    int totalBooksTaken();

}
