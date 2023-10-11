package com.example.BookService2.route;

import com.example.BookService2.model.BookDao;
import com.example.BookService2.processor.BookAddition;
import com.example.BookService2.processor.BookProcessor;
import com.example.BookService2.util.JsonConverter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class BookRoute extends RouteBuilder {

    private final JsonConverter jsonConverter = new JsonConverter();
    private final JacksonDataFormat bookDataFormat = new JacksonDataFormat(BookDao.class);
    @Override
    public void configure() throws Exception {
        from("direct:book").process(new BookProcessor());

        from("direct:addBook")
                .process(new BookAddition());

        from("direct:loanBook")
                .bean(BookProcessor.class,"getBook")
                .marshal(bookDataFormat)
                .to("rest:post:https://localhost:8081/bookStore/loanBook/");

//        EXPERIMENTAL
    }
}
