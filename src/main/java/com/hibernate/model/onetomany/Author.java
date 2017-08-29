package com.hibernate.model.onetomany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
public class Author {

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "NATIONALITY")
	private String nationality;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Book> books = new ArrayList<>();
	
	public Author() {
		
	}
	
	public Author(Long id, String name, String nationality) {
		this.id = id;
		this.name = name;
		this.nationality = nationality;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", nationality=" + nationality + "]";
	};
}
