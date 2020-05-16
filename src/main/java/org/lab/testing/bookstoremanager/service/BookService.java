package org.lab.testing.bookstoremanager.service;

import org.lab.testing.bookstoremanager.dto.BookDTO;
import org.lab.testing.bookstoremanager.dto.MessageResponseDTO;
import org.lab.testing.bookstoremanager.entity.Book;
import org.lab.testing.bookstoremanager.exception.BookNotFoundException;
import org.lab.testing.bookstoremanager.mapper.BookMapper;
import org.lab.testing.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class BookService {
	
	private BookRepository bookRepository;
	
	private final BookMapper bookMapper = BookMapper.INSTANCE;
	
	@Autowired
	public BookService(BookRepository bookRepository) { this.bookRepository = bookRepository; }
	
	@PostMapping
	public MessageResponseDTO create(BookDTO bookDTO) {
		Book book2Save = bookMapper.toModel(bookDTO);
		
		Book bookSaved = bookRepository.save(book2Save);
		return MessageResponseDTO.builder()
				.message("Book created with ID "+bookSaved.getId())
				.build();
	}

	public BookDTO findById(Long id) throws BookNotFoundException {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException(id));
		
		return bookMapper.toDTO(book);
	}
	
}