package org.lab.testing.bookstoremanager.controller;

import static org.lab.testing.bookstoremanager.utils.BookUtils.asJsonString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lab.testing.bookstoremanager.dto.BookDTO;
import org.lab.testing.bookstoremanager.dto.MessageResponseDTO;
import org.lab.testing.bookstoremanager.service.BookService;
import org.lab.testing.bookstoremanager.utils.BookUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
	
	final String BOOK_API_URI_PATH = "/api/v1/books";
	
	private MockMvc mockMvc;
	
	@Mock
	private BookService bookService;
	
	@InjectMocks
	private BookController bookController;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
				.build();
	}
	
	@Test
	void whenPOSTisCalledThenABookShouldBeCreated() {
		BookDTO bookDTO = BookUtils.createFakeBookDTO();
		MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
				.message("Book created with ID " + bookDTO.getId())
				.build();
		
		when(bookService.create(bookDTO)).thenReturn(expectedMessageResponse);
		
		try {
			mockMvc.perform(MockMvcRequestBuilders.post(BOOK_API_URI_PATH)
					.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(bookDTO)))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	@Test
	void whenPOSTwithInvalidISBNIsCalledThenABadRequestShouldBeReturned() throws Exception {
		
		BookDTO bookDTO = BookUtils.createFakeBookDTO();
		bookDTO.setIsbn("Invalid isbn");
		
		
		mockMvc.perform(MockMvcRequestBuilders.post(BOOK_API_URI_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bookDTO)))
				.andExpect(status().isBadRequest());
		
	}
}