package com.example.BookService2.processor;

import com.example.BookService2.model.BookDao;
import com.example.BookService2.util.BookOperations;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookAddition implements Processor {

    private BookOperations bookOperations = new BookOperations();

    @Override
    public void process(Exchange exchange) throws Exception {
        List<BookDao> bookDaoList = bookOperations.jsonToBookDao();
        List<BookDao> newBookDaoList = bookOperations.addBook((BookDao) exchange.getMessage().getBody(),bookDaoList);
        exchange.getMessage().setBody(bookOperations.convertBooksToString(newBookDaoList));

    }
}
