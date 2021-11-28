package com.Visma.demo.dao;

import com.Visma.demo.entity.Book;
import com.Visma.demo.entity.LibraryRegistry;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class LibraryRegistryDAOImpl implements LibraryRegistryDAO{


    File takenBooksFile = new File(System.getProperty("user.dir") + "\\demo\\data\\takenBooks.json");
    ObjectMapper objectMapper = new ObjectMapper();

    TypeReference<HashMap<String, LibraryRegistry>> typeReference = new TypeReference<>() {};
    Map<String, LibraryRegistry> takenBooks = objectMapper.readValue(takenBooksFile, typeReference);

    public LibraryRegistryDAOImpl() throws IOException {
    }


    @Override
    public void registerTakenBook(String client, int days, Book book) throws IOException {


        takenBooks.put(book.getGUID(), new LibraryRegistry(client, days));
        objectMapper.writeValue(takenBooksFile,takenBooks);
        book.setTaken(true);
    }

    @Override
    public LibraryRegistry getInformation(String guid) {

        return takenBooks.get(guid);
    }

    @Override
    public int totalBooksTaken() {
        return takenBooks.size();
    }
}
