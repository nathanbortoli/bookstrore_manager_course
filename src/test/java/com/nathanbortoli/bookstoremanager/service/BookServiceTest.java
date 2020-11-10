package com.nathanbortoli.bookstoremanager.service;

import com.nathanbortoli.bookstoremanager.dto.BookDTO;
import com.nathanbortoli.bookstoremanager.entity.Book;
import com.nathanbortoli.bookstoremanager.exception.BookNotFoundException;
import com.nathanbortoli.bookstoremanager.reposity.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.nathanbortoli.bookstoremanager.util.BookUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void whenGivenExistingIdThenReturnThisBook() throws BookNotFoundException {
       Book expectedFoundBook =  createFakeBook();

        when(bookRepository.findById(expectedFoundBook.getId())).thenReturn(Optional.of(expectedFoundBook));

        BookDTO bookDTO = bookService.findById(expectedFoundBook.getId());

        assertEquals(expectedFoundBook.getName(), bookDTO.getName());
        assertEquals(expectedFoundBook.getIsbn(), bookDTO.getIsbn());
        assertEquals(expectedFoundBook.getPublisherName(), bookDTO.getPublisherName());
    }

    @Test
    void whenGivenUnexistingIdThenNotFindThrowAnException() {
        var invalidId = 10L;

        when(bookRepository.findById(invalidId))
                .thenReturn(Optional.ofNullable(any(Book.class)));

        assertThrows(BookNotFoundException.class, () -> bookService.findById(invalidId));


    }

}
