package com.example.BookService2.util;

import com.example.BookService2.model.BookDao;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/*Ask why you can't use @Autowired in this case and why is it returning nullPointerException*/
@Slf4j
public class BookOperations{


    public List<BookDao> jsonToBookDao() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(Constants.FILE_PATH), new TypeReference<List<BookDao>>(){});
    }

    public List<String> convertBooksToString(List<BookDao> bookDaos){
        return bookDaos.stream().map(Object::toString).collect(Collectors.toList());
    }

    public List<BookDao> addBook(BookDao bookDao,List<BookDao> bookDaoList) throws IOException {
        bookDaoList.add(bookDao);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(Constants.FILE_PATH),bookDaoList);
        log.info("Updated file: ");
        return bookDaoList;
    }



}
