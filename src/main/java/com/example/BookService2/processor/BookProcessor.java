package com.example.BookService2.processor;

import com.example.BookService2.model.BookDao;
import com.example.BookService2.util.BookOperations;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookProcessor implements Processor {


    private BookOperations bookOperations = new BookOperations();


    @Override
    public void process(Exchange exchange) throws Exception {

        List<BookDao> books = bookOperations.jsonToBookDao();
        exchange.getMessage().setBody(bookOperations.convertBooksToString(books));
    }



}
