package com.dagasource.Controller;

import com.dagasource.InsertBook.Book;
import com.dagasource.InsertUser.*;
import com.dagasource.InsertBook.BookRepository;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fabio on 25/01/17.
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
class Controller {

    private final BookRepository bookRepository;
    private final UserRepo userRepo;

    public Controller(BookRepository bookRepository, UserRepo userRepo) {
        this.bookRepository = bookRepository;
        this.userRepo = userRepo;
    }

    @RequestMapping(value= "/addBook", method = RequestMethod.POST)
    public ResponseEntity<Book> addBook(@RequestParam("isbn") String isbn){
        try {
            Book book = new Book();
            book.setIsbn(isbn);
            Book saved = bookRepository.save(book);
            System.out.println(isbn);
            return ResponseEntity.ok().body(saved);
        }catch (Exception e){
            System.out.println("ATTENZIONE: " + e.getMessage());
        }
        return null;
    }

    @RequestMapping(value= "/addUser", method = RequestMethod.POST)
    public ResponseEntity<Users> addUser(@RequestBody String request){
        try {
            Users user = new Users();
            JsonObject jsonObject = null;

            jsonObject = new JsonParser().parse(request).getAsJsonObject();
            String cognome = jsonObject.get("cognome").getAsString();
            user.setCognome(cognome);
            String nome = jsonObject.get("nome").getAsString();
            user.setNome(nome);
            Users saved = userRepo.save(user);
            return ResponseEntity.ok().body(saved);

        }catch (Exception e){
            System.out.println("ATTENZIONE: " + e.getMessage());
        }
        return null;
    }

    @RequestMapping(value= "/getDataHome", method = RequestMethod.GET)
    public ResponseEntity<String> getDataHome(){
        try {

            String response = "{"+'\"'+"messaggio:123"+'\"';
            return ResponseEntity.ok().body(response);

        }catch (Exception e){
            System.out.println("ATTENZIONE: " + e.getMessage());
        }
        return null;
    }
}