package com.hibernate.model.manytomany;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MAUTHOR")
public class MAuthor {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "NATIONALITY")
	private String nationality;

	@ManyToMany(cascade=CascadeType.ALL, mappedBy="authors") 
	private Set<MBook> books; 
	
	public MAuthor() {
		
	}
	
	public MAuthor(String name, String nationality) {
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
	
	public Set<MBook> getBooks() {
		return books;
	}

	public void setBooks(Set<MBook> books) {
		this.books = books;
	}

	public void addBook(MBook book) {
		this.books.add(book);
	}
	
	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", nationality=" + nationality + "]";
	};
}
