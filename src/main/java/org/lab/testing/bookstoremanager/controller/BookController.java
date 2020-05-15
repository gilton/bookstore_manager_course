package org.lab.testing.bookstoremanager.controller;

import org.lab.testing.bookstoremanager.dto.MessageResponseDTO;
import org.lab.testing.bookstoremanager.entity.Book;
import org.lab.testing.bookstoremanager.repository.BookRepository;
import org.lab.testing.bookstoremanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
	
	private BookService bookService;
	
	public BookController() { }
	
	@Autowired
	public BookController(BookService bookService) { this.bookService = bookService; }


	@PostMapping
	public MessageResponseDTO create(@RequestBody Book book) {
		return bookService.create(book);
	}
	
}
