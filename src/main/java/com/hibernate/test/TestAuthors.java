package com.hibernate.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hibernate.model.onetomany.Author;
import com.hibernate.model.onetomany.Book;

public class TestAuthors {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("application");
	
	public static void main(String[] args) {
		
		deteleAllAuthors();
		createData();
		printAll();

	}

	private static void deteleAllAuthors() {
		
		EntityManager manager = emf.createEntityManager();
		
		List<Author> authors = manager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
        
		manager.getTransaction().begin();
		for(Author e : authors) {
			manager.remove(e);
		}
		manager.getTransaction().commit();
		manager.close();
	}
	
	private static void createData() {
		EntityManager manager = emf.createEntityManager();
		
		manager.getTransaction().begin();
		
			Author a1 = new Author(1L,"Joanne Rowling","English");
			Author a2 = new Author(2L,"Jorge Luis Borges","Argentinian");
			Author a3 = new Author(3L,"Adolfo Bioy Casares","Argentinian");
			manager.persist(a1);
			manager.persist(a2);
			
			
			manager.persist(new Book(1L,"Harry Potter and the Philosopher's Stone", a1));
			manager.persist(new Book(2L,"Harry Potter and the Chamber of Secrets", a1));
			manager.persist(new Book(3L,"Historia universal de la infamia", a2));
			manager.persist(new Book(4L,"El Aleph ", a2));
			
			a3.getBooks().add(new Book(5L,"Dormir al sol ",a3));
			manager.persist(a3);
		
		manager.getTransaction().commit();
		
		manager.close();
		
	}
	
	private static void printAll() {
		EntityManager manager = emf.createEntityManager();
		
		Author author = manager.find(Author.class, 1L);
		
		System.out.println(author);
		for(Book book : author.getBooks()) {
			System.out.println(book);
		}
		
		author = manager.find(Author.class, 2L);
		
		System.out.println(author);
		for(Book book : author.getBooks()) {
			System.out.println(book);
		}
		
		Book book = manager.find(Book.class,5L);
		System.out.println(book);
	}
	
}
