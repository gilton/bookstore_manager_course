package org.lab.testing.bookstoremanager.utils;

import org.lab.testing.bookstoremanager.dto.AuthorDTO;
import org.lab.testing.bookstoremanager.entity.Author;

import com.github.javafaker.Faker;

public class AuthorUtils {
	
	private static final Faker faker = Faker.instance();
	
	public static AuthorDTO createFakeAuthorDTO() {
		return AuthorDTO.builder()
				.id(faker.number().randomNumber())
				.name(faker.book().author())
				.age(faker.number().numberBetween(18,100))
				.build();
	}
	
	public static Author createFakeAuthor() {
		return Author.builder()
				.id(faker.number().randomNumber())
				.name(faker.book().author())
				.age(faker.number().numberBetween(18,100))
				.build();
	}
	
}
