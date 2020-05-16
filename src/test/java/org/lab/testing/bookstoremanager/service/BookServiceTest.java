package org.lab.testing.bookstoremanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.lab.testing.bookstoremanager.utils.BookUtils.createFakeBook;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lab.testing.bookstoremanager.dto.BookDTO;
import org.lab.testing.bookstoremanager.entity.Book;
import org.lab.testing.bookstoremanager.exception.BookNotFoundException;
import org.lab.testing.bookstoremanager.repository.BookRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookService bookService;

	@Test
	public void whenGivenAnExistedIdThenReturnABook() throws BookNotFoundException {
		Book expectedFoundBook = createFakeBook();
		when(bookRepository.findById(expectedFoundBook.getId())).thenReturn(Optional.of(expectedFoundBook));
		
		BookDTO bookDTO = bookService.findById(expectedFoundBook.getId());
		
		assertEquals(expectedFoundBook.getName(), bookDTO.getName());
		assertEquals(expectedFoundBook.getIsbn(), bookDTO.getIsbn());
		assertEquals(expectedFoundBook.getPublisherName(), bookDTO.getPublisherName());
	}

}