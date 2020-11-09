package com.nathanbortoli.bookstoremanager.service;

import com.nathanbortoli.bookstoremanager.dto.BookDTO;
import com.nathanbortoli.bookstoremanager.dto.MessageResponseDTO;
import com.nathanbortoli.bookstoremanager.entity.Book;
import com.nathanbortoli.bookstoremanager.exception.BookNotFoundException;
import com.nathanbortoli.bookstoremanager.mapper.BookMapper;
import com.nathanbortoli.bookstoremanager.reposity.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create (BookDTO bookDTO){
        Book bookToSabe = bookMapper.toModel(bookDTO);

        Book saveBook = bookRepository.save(bookToSabe);
        return MessageResponseDTO.builder().message("Book created with ID: " + saveBook.getId()).build();
    }


    public BookDTO findById(Long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        return bookMapper.toDTO(book);
    }
}
