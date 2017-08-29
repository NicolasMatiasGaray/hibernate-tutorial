package com.hibernate.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hibernate.model.manytomany.MAuthor;
import com.hibernate.model.manytomany.MBook;
import com.hibernate.model.onetomany.Book;

public class TestManyToMany {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("application");

	public static void main(String[] args) {
		//deteleAllBooks();
		//unidirectionalTest();
		bidirectionalTest();
		//printAll();
	}
	
	private static void deteleAllBooks() {
		
		EntityManager manager = emf.createEntityManager();
		
		List<MBook> books = manager.createQuery("SELECT a FROM MBook a", MBook.class).getResultList();
        
		manager.getTransaction().begin();
		for(MBook e : books) {
			manager.remove(e);
		}
		manager.getTransaction().commit();
		manager.close();
	}
	
	private static void unidirectionalTest() {
		
		EntityManager manager = emf.createEntityManager();
		
		manager.getTransaction().begin();
		
		MAuthor a1 = new MAuthor("Joanne Rowling","English");
		MAuthor a2 = new MAuthor("Jorge Luis Borges","Argentinian");
		MAuthor a3 = new MAuthor("Adolfo Bioy Casares","Argentinian");
		MAuthor a4 = new MAuthor("Nicolas Garay", "Argentinian");
		
		MBook b1 = new MBook("Harry Potter and the Philosopher's Stone");
		MBook b2 = new MBook("Harry Potter and the Chamber of Secrets");
		MBook b3 = new MBook("Historia universal de la infamia");
		MBook b4 = new MBook("El Aleph");
		MBook b5 = new MBook("Dormir al sol");
		
		b1.addAuthor(a1);
		b1.addAuthor(a4);
		b2.addAuthor(a1);
		b2.addAuthor(a4);
		b3.addAuthor(a2);
		b3.addAuthor(a4);
		b4.addAuthor(a2);
		b4.addAuthor(a4);
		b5.addAuthor(a3);
		b5.addAuthor(a4);
		
		manager.persist(b1);
		manager.persist(b2);
		manager.persist(b3);
		manager.persist(b4);
		manager.persist(b5);
		
		manager.getTransaction().commit();
		manager.close();
	}
	
	private static void bidirectionalTest() {
		
		EntityManager manager = emf.createEntityManager();
		
		manager.getTransaction().begin();
		
		MAuthor author = manager.find(MAuthor.class, 2L);
		
		if(author != null) {
			System.out.println(author);
			for(MBook book : author.getBooks()) {
				System.out.println(book);
			}
		}
	}
	
	private static void printAll() {
		EntityManager manager = emf.createEntityManager();
		
		Book book = manager.find(Book.class,1L);
		System.out.println(book);
		
		book = manager.find(Book.class,3L);
		System.out.println(book);
		
		book = manager.find(Book.class,5L);
		System.out.println(book);

	}
	
	
}
