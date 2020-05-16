package org.lab.testing.bookstoremanager.controller;

import javax.validation.Valid;

import org.lab.testing.bookstoremanager.dto.BookDTO;
import org.lab.testing.bookstoremanager.dto.MessageResponseDTO;
import org.lab.testing.bookstoremanager.exception.BookNotFoundException;
import org.lab.testing.bookstoremanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public MessageResponseDTO create(@RequestBody @Valid BookDTO bookDTO) {
		return bookService.create(bookDTO);
	}
	
	@GetMapping("/{id}")
	public BookDTO findById(@PathVariable Long id) throws BookNotFoundException {
		return bookService.findById(id);
	}
	
	
}