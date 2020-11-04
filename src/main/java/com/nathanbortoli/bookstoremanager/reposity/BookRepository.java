package com.nathanbortoli.bookstoremanager.reposity;

import com.nathanbortoli.bookstoremanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Book, Long> {
}
