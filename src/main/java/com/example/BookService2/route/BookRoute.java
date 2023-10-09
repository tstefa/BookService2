package com.example.BookService2.route;

import com.example.BookService2.processor.BookAddition;
import com.example.BookService2.processor.BookProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BookRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:book").process(new BookProcessor());

        from("direct:addBook").process(new BookAddition());
    }
}
