package org.lab.testing.bookstoremanager.service;

import org.lab.testing.bookstoremanager.dto.MessageResponseDTO;
import org.lab.testing.bookstoremanager.entity.Book;
import org.lab.testing.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class BookService {
	
	private BookRepository bookRepository;
	
	@Autowired
	public BookService(BookRepository bookRepository) { this.bookRepository = bookRepository; }
	
	@PostMapping
	public MessageResponseDTO create(Book book) {
		Book bookSaved = bookRepository.save(book);
		return MessageResponseDTO.builder()
				.message("Book created with ID "+bookSaved.getId())
				.build();
	}
	
}
