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
                .bean(BookProcessor.class, "getBook")
                .log("${body}")
                .marshal(bookDataFormat)
                .log("${body}")
                .to("http://localhost:8083/bookStore/loanBook");

//        EXPERIMENTAL
//        from("timer: first-timer")
//                .transform().constant("Constant")
//                .to("log:first-timer");
//    }

//    If I want an operation that does not make a change on the body you use processing
//    If you want to have an operation that makes changes to the body of the message

//    Working with ActiveMQ - What is it - are we using it as well
    }
}
