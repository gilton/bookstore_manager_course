package org.lab.testing.bookstoremanager.mapper;

import org.lab.testing.bookstoremanager.dto.BookDTO;
import org.lab.testing.bookstoremanager.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
	
	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

	Book toModel(BookDTO bookDTO);
	
	BookDTO toDTO(Book book);
	
}
