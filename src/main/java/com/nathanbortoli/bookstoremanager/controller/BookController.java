package com.nathanbortoli.bookstoremanager.controller;

import com.nathanbortoli.bookstoremanager.dto.BookDTO;
import com.nathanbortoli.bookstoremanager.dto.MessageResponseDTO;
import com.nathanbortoli.bookstoremanager.entity.Book;
import com.nathanbortoli.bookstoremanager.reposity.BookRepository;
import com.nathanbortoli.bookstoremanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public MessageResponseDTO create (@RequestBody @Valid BookDTO bookDTO){
        return bookService.create(bookDTO);
    }

    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable Long id){
        return bookService.findById(id);
    }
}
