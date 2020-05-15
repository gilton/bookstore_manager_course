package org.lab.testing.bookstoremanager.repository;

import org.lab.testing.bookstoremanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	
}
