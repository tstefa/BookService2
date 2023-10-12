package com.example.BookService2.controller;

import com.example.BookService2.model.BookDao;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    ProducerTemplate producerTemplate;

    @GetMapping("/all-books")
    public ResponseEntity<String> getBooks() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(producerTemplate.requestBody("direct:book","").toString());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @PostMapping("/add-book")
    public ResponseEntity<String> addBook(@RequestBody BookDao bookDao) {

        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(producerTemplate.requestBody("direct:addBook",bookDao).toString());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

    @GetMapping("/loan-book/{bookId}/to/{customerId}")
    public String loanBook(@PathVariable("bookId") String bookId,@PathVariable("customerId") String customerId) {

        Map<String,String> idList = new HashMap<>();
        idList.put("bookId",bookId);
        idList.put("customerId",customerId);
        try {
            producerTemplate.sendBody("direct:loanBook",idList);
        } catch (Exception e){
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST.toString();
        }
        return HttpStatus.OK.toString();
    }
}
