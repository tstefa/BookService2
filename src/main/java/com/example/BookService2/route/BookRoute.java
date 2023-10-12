package com.example.BookService2.route;

import com.example.BookService2.model.BookDao;
import com.example.BookService2.processor.BookAddition;
import com.example.BookService2.processor.BookProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class BookRoute extends RouteBuilder {
    private final JacksonDataFormat bookDataFormat = new JacksonDataFormat(BookDao.class);
    @Override
    public void configure() {
        from("direct:book").process(new BookProcessor());

        from("direct:addBook")
                .process(new BookAddition());

        from("direct:loanBook")
                .setProperty("id",simple("${body.get(customerId)}"))
                .log("CustomerId has been set!")
                .bean(BookProcessor.class, "getBook")
                .log("${body}")
                .marshal(bookDataFormat)
                .log("${body}")
                .toD("http://localhost:8083/bookStore/loanBook/${exchangeProperty.id}");

    }
}
