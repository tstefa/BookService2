package com.example.BookService2.util;

import com.example.BookService2.model.BookDao;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/*Ask why you can't use @Autowired in this case and why is it returning nullPointerException*/
@Slf4j
public class BookOperations{


    public List<BookDao> jsonToBookDao() throws IOException {
        Resource classPathResource = new ClassPathResource(Constants.FILE_PATH);
        File file = classPathResource.getFile();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file,new TypeReference<List<BookDao>>(){});
    }

    public List<String> convertBooksToString(List<BookDao> bookDaos){
        return bookDaos.stream().map(Object::toString).collect(Collectors.toList());
    }

    public List<BookDao> addBook(BookDao bookDao,List<BookDao> bookDaoList) throws IOException {

//        JSONObject jsonObject = new JSONObject(bookDaoList);
        bookDaoList.add(bookDao);
        try (FileWriter file = new FileWriter(Constants.FILE_PATH)) {
            JSONObject jsonObject = new JSONObject(bookDaoList.toString());

            log.info("Writing file ...");
            file.write(bookDaoList.toString());
            log.info("File written successfully!");
        } catch (Exception e){
            e.printStackTrace();
        }

//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
////            InputStream inputStream = new FileInputStream(new File("/books.json"));
////            TypeReference<List<BookDao>> typeReference = new TypeReference<List<BookDao>>() {};
////            List<BookDao> bookDaos = objectMapper.readValue(inputStream,typeReference);
//            bookDaoList.add(bookDao);
//
//            objectMapper.writeValue(new File("/books.json"),bookDaoList);
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        }catch (JsonParseException e){
//            e.printStackTrace();
//        }

//        FileWriter file = new FileWriter("/books.json");
//        file.append(bookDao);
        return bookDaoList;
    }



}
