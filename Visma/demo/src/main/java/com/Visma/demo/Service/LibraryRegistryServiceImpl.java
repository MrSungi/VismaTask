package com.Visma.demo.Service;

import com.Visma.demo.dao.LibraryRegistryDAO;
import com.Visma.demo.entity.Book;
import com.Visma.demo.entity.LibraryRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LibraryRegistryServiceImpl implements LibraryRegistryService{

    @Autowired
    LibraryRegistryDAO libraryRegistryDAO;


    @Override
    public void registerTakenBook(String client, int days, Book book) throws IOException {


        libraryRegistryDAO.registerTakenBook(client, days, book);

    }

    @Override
    public LibraryRegistry getInformation(String guid) {
        return libraryRegistryDAO.getInformation(guid);
    }

    @Override
    public int totalBooksTaken() {
        return libraryRegistryDAO.totalBooksTaken();
    }


}
