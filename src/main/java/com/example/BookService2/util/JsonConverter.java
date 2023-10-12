package com.example.BookService2.util;

import com.example.BookService2.model.BookDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;

@Component
public class JsonConverter implements DataFormat {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void marshal(Exchange exchange, Object graph, OutputStream stream) throws Exception {
        BookDao bookDao = exchange.getMessage().getBody(BookDao.class);
        String bookDaoJson = objectMapper.writeValueAsString(bookDao.toString());
        exchange.getMessage().setBody(bookDaoJson);

    }

    @Override
    public Object unmarshal(Exchange exchange, InputStream stream) throws Exception {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
