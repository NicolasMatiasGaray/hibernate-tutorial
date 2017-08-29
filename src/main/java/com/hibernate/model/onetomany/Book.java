package com.hibernate.model.onetomany;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
public class Book {

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TITLE")
	private String title;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_AUTHOR")
	private Author author;

	public Book() {
	}

	public Book(Long id, String name, Author author) {
		this.id = id;
		this.title = name;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + title + ", author=" + author + "]";
	}
	
}
