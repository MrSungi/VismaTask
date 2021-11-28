package com.Visma.demo.Service;

import com.Visma.demo.entity.Book;
import com.Visma.demo.entity.LibraryRegistry;

import java.io.IOException;
import java.util.HashMap;

public interface LibraryRegistryService {

    void registerTakenBook(String client, int days, Book book) throws IOException;
    LibraryRegistry getInformation(String guid);

    int totalBooksTaken();


}
